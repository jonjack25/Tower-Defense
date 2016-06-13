import java.util.*;
import java.util.List;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
public class Game
{
    private Grid grid;
    public List<Turret> turrets;
    public List<Enemy> enemies;
    public List<Bullet> bullets;
    private Base base;
    private int gameState;
    private int coins;
    private int round;
    private int enemyCount;
    private int typeToSpawn;
    private int randSpawnTime;
    private Timer spawn, interval;
    
    public static int numTur = 0;
    
    private Position temp = new Position(10, 10, 10, 10, 10, 10);

    public Game(GameScreen gs)
    {
        grid = new Grid(gs);
        turrets = new ArrayList<Turret>();
        enemies = new ArrayList<Enemy>();
        bullets = new ArrayList<Bullet>();
        base = new Base(grid.getGameGrid()[22][45], 4, 4);
        gameState = 0;
        coins = 300;
        randSpawnTime = (int)(Math.random() * 2001) + 500;
        typeToSpawn = (int)(Math.random() * 3);
        spawn = new Timer(randSpawnTime, new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(enemyCount > 0)
                {
                    if(typeToSpawn == 0)
                        enemies.add(new BaseEnemy(grid.getGameGrid()[7][2]));
                    else if(typeToSpawn == 1)
                        enemies.add(new QuickEnemy(grid.getGameGrid()[7][2]));
                    else
                        enemies.add(new SlowEnemy(grid.getGameGrid()[7][2]));
                      
                    randSpawnTime = (int)(Math.random() * 2001) + 500;
                    //setDelay(randSpawnTime);
                    typeToSpawn = (int)(Math.random() * 3);  
                    enemyCount--;
                }
            }
        });
        interval = new Timer(7000, new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                gameState = 1;
                round++;
                scaleEnemies();
            }
        });
    }
    
    public Grid getGrid()
    {
        return grid;
    }
    
    public void updateGame()
    {
        if(gameState == 0)
        {
            interval.start();
            spawn.stop();
        }
        else if(gameState == 1)
        {
            spawn.start();
            interval.stop();
            fireTurrets();
            moveEnemies();
            moveBullets();
            collideEnemiesAndBullets();
            removeEntities();
            if(enemyCount == 0)
                gameState = 0;
            if(baseHasBeenBreached())
                gameState = 2;
        }
        else
        {
            interval.stop();
            spawn.stop();
        }
    }
    
    public void draw(Graphics g)
    {
        grid.draw(g);
        for(Turret t : turrets)
            t.draw(g);
        for(Enemy e: enemies)
            e.draw(g);
        for(Bullet b : bullets)
            b.draw(g);
    }
    
    public Turret purchaseStrongTurret()
    {
        if(coins >= StrongTurret.PURCHASE_COST)
        {
            coins -= StrongTurret.PURCHASE_COST;
            return new StrongTurret(temp);
        }
        return null;
    }
    
    public Turret purchaseFastTurret()
    {
        if(coins >= FastTurret.PURCHASE_COST)
        {
            coins -= FastTurret.PURCHASE_COST;
            return new FastTurret(temp);
        }
        return null;
    }
    
    public Turret purchaseBaseTurret()
    {
        if(coins >= BaseTurret.PURCHASE_COST)
        {
            coins -= BaseTurret.PURCHASE_COST;
            return new BaseTurret(temp);
        }
        return null;
    }
    
    public void cancelPurchase(Turret t)
    {
        if(t instanceof StrongTurret)
            coins += StrongTurret.PURCHASE_COST;
        else if(t instanceof FastTurret)
            coins += FastTurret.PURCHASE_COST;
        else if(t instanceof BaseTurret)
            coins += BaseTurret.PURCHASE_COST;
    }
    
    public void placeTurret(Turret t, Position p)
    {
        turrets.add(t);
        t.setPosition(p);
        int x = p.getRow();
        int y = p.getCol();
        for(int i = x; i > x - 3; i--)
        {
            for(int j = y; j > y - 3; j--)
            {
                grid.setFilled(grid.getGameGrid()[i][j], t);
            }
        }
        
    }
    
    public void upgradeTurret(Turret t)
    {
        if(coins >= t.getUpgradeCost())
        {
            coins -= t.getUpgradeCost();
            t.upgradeDamage(10);
        }
    }
    
    public void getKillCoins(Enemy e)
    {
        coins += e.getKillCost();
    }
    
    public void scaleEnemies()
    {
        enemyCount = 2 * round;
        QuickEnemy.HEALTH += 5;
        BaseEnemy.HEALTH += 10;
        SlowEnemy.HEALTH += 15;
    }
    
    public void fireTurrets()
    {
        for(Turret t : turrets)
        {
            Bullet b = t.fire(enemies);
            if(b != null)
                bullets.add(b);
        }
    }
    
    public void moveBullets()
    {
        
    }
    
    public void moveEnemies()
    {
        Point temp;
        int axis, xTarget, yTarget;
        for(Enemy e : enemies)
        {
            if(e.move() == null)
                continue;
            else
            {
                temp = e.move();
                axis = e.getPath().getAxis();
                xTarget = (int)temp.getX();
                yTarget = (int)temp.getY();
                if(grid.getGameGrid()[xTarget][yTarget].isEmpty() && grid.isInPath(grid.getGameGrid()[xTarget][yTarget]))
                {
                    grid.setFilled(e.getPosition(), null);
                    e.setPosition(grid.getGameGrid()[xTarget][yTarget]);
                    grid.setFilled(grid.getGameGrid()[xTarget][yTarget], e);
                }
                else if(axis == 1)
                {
                    if(grid.getGameGrid()[xTarget + 1][yTarget].isEmpty() && grid.isInPath(grid.getGameGrid()[xTarget + 1][yTarget]))
                    {
                        grid.setFilled(e.getPosition(), null);
                        e.setPosition(grid.getGameGrid()[xTarget + 1][yTarget]);
                        grid.setFilled(grid.getGameGrid()[xTarget + 1][yTarget], e);
                    }
                    else if(grid.getGameGrid()[xTarget - 1][yTarget].isEmpty() && grid.isInPath(grid.getGameGrid()[xTarget - 1][yTarget]))
                    {
                        grid.setFilled(e.getPosition(), null);
                        e.setPosition(grid.getGameGrid()[xTarget - 1][yTarget]);
                        grid.setFilled(grid.getGameGrid()[xTarget - 1][yTarget], e);
                    }
                }
                else if(axis == -1)
                {
                    if(grid.getGameGrid()[xTarget][yTarget + 1].isEmpty() && grid.isInPath(grid.getGameGrid()[xTarget][yTarget + 1]))
                    {
                        grid.setFilled(e.getPosition(), null);
                        e.setPosition(grid.getGameGrid()[xTarget][yTarget + 1]);
                        grid.setFilled(grid.getGameGrid()[xTarget][yTarget + 1], e);
                    }
                    else if(grid.getGameGrid()[xTarget][yTarget - 1].isEmpty() && grid.isInPath(grid.getGameGrid()[xTarget][yTarget - 1]))
                    {
                        grid.setFilled(e.getPosition(), null);
                        e.setPosition(grid.getGameGrid()[xTarget][yTarget - 1]);
                        grid.setFilled(grid.getGameGrid()[xTarget][yTarget - 1], e);
                    }
                }
            }
        }
    }
    
    public void collideEnemiesAndBullets()
    {
        for(Bullet b : bullets)
        {
            for(Enemy e : enemies)
            {
                if(b.hasCollided(e))
                {
                    b.damageEnemy(e);
                    b.changeGameState();
                }
            }
        }
    }
    
    public void removeEntities()
    {
        for(Bullet b : bullets)
        {
            if(b.getGameState() == 1)
            {
                bullets.remove(b);
            }
        }
        for(Enemy e : enemies)
        {
            if(e.getHealth() <= 0)
            {
                enemies.remove(e);
            }
        }
    }
    
    public boolean baseHasBeenBreached()
    {
        for(Enemy e : enemies)
        {
            for(int i = base.getPosition().getRow(); i < base.getPosition().getRow() + base.getLength(); i++)
            {
                for(int j = base.getPosition().getCol(); j < base.getPosition().getCol() + base.getWidth(); j++)
                {
                    if(e.getPosition().equals(grid.getGameGrid()[i][j]))
                        return true;
                }
            }
        }
        return false;
    }
    
}
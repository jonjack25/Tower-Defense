import java.util.*;
import java.util.List;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
public class Game
{
    private Grid grid;
    private List<Turret> turrets;
    private List<Enemy> enemies;
    private List<Bullet> bullets;
    private Base base;
    private int gameState;
    private int coins;
    private int round;
    private int enemyCount;
    private int typeToSpawn;
    private int randSpawnTime;
    private int spawnPosition;
    private int previousSpawn;
    private boolean first = true;
    private boolean next = true;
    private Timer spawn, interval;

    public Game(GameScreen gs)
    {
        grid = new Grid(gs);
        turrets = new ArrayList<Turret>();
        enemies = new ArrayList<Enemy>();
        bullets = new ArrayList<Bullet>();
        base = new Base(grid.getGameGrid()[22][45], 4, 5);
        gameState = 1;
        enemyCount = 2;
        round = 1;
        coins = 300;
        randSpawnTime = (int)(Math.random() * 2001) + 500;
        typeToSpawn = (int)(Math.random() * 3);
        spawnPosition = (int)(Math.random() * 4) + 3;
        previousSpawn = spawnPosition;
        
        spawn = new Timer(randSpawnTime, new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(enemyCount > 0)
                {
                    //System.out.println(enemyCount);
                    while(spawnPosition == previousSpawn)
                    {
                        spawnPosition = (int)(Math.random() * 4) + 3;
                    }
                    if(typeToSpawn == 0)
                    {
                        Enemy en = new BaseEnemy(grid.getGameGrid()[spawnPosition][0]);
                        enemies.add(en);
                        grid.setFilled(grid.getGameGrid()[spawnPosition][0], en);
                    }
                    else if(typeToSpawn == 1)
                    {
                        Enemy en = new QuickEnemy(grid.getGameGrid()[spawnPosition][0]);
                        enemies.add(en);
                        grid.setFilled(grid.getGameGrid()[spawnPosition][0], en);
                    }
                    else
                    {
                        Enemy en = new SlowEnemy(grid.getGameGrid()[spawnPosition][0]);
                        enemies.add(en);
                        grid.setFilled(grid.getGameGrid()[spawnPosition][0], en);
                    }
                      
                    randSpawnTime = (int)(Math.random() * 2001) + 2000;
                    //setDelay(randSpawnTime);
                    next = true;
                    typeToSpawn = (int)(Math.random() * 3);
                    previousSpawn = spawnPosition;
                    spawnPosition = (int)(Math.random() * 4) + 3;
                    enemyCount--;
                    //System.out.println(enemies.size());
                }
            }
        });
        interval = new Timer(3000, new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                gameState = 1;
                if(enemyCount == 0)
                {
                    round++;
                    scaleEnemies();
                }
            }
        });
        spawn.setInitialDelay(0);
        spawn.start();
    }
    
    public Grid getGrid()
    {
        return grid;
    }
    
    public void updateGame()
    {
        if(gameState == 0)
        {
            if(first == true)
            {
                interval.start();
                first = false;
            }
        }
        else if(gameState == 1)
        {
            if(first == false)
            {
                first = true;
                interval.stop();
                spawn.start();
            }
            fireTurrets();
            moveEnemies();
            moveBullets();
            collideEnemiesAndBullets();
            removeEntities();
            if(enemies.size() == 0)
            {
                gameState = 0;
            }
            if(baseHasBeenBreached())
            {
                gameState = 2;
                System.out.println("Base has been breached");
            }
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
        base.draw(g);
    }
    
    public Turret findTurret(Position p)
    {
        if(p == null)
            return null;
        for(Turret t : turrets)
        {
            int i = t.getPosition().getRow();
            int j = t.getPosition().getCol();
            for(int a = i; a > i - 3; a--)
            {
                for(int b = j; b > j - 3; b--)
                {
                    if(p.getRow() == a && p.getCol() == b)
                        return t;
                }
            }
        }
        return null;
    }
    
    public int getCoins()
    {
        return coins;
    }
    
    public int getRound()
    {
        return round;
    }
    
    public Turret purchaseStrongTurret()
    {
        if(coins >= StrongTurret.PURCHASE_COST)
        {
            coins -= StrongTurret.PURCHASE_COST;
            return new StrongTurret();
        }
        return null;
    }
    
    public Turret purchaseFastTurret()
    {
        if(coins >= FastTurret.PURCHASE_COST)
        {
            coins -= FastTurret.PURCHASE_COST;
            return new FastTurret();
        }
        return null;
    }
    
    public Turret purchaseBaseTurret()
    {
        if(coins >= BaseTurret.PURCHASE_COST)
        {
            coins -= BaseTurret.PURCHASE_COST;
            return new BaseTurret();
        }
        return null;
    }
    
    public void cancelBasePurchase()
    {
        coins += BaseTurret.PURCHASE_COST;
    }
    
    public void cancelFastPurchase()
    {
        coins += FastTurret.PURCHASE_COST;
    }
    
    public void cancelStrongPurchase()
    {
        coins += StrongTurret.PURCHASE_COST;
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
            t.upgradeDamage();
        }
        //System.out.println(coins);
    }
    
    public void getKillCoins(Enemy e)
    {
        coins += e.getKillCost();
    }
    
    public void scaleEnemies()
    {
        enemyCount = 2 * round;
        QuickEnemy.HEALTH += 20;
        BaseEnemy.HEALTH += 30;
        SlowEnemy.HEALTH += 40;
    }
    
    public void fireTurrets()
    {
        for(Turret t : turrets)
        {
            Bullet b = t.fire(enemies);
            if(b != null)
            {
                b.setPosition(t.getPosition());
                bullets.add(b);
            }
        }
    }
    
    public void moveBullets()
    {
        for(Bullet b : bullets)
        {
            b.move();
        }
    }
    
    public void moveEnemies()
    {
        Point temp;
        
        int axis, dir, xTarget, yTarget;
        for(Enemy e : enemies)
        {
            temp = e.move();
            if(e.getPath().getTurn() == true)
                continue;
            axis = e.getPath().getAxis();
            dir = e.getPath().getDirection();
            if(temp == null)
            {
//                 Position p;
//                 if(axis == 1 && dir == 1)
//                     temp = new Point(e.getXCoord() + 1, e.getYCoord());
//                 else if(axis == 1 && dir == -1)
//                     temp = new Point(e.getXCoord(), e.getYCoord() + 1);
//                 else if(axis == -1 && dir == 1)
//                     temp = new Point(e.getXCoord(), e.getYCoord() + 1);
//                 else if(axis == -1 && dir == -1)
//                     temp = new Point(e.getXCoord() -1, e.getYCoord());
//                 p = grid.getPosition(temp);
//                 if(p == e.getPosition())
//                     e.setCoordinates(temp);
                 continue;
            }
            else
            {
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
        for(int i = bullets.size() - 1; i >= 0; i--)
        {
            if(bullets.get(i).getGameState() == 1 || bullets.get(i).getEnemy().getPosition().getType() == 0)
            {
                bullets.remove(i);
            }
        }
        for(int i = enemies.size() - 1; i >= 0; i--)
        {
            if(enemies.get(i).getHealth() <= 0)
            {
                coins += enemies.get(i).getKillCost();
                grid.setFilled(enemies.get(i).getPosition(), null);
                enemies.remove(i);
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

//Game - The Game class serves as the brain of the program and the bridge between the Entities and Grid and the GUI. 
//       It implements the purchasing system as well as the interacction between Entities

import java.util.*;
import java.util.List;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
public class Game
{
    //Private Fields
    
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
    private int lives;
    private boolean first;
    private boolean next;
    private boolean displayRound;
    private boolean gameOver;
    private Timer spawn, interval;
    private GameScreen screen;

    //Constructors
    
    public Game(GameScreen gs)
    {
        screen = gs;
        grid = new Grid(gs);
        turrets = new ArrayList<Turret>();
        enemies = new ArrayList<Enemy>();
        bullets = new ArrayList<Bullet>();
        base = new Base(grid.getGameGrid()[22][45], 4, 5);
        gameState = 0;
        enemyCount = 2;
        round = 0;
        coins = 300;
        randSpawnTime = (int)(Math.random() * 1701) + 300;
        typeToSpawn = (int)(Math.random() * 3);
        spawnPosition = (int)(Math.random() * 4) + 3;
        lives = 3;
        first = true;
        next = false;
        displayRound = true;
        gameOver = false;
        
        //The spawn timer continually spawns random types of enemies with varying delays
        spawn = new Timer(randSpawnTime, new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(enemyCount > 0)
                {
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
                      
                    randSpawnTime = (int)(Math.random() * 1701) + 300;
                    typeToSpawn = (int)(Math.random() * 3);
                    spawnPosition = (int)(Math.random() * 4) + 3;
                    next = true;
                    enemyCount--;
                }
            }
        });
        
        //The interval is a 3 second period after which the round is updated and the enemies scaled
        interval = new Timer(3000, new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                gameState = 1;
                spawn.start();
                if(round > 1)
                    scaleEnemies();
            }
        });
        spawn.setInitialDelay(0);
    }
    
    //Accessors
    
    public Grid getGrid()
    {
        return grid;
    }
    
    public int getCoins()
    {
        return coins;
    }
    
    public int getRound()
    {
        return round;
    }
    
    public int getLives()
    {
        return lives;
    }
    
    public boolean getGameOver()
    {
        return gameOver;
    }
    
    //Methods
    
    //Postcondition - game is updated, with either the interval or the spawn timer running at all times. This methods moves enemies, bullets, and fires turrets.
    //                It also removes Entities and checks whether the game is over or switching levels.                 
    public void updateGame()
    {
        if(gameState == 0)
        {
            if(first == true)
            {
                round++;
                interval.start();
                removeEntities();
                first = false;
                displayRound = true;
            }
        }
        else if(gameState == 1)
        {
            if(first == false)
            {
                first = true;
                interval.stop();
                displayRound = false;
                return;
            }
            if(next)
            {
                spawn.setDelay(randSpawnTime);
                next = false;
            }
            fireTurrets();
            moveEnemies();
            moveBullets();
            collideEnemiesAndBullets();
            removeEntities();
            if(enemies.size() == 0 && enemyCount == 0)
            {
                gameState = 0;
            }
            if(baseHasBeenBreached())
            {
                lives--;
            }
            if(lives == 0)
            {
                gameState = 2;
                gameOver = true;
                screen.setSound("resources/Lose.wav");
            }
        }
        else
        {
            interval.stop();
            spawn.stop();
        }
    }
    
    //Postcondition - draws all the Entities, the grid, and any other necessary displays(like round)
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
        
        if(displayRound && round != 0)
        {
            g.setColor(new Color(200, 34, 12));
            g.setFont(new Font("SansSerif", Font.BOLD, 300));
            
            g.drawString(round + "", screen.getFrame().getWidth() / 2 - 100, screen.getFrame().getHeight() / 2);
        }
        
        if(gameOver)
        {
            g.setColor(new Color(0, 0, 0));
            g.setFont(new Font("SansSerif", Font.BOLD, 100));
            
            g.drawString("GAME OVER", screen.getFrame().getWidth() / 2 - 300, screen.getFrame().getHeight() / 2 - 100);
        }
    }
    
    
    //Precondition - p != null
    //Postcondition - returns the Turret that exists at the Position passed in
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
    
    //Precondition - There are enough coins for a purchase
    //Postcondition - Returns a new StrongTurret
    public Turret purchaseStrongTurret()
    {
        if(coins >= StrongTurret.PURCHASE_COST)
        {
            coins -= StrongTurret.PURCHASE_COST;
            return new StrongTurret();
        }
        return null;
    }
    
    //Precondition - There are enough coins for a purchase
    //Postcondition - Returns a new FastTurret
    public Turret purchaseFastTurret()
    {
        if(coins >= FastTurret.PURCHASE_COST)
        {
            coins -= FastTurret.PURCHASE_COST;
            return new FastTurret();
        }
        return null;
    }
    
    //Precondition - There are enough coins for a purchase
    //Postcondition - Returns a new BaseTurret
    public Turret purchaseBaseTurret()
    {
        if(coins >= BaseTurret.PURCHASE_COST)
        {
            coins -= BaseTurret.PURCHASE_COST;
            return new BaseTurret();
        }
        return null;
    }
    
    //Postcondition - cancels a purchase by adding the purchase cost back to coins
    public void cancelBasePurchase()
    {
        coins += BaseTurret.PURCHASE_COST;
    }
    
    ////Postcondition - cancels a purchase by adding the purchase cost back to coins
    public void cancelFastPurchase()
    {
        coins += FastTurret.PURCHASE_COST;
    }
    
    //Postcondition - cancels a purchase by adding the purchase cost back to coins
    public void cancelStrongPurchase()
    {
        coins += StrongTurret.PURCHASE_COST;
    }
    
    //Precondition - t != null, p != null
    //Postcondition - places t at Position p and updates the Grid accordingly
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
    
    //Precondition - t != null, there are enough coin for an upgrade
    //Postcondition - if there are enough coins, t is upgraded
    public void upgradeTurret(Turret t)
    {
        if(coins >= t.getUpgradeCost())
        {
            coins -= t.getUpgradeCost();
            t.upgradeDamage();
        }
    }
    
    //Postcondition - adds to coins the reward for killing e
    public void getKillCoins(Enemy e)
    {
        coins += e.getKillCost();
    }
    
    //Postcondition - enemy count is scaled, as is enemy health
    public void scaleEnemies()
    {
        QuickEnemy.HEALTH *= 1.15;
        BaseEnemy.HEALTH *= 1.15;
        SlowEnemy.HEALTH *= 1.15;
        enemyCount = (int)(1.5 * round);
    }
    
    //Postcondition - the enemy list is passed into every Turrets fire method so a Turret can fire if it is able to
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
    
    //Postcondition - every bullet is moved towards its enemy
    public void moveBullets()
    {
        for(Bullet b : bullets)
        {
            b.move();
        }
    }
    
    //Postcondition - for every Enemy, if it can move, and there is a space to move to, the enemy moves and the grid is updated
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
    
    //Postcondition - if a bullets has reached an enemy, it damages the enemy and changes the bullet gamestate
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
    
    //Postcondition - removes any Bullet without an active enemy and any enemy with 0 health
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
    
    //Postcondition - returns whether an enemy has reached the base
    public boolean baseHasBeenBreached()
    {
        for(int e = enemies.size() - 1; e >= 0; e--)
        {
            for(int i = base.getPosition().getRow(); i < base.getPosition().getRow() + base.getLength(); i++)
            {
                for(int j = base.getPosition().getCol(); j < base.getPosition().getCol() + base.getWidth(); j++)
                {
                    if(enemies.get(e).getPosition().equals(grid.getGameGrid()[i][j]))
                    {
                        grid.setFilled(enemies.get(e).getPosition(), null);
                        enemies.remove(e);
                        return true;
                    }
                }
            }
        }
        return false;
    }

}

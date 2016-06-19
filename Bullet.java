//Bullet - The Bullet Class creates, draws and moves the projectile Turrets shoot

import java.awt.*;
import javax.swing.*;

public class Bullet extends Entity
{
    //Private Fields
    private int damage;
    private int gameState = 0;
    private Enemy e;
    private Position pos;
    private Image bullet;
    private Point p = new Point();

    //Constants
    private final int PIXELS = 12;    
    
    //Constructors
    public Bullet(Enemy enemy, int d)
    {
        e = enemy;
        damage = d;
        bullet = new ImageIcon("resources/newBullet.png").getImage();
    }
    
    //Mutators    
    public void changeGameState()
    {
        gameState = 1;
    }
    
    public void setPoint(Position pos)
    {
        p.setLocation(new Point(pos.getXCoordinate() - Grid.POSITION_SIZE / 2, pos.getYCoordinate() - Grid.POSITION_SIZE / 2));
    }
    
    public void setPosition(Position p)
    {
        pos = p;
        setPoint(pos);
    }
    
    //Accessors
    public Enemy getEnemy()
    {
        return e;
    }
    
    public int getGameState()
    {
        return gameState;
    }
    
    public Position getPosition()
    {
        return pos;
    }
    
    //Methods
    
    //Postcondition - Takes the Enemy and changes its health based on the Bullet's damage
    //                Changes the gameState to 1
    public void damageEnemy(Enemy enemy)
    {
        enemy.changeHealth(damage);
        changeGameState();
    }
    
    //Postcondition - Draws the bullet on the grid    
    public void draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g2d.drawImage(bullet, (int)p.getX() - Grid.POSITION_SIZE / 4, (int)p.getY() - Grid.POSITION_SIZE / 4, (int)getPosition().getWidth() / 2, (int)getPosition().getHeight() / 2, null);
    }
    
    //Postcondition - Checks if the Bullet and Enemy are in the same position, meaning they have collided
    public boolean hasCollided(Enemy e)
    {
        if(e.getPosition().contains(p))
            return true;
        return false;
    }
    
    //Postcondition - Moves the Bullet 12 pixels towards the Enemy based on the slope between the Bullet and Enemy
    public void move()
    {
        int slopeX = (int)(e.getXCoord() + Grid.POSITION_SIZE / 2 - p.getX());
        int slopeY = (int)(e.getYCoord() + Grid.POSITION_SIZE / 2 - p.getY());
 
        if(slopeX > 0)
        {
            moveX(PIXELS);
        }
        else if(slopeX < 0)
        {
            moveX(-1 * PIXELS);
        }
        
        if(slopeY > 0)
        {
            moveY(PIXELS);
        }
        else if(slopeY < 0)
        {
            moveY(-1 * PIXELS);
        }
    }
    
    //Postcondition - Moves the X-Coordinate of the Bullet by num pixels
    public void moveX(int num)
    {
       p.translate(num, 0);
    }

    //Postcondition - Moves the Y-Coordinate of the Bullet by num pixels
    public void moveY(int num)
    {
       p.translate(0, num);
    }
}

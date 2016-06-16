import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import javax.swing.*;

public class Bullet extends Entity
{
    private Enemy e;
    private Point p = new Point();
    private int damage;
    private int gameState = 0;
    private final int PIXELS = 4;
    private Position pos;
    private Image bullet;
    
    public Bullet(Enemy enemy, int d)
    {
        e = enemy;
        damage = d;
        bullet = new ImageIcon("newBullet.png").getImage();
    }
    
    public void changeGameState()
    {
        gameState = 1;
    }
    
    public int getGameState()
    {
        return gameState;
    }
    
    public Enemy getEnemy()
    {
        return e;
    }
    
    public void setPosition(Position p)
    {
        pos = p;
        setPoint(pos);
    }
    
    public void setPoint(Position pos)
    {
        p.setLocation(new Point(pos.getXCoordinate() - Grid.POSITION_SIZE / 2, pos.getYCoordinate() - Grid.POSITION_SIZE / 2));
    }
    
    public void moveX(int num)
    {
       p.translate(num, 0);
    }

    public void moveY(int num)
    {
       p.translate(0, num);
    }
    
    public Position getPosition()
    {
        return pos;
    }
 
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
    
    public void draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g2d.drawImage(bullet, (int)p.getX() - Grid.POSITION_SIZE / 4, (int)p.getY() - Grid.POSITION_SIZE / 4, (int)getPosition().getWidth() / 2, (int)getPosition().getHeight() / 2, null);
    }
    
    public boolean hasCollided(Enemy e)
    {
//         if(p.getX() == e.getXCoordinate() && p.getY() == (e.getYCoordinate()))
//         {
//            return true;
//         }
        if(e.getPosition().contains(p))
            return true;
        return false;
    }
    
    public void damageEnemy(Enemy enemy)
    {
        enemy.changeHealth(damage);
        changeGameState();
    }
}

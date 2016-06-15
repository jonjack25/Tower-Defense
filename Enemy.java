import java.awt.Graphics;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Enemy extends Entity
{
    private boolean canMove = true;
    private Position pos;
    private Timer moving;
    private int health;
    private int killCost;
    private int speed;
    private GamePath path;
    private Image image;
    private int xCoord, yCoord;
    
    public Enemy(Position p)
    {
        pos = p;
        path = new GamePath();
        xCoord = p.getXCoordinate();
        yCoord = p.getYCoordinate();
    }
    
    public void draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g2d.drawImage(image, getXCoord(), getYCoord(), (int)getPosition().getWidth(), (int)getPosition().getHeight(), null);
    }
    
    public int getHealth()
    {
        return health;
    }
    
    public int getKillCost()
    {
        return killCost;
    }
    
    public int getXCoord()
    {
        return xCoord;
    }
    
    public int getYCoord()
    {
        return yCoord;
    }
    
    public void setCoordinates(Point p)
    {
        xCoord = (int)p.getX();
        yCoord = (int)p.getY();
    }
    
    public int getSpeed()
    {
        return speed;
    }
    
    public void setImage(Image i)
    {
        image = i;
    }
    
    public void setKillCost(int k)
    {
        killCost = k;
    }
    
    public void setPosition(Position p)
    {
        pos = p;
        setCoordinates(new Point(p.getXCoordinate(), p.getYCoordinate()));
    }
    
    public void setHealth(int h)
    {
        health = h;
    }
    
    public Position getPosition()
    {
        return pos;
    }
    
    public GamePath getPath()
    {
        return path;
    }
        
    public int getXCoordinate()
    {
        return pos.getXCoordinate();
    }
    
    public int getYCoordinate()
    {
        return pos.getYCoordinate();
    }
    
    public void changeHealth(int damage)
    {
        health -= damage;
    }
    
    public void setMoveTime(int speed)
    {
        moving = new Timer(speed, new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                canMove = true;
            }
        });
        moving.setInitialDelay(0);
        moving.start();
    }
    
    public Point move()
    {
        if(canMove)
        {
            canMove = false;
            return path.getNextMove(pos);
        }
        return null;
    }
}

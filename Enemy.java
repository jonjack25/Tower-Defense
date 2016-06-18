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
    
    private int fullHealth;
    
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
        if(path.getAxis() == 1 && path.getDirection() == -1)
        {
            g2d.drawImage(image, getXCoord() + (int)getPosition().getWidth() + 15, getYCoord() - 15, -((int)getPosition().getWidth() + 15), (int)getPosition().getHeight() + 15, null);
        }
        else
            g2d.drawImage(image, getXCoord(), getYCoord() - 15, (int)getPosition().getWidth() + 15, (int)getPosition().getHeight() + 15, null);
        
        if(health != fullHealth)
        {
            g.setColor(new Color(0, 0, 0));
            g.drawRect(getXCoord() + 0, getYCoord() - 5 - 15, 25, 5);
            g.setColor(new Color(255, 0, 0));
            g.fillRect(getXCoord() + 0, getYCoord() - 5 - 15, 25, 5);
            //g.fillRect(getXCoord() + 0, getYCoord() - 5, 25 * (int)(health / fullHealth), 5);
            //g.setColor(new Color(255, 0, 0));
            //g.fillRect(getXCoord() + 25 * (int)(health / fullHealth), getYCoord() - 5, 25 - 25 * (int)(health / fullHealth), 5);
            g.setColor(new Color(0, 255, 0));
            g.fillRect(getXCoord() + 0, getYCoord() - 5 - 15, 25 * health / fullHealth, 5);
        }
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
        fullHealth = h;
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

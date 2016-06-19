//Enemy - The Enemy Class creates a draws the enemies for the game

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Enemy extends Entity
{
    //Private Fields
    private int speed;
    private int xCoord;
    private int yCoord;  
    private int health;
    private int killCost;
    private int fullHealth;    
    private boolean canMove = true;
    private Image image;
    private Timer moving;
    private Position pos;
    private GamePath path;
    
    //Constructors
    public Enemy(Position p)
    {
        pos = p;
        path = new GamePath();
        xCoord = p.getXCoordinate();
        yCoord = p.getYCoordinate();
    }
    
    //Mutators
    public void setCoordinates(Point p)
    {
        xCoord = (int)p.getX();
        yCoord = (int)p.getY();
    }
    
    public void setHealth(int h)
    {
        health = h;
        fullHealth = h;
    }
    
    public void setImage(Image i)
    {
        image = i;
    }
    
    public void setKillCost(int k)
    {
        killCost = k;
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
    
    public void setPosition(Position p)
    {
        pos = p;
        setCoordinates(new Point(p.getXCoordinate(), p.getYCoordinate()));
    }
    
    //Accessors
    public int getHealth()
    {
        return health;
    }
    
    public int getKillCost()
    {
        return killCost;
    }
    
    public GamePath getPath()
    {
        return path;
    }
    
    public Position getPosition()
    {
        return pos;
    }
    
    public int getSpeed()
    {
        return speed;
    }
    
    public int getXCoord()
    {
        return xCoord;
    }
        
    public int getXCoordinate()
    {
        return pos.getXCoordinate();
    }
    
    public int getYCoord()
    {
        return yCoord;
    }
    
    public int getYCoordinate()
    {
        return pos.getYCoordinate();
    }
    
    //Methods
    
    //Postcondition - Adjusts the Enemy's health based on the Turret's damage
    public void changeHealth(int damage)
    {
        health -= damage;
    }
    
    //Postcondition - Enemy is drawn on the path
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
            g.setColor(new Color(0, 255, 0));
            g.fillRect(getXCoord() + 0, getYCoord() - 5 - 15, 25 * health / fullHealth, 5);
        }
    }
    
    //Postcondition - Moves the Enemy to the next point on the path
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

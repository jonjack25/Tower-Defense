import java.awt.Graphics;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Enemy extends Entity
{
    private boolean canMove = true;
    private Position pos;
    private Timer moving;
    private int health;
    private int killCost;
    private GamePath path;
    
    public Enemy(Position p)
    {
        pos = p;
    }
    
    public abstract void draw(Graphics g);
    
    public int getHealth()
    {
        return health;
    }
    
    public int getKillCost()
    {
        return killCost;
    }
    
    public void setPosition(Position p)
    {
        pos = p;
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
        moving.start();
        moving = new Timer(speed, new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                canMove = true;
            }
        });
    }
    
    public Point move()
    {
        if(canMove)
        {
            return path.getNextMove(pos);
        }
        return null;
    }
}
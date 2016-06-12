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
    private GamePath path;
    
    public Enemy(Position p)
    {
        pos = p;
        moving.start();
    }
    
    public abstract int getSpeed();
    public abstract int getHealth();
    public abstract int getKillCost();
    public abstract void draw(Graphics g);
    
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
    
    public void setMoveTime(int speed)
    {
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
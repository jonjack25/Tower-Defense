import java.awt.Graphics;
import java.util.List;
import java.awt.Point;
import javax.swing.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class Turret extends Entity
{
    private int radius = 150;
    private boolean canFire = true;
    private Position pos;
    private Timer reload;
    private Enemy fireAt;
    private int upgrade;
    private int upgradeCost = 50;
    private int damage;
    private int speed;
    private Image image;
    
    public Turret()
    {
        
    }

    public Turret(Position p)
    {
        pos = p;
    }
    
    public int getReloadSpeed()
    {
        return speed;
    }
    
    public void draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        //g2d.rotate(Math.toRadians(getAngle()));
        //fireAt = null;
        g2d.drawImage(image, getPosition().getXCoordinate() - (int)getPosition().getWidth() * 2, getPosition().getYCoordinate() - (int)getPosition().getWidth() * 2, (int)getPosition().getWidth() * 3, (int)getPosition().getHeight() * 3, null);
    }
    
    private double getAngle()
    {
        if(fireAt == null)
            return 0;
        else
        {
            double x1 = pos.getXCoordinate() - pos.getWidth() / 2, 
                   y1 = pos.getYCoordinate() - pos.getWidth() / 2;
            double x2 = fireAt.getPosition().getXCoordinate() + fireAt.getPosition().getWidth() / 2,
                   y2 = fireAt.getPosition().getYCoordinate() + fireAt.getPosition().getWidth() / 2;
            return Math.atan2(y2 - y1, x2 - x1);
        }
    }
    
    public void setImage(Image i)
    {
        image = i;
    }
    
    public int getDamage()
    {
        return damage;
    }
    
    public void setDamage(int d)
    {
        damage = d;
    }
    
    public void setUpgrade(int u)
    {
        upgrade = u;
    }
    
    public void setPosition(Position p)
    {
        pos = p;
    }
    
    public int getUpgradeCost()
    {
        return upgradeCost;
    }
    
    public Position getPosition()
    {
        return pos;
    }
    
    public int getRadius()
    {
        return radius;
    }
    
    public void upgradeDamage()
    {
        damage += upgrade;
        upgradeCost = (int)(upgradeCost * 1.5);
    }
    
    public void setTimer(int s)
    {
        speed = s;
        reload = new Timer(s, new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                canFire = true;
            }
        });
    }
    
    public Bullet fire(List<Enemy> e)
    {
        double distance = 0;
        int random = (int)(Math.random());
        if(canFire)
        {
            for(int i = 0; i < e.size(); i++)
            {
                distance = Math.sqrt(Math.pow(e.get(i).getXCoord() - pos.getXCoordinate(), 2) + Math.pow(e.get(i).getYCoord() - pos.getYCoordinate(), 2));        
                if(radius >= distance)
                {
                    fireAt = e.get(i);
                    Bullet b = new Bullet(e.get(i), damage);
                    canFire = false;
                    reload.restart();
                    return b;
                }
            }
        }
        return null;
    }
}

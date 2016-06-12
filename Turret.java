import java.awt.Graphics;
import java.util.List;
import java.awt.Point;
import javax.swing.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Turret extends Entity
{
    private int radius = 1;
    private boolean canFire = true;
    private Position pos;
    private Timer reload;
    private Enemy fireAt;
    private int upgrade;
    public int upgradeCost = 50;
    private int damage;
    
    public Turret(int d)
    {
        damage = d;
        setUpgrade();
    }

    public Turret(Position p, int d)
    {
        pos = p;
        damage = d;
        setUpgrade();
    }
    
    public abstract int getReloadSpeed();
    public abstract void draw(Graphics g);
    
    public int getDamage()
    {
        return damage;
    }
    
    public void setUpgrade()
    {
        upgrade = 50;
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
    
    public void setTimer(int speed)
    {
        reload = new Timer(speed, new ActionListener()
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
        if(canFire)
        {
            for(int i = 0; i < e.size(); i++)
            {
                distance = Math.sqrt(Math.pow(e.get(i).getXCoordinate() - pos.getXCoordinate(), 2) + Math.pow(e.get(i).getYCoordinate() - pos.getYCoordinate(), 2));        
                if(radius >= distance)
                {
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

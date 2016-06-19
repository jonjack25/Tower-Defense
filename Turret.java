//Turret - The Turret Class creates and draws Turrets used in the game

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.Timer;

public class Turret extends Entity
{
    //Private Fields
    private int speed;
    private int damage;
    private int upgrade;
    private int radius = 150;
    private int upgradeCost = 50;
    private boolean canFire = true;
    private Image image;
    private Enemy fireAt;
    private Timer reload;    
    private Position pos;
    
    //Constructors
    public Turret()
    {
        
    }

    public Turret(Position p)
    {
        pos = p;
    }
    
    //Mutators
    public void setDamage(int d)
    {
        damage = d;
    }
    
    public void setImage(Image i)
    {
        image = i;
    }
    
    public void setPosition(Position p)
    {
        pos = p;
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
    
    public void setUpgrade(int u)
    {
        upgrade = u;
    }
    
    //Accessors
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
    
    public int getDamage()
    {
        return damage;
    }
    
    public Position getPosition()
    {
        return pos;
    }
    
    public int getRadius()
    {
        return radius;
    }
    
    public int getReloadSpeed()
    {
        return speed;
    }
    
    public int getUpgrade()
    {
        return upgrade;
    }
    
    public int getUpgradeCost()
    {
        return upgradeCost;
    }
    
    //Methods
    
    //Postcondition - Turret is drawn and rotated at an angle towards the Enemy
    public void draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g2d.rotate(getAngle() - 165, pos.getXCoordinate() - pos.getWidth() / 2, pos.getYCoordinate() - pos.getWidth() / 2);
        g2d.drawImage(image, getPosition().getXCoordinate() - (int)getPosition().getWidth() * 2, getPosition().getYCoordinate() - (int)getPosition().getWidth() * 2, (int)getPosition().getWidth() * 3, (int)getPosition().getHeight() * 3, null);
    }
    
    //Postcondition - Locates an Enemy within the Turret's radius and creates a new Bullet to fire at a specified Enemy
    //                The reload Timer is reset
    public Bullet fire(List<Enemy> e)
    {
        double distance = 0;
        if(canFire)
        {
            for(int i = 0; i < e.size(); i++)
            {
                distance = Math.sqrt(Math.pow(e.get(i).getXCoord() + Grid.POSITION_SIZE / 2 - (pos.getXCoordinate() - Grid.POSITION_SIZE / 2), 2) + Math.pow(e.get(i).getYCoord() + Grid.POSITION_SIZE / 2 - (pos.getYCoordinate() - Grid.POSITION_SIZE / 2), 2));        
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
    
    //Postcondition - The damage of the Turret is upgraded and the cost to upgrade increases
    public void upgradeDamage()
    {
        damage += upgrade;
        upgradeCost = (int)(upgradeCost * 1.5);
    }
}

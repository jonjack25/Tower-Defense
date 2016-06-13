import java.awt.Graphics;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class FastTurret extends Turret
{
    private int upgrade = 5;
    private final int reload;
    public static final int PURCHASE_COST = 300;
    private Enemy fireAt;
    Image fast;
    
    public FastTurret(Position p)
    {
        super(p, 75);
        reload = 250;
        setTimer(reload);
        fast = new ImageIcon("circleTurretGreen.png").getImage();
    }
    
    public int getReloadSpeed()
    {
        return reload;
    }
    
    public void upgrade()
    {
        upgradeDamage(upgrade);
    }
    
    public int getUpgradeCost()
    {
        return 0;
    }
    
    public void draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g2d.drawImage(fast, getPosition().getXCoordinate(), getPosition().getYCoordinate(), (int)getPosition().getWidth(), (int)getPosition().getHeight(), null);        
    }
}
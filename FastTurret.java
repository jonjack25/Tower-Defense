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
    private final int RELOAD = 1000;
    public static final int DAMAGE = 100;
    private final int UPGRADE = 10;
    public static final int PURCHASE_COST = 300;
    private final Image FAST = new ImageIcon("circleTurretGreen.png").getImage();
    
    public FastTurret()
    {
        super();
        setTimer(RELOAD);
        setDamage(DAMAGE);
        setUpgrade(UPGRADE);
        setImage(FAST);
    }
    
    public FastTurret(Position p)
    {
        super(p);
        setTimer(RELOAD);
        setDamage(DAMAGE);
        setUpgrade(UPGRADE);
        setImage(FAST);
    }
}

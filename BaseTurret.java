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

public class BaseTurret extends Turret
{
    private final int RELOAD = 2000;
    private final int DAMAGE = 250;
    private final int UPGRADE = 20;
    public static final int PURCHASE_COST = 200;
    private Enemy fireAt;
    private final Image BASE = new ImageIcon("circleTurretRed.png").getImage();
    
    public BaseTurret()
    {
        super();
        setDamage(DAMAGE);
        setTimer(RELOAD);
        setUpgrade(UPGRADE);
        setImage(BASE);
    }
    
    public BaseTurret(Position p)
    {
        super(p);
        setTimer(RELOAD);
        setDamage(DAMAGE);
        setUpgrade(UPGRADE);
        setImage(BASE);
    }
}
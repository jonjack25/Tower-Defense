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

public class StrongTurret extends Turret
{
    private final int RELOAD = 4000;
    private final int DAMAGE = 500;
    private final int UPGRADE = 30;
    public static final int PURCHASE_COST = 500;
    private Enemy fireAt;
    private final Image STRONG = new ImageIcon("circleTurretBlue.png").getImage();
    
    public StrongTurret()
    {
        super();
        setTimer(RELOAD);
        setDamage(DAMAGE);
        setUpgrade(UPGRADE);
        setImage(STRONG);
    }
    
    public StrongTurret(Position p)
    {
        super(p);
        setTimer(RELOAD);
        setDamage(DAMAGE);
        setUpgrade(UPGRADE);
        setImage(STRONG);
    }
}
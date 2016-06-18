import java.awt.*;
import javax.swing.*;
import java.io.*;

public class StrongTurret extends Turret
{
    //CONSTANTS
    private final int RELOAD = 4000;
    public static final int DAMAGE = 500;
    private final int UPGRADE = 30;
    public static final int PURCHASE_COST = 500;
    private final Image STRONG = new ImageIcon("resources/circleTurretBlue.png").getImage();
    
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
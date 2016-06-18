import java.awt.*;
import javax.swing.*;
import java.io.*;

public class BaseTurret extends Turret
{
    //CONSTANTS
    private final int RELOAD = 2000;
    public static final int DAMAGE = 250;
    private final int UPGRADE = 20;
    public static final int PURCHASE_COST = 300;
    private final Image BASE = new ImageIcon("resources/circleTurretRed.png").getImage();
    
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
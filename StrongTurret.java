//StrongTurret - Initializes the values and timer for a StrongTurret

import javax.swing.*;
import java.awt.*;

public class StrongTurret extends Turret
{
    //Constants
    private final int UPGRADE = 30;
    private final int RELOAD = 4000;
    public static final int DAMAGE = 500;
    public static final int PURCHASE_COST = 500;
    private final Image STRONG = new ImageIcon("resources/circleTurretBlue.png").getImage();
    
    //Constructors
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

//BaseTurret - Initializes the values and timer for a BaseTurret

import javax.swing.*;
import java.awt.*;

public class BaseTurret extends Turret
{
    //Constants
    private final int UPGRADE = 20;
    private final int RELOAD = 2000;
    public static final int DAMAGE = 250;
    public static final int PURCHASE_COST = 250;
    private final Image BASE = new ImageIcon("resources/circleTurretRed.png").getImage();
    
    //Constructors
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

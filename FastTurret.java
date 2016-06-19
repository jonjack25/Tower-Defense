//FastTurret - Initializes the values and timer for a FastTurret

import javax.swing.*;
import java.awt.*;

public class FastTurret extends Turret
{
    //Constants
    private final int UPGRADE = 10;
    private final int RELOAD = 1000;
    public static final int DAMAGE = 125;
    public static final int PURCHASE_COST = 700;
    private final Image FAST = new ImageIcon("resources/circleTurretGreen.png").getImage();
    
    //Constructors
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

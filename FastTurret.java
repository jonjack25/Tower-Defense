import java.awt.*;
import javax.swing.*;
import java.io.*;

public class FastTurret extends Turret
{
    //COSTANTS
    private final int RELOAD = 1000;
    public static final int DAMAGE = 125;
    private final int UPGRADE = 10;
    public static final int PURCHASE_COST = 700;
    private final Image FAST = new ImageIcon("resources/circleTurretGreen.png").getImage();
    
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
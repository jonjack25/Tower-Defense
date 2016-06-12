import java.awt.Graphics;

public class FastTurret extends Turret
{
    private int upgrade = 5;
    private final int reload;
    public static final int PURCHASE_COST = 300;
    private Enemy fireAt;
    
    public FastTurret()
    {
        super(75);
        reload = 250;
        setTimer(reload);
    }
    
    public FastTurret(Position p)
    {
        super(p, 75);
        reload = 250;
        setTImer(reload);
    }
    
    public int getReloadSpeed()
    {
        return reload;
    }
    
    public void upgrade()
    {
        upgradeDamage(upgrade);
    }
    
    public void draw(Graphics g)
    {
        
    }
}

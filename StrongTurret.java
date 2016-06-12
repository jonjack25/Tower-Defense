import java.awt.Graphics;

public class StrongTurret extends Turret
{
    private int upgrade = 25;
    private final int reload;
    public static final int PURCHASE_COST = 500;
    private Enemy fireAt;
    
    public StrongTurret()
    {
        super(500);
        reload = 2000;
        setTimer(reload);
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

import java.awt.Graphics;

public class BaseTurret extends Turret
{
    private int upgrade = 15;
    private final int reload;
    public static final int PURCHASE_COST = 400;
    private Enemy fireAt;
    
    public BaseTurret()
    {
        super(200);
        reload = 1000;
        setTimer(reload);
    }
    
    public BaseTurret(Position p)
    {
        super(p, 200);
        reload = 1000;
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

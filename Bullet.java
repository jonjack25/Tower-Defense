import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends Entity
{
    private Enemy e;
    private Position pos;
    private final int speed = 4;
    private int damage;
    
    public Bullet(Enemy enemy, int d)
    {
        e = enemy;
        damage = d;
    }
    
    public void setPosition(Position p)
    {
        pos = p;
    }
    
    public Position getPosition()
    {
        return pos;
    }
            
    public void move()
    {
        //moveTowardsEnemy
    }
    
    public void draw(Graphics g)
    {
        
    }
    
    public boolean hasCollided()
    {
        return true;
    }
    
    public void damage()
    {
        
    }
}
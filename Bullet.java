import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
public class Bullet extends Entity
{
    private Enemy e;
    private Position pos;
    private int damage;
    private int gameState = 0;
    
    public Bullet(Enemy enemy, int d)
    {
        e = enemy;
        damage = d;
    }
    
    public void changeGameState()
    {
        gameState = 1;
    }
    
    public void setPosition(Position p)
    {
        pos = p;
    }
    
    public Position getPosition()
    {
        return pos;
    }
            
    public Point move()
    {
        int xTarget = -1;
        int yTarget = -1;
        int rise = e.getPosition().getRow() - pos.getRow();        
        int run = e.getPosition().getCol() - pos.getCol();
        if(run == 0 && rise == 0)
            return null;
        if(run == 0)
        {
            yTarget = pos.getCol();
            xTarget = pos.getRow() + rise / Math.abs(rise);
        }
        
        else if(rise == 0)
        {
            xTarget = pos.getRow();
            yTarget = pos.getCol() + run / Math.abs(run);
        }
        
        else
        {
            yTarget = pos.getCol() + run / Math.abs(run);
            xTarget = pos.getRow() + (int)(rise / run);
        }
        
        return new Point(xTarget, yTarget);
    }
    
    public void draw(Graphics g)
    {
        
    }
    
    public boolean hasCollided(Enemy e)
    {
        if(pos.equals(e.getPosition()))
        {
            return true;
        }
        return false;
    }
    
    public void damageEnemy(Enemy enemy)
    {
        e.changeHealth(damage);
        changeGameState();
    }
    
    public int getGameState()
    {
        return gameState;
    }
}
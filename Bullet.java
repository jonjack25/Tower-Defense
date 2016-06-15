import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import javax.swing.*;

public class Bullet extends Entity
{
    private Enemy e;
    private Position pos;
    private final int speed = 4;
    private int damage;
    private int gameState = 0;
    Image bullet;
    
    public Bullet(Enemy enemy, int d)
    {
        e = enemy;
        damage = d;
        bullet = new ImageIcon("bullet.png").getImage();
    }
    
    public void changeGameState()
    {
        gameState = 1;
    }
    
    public int getGameState()
    {
        return gameState;
    }
    
    public void setPosition(Position p)
    {
        pos = p;
    }
    
    public Position getPosition()
    {
        return pos;
    }
            
    public Position move()
    {
        return e.getPosition();
    }
    
    public void draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g2d.drawImage(bullet, getPosition().getXCoordinate(), getPosition().getYCoordinate(), (int)getPosition().getWidth(), (int)getPosition().getHeight(), null);
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
        enemy.changeHealth(damage);
        changeGameState();
    }
}
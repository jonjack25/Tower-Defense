import java.awt.Graphics;

public class BaseEnemy extends Enemy
{
   private int health;
   private int killCost;
   private final int speed;
   public static int HEALTH = 150;
   
   public BaseEnemy(Position p)
   {
       super(p);
       speed = 500;
       setMoveTime(speed);
   }
   
   public void setHealth()
   {
       health = HEALTH;
   }
   
   public void setKillCost()
   {
       killCost = 150;
   }
   
   public void draw(Graphics g)
   {
       
   }
}

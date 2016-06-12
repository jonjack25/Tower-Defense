import java.awt.Graphics;

public class QuickEnemy extends Enemy
{
   private int health;
   private int killCost;
   private final int speed;
   public static int HEALTH = 50;
   
   public QuickEnemy(Position p)
   {
       super(p);
       speed = 250;
       setMoveTime(speed);
   }
   
   public void setHealth()
   {
       health = HEALTH;
   }
   
   public void setKillCost()
   {
       killCost = 100;
   }
   
   public void draw(Graphics g)
   {
       
   }
}

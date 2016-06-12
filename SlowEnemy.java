import java.awt.Graphics;

public class SlowEnemy extends Enemy
{
   private int health;
   private int killCost;
   private final int speed;
   public static int HEALTH = 400;

   public SlowEnemy(Position p)
   {
       super(p);
       speed = 1000;
       setMoveTime(speed);
   }
   
   public void setHealth()
   {
       health = HEALTH;
   }
   
   public void setKillCost()
   {
       killCost = 200;
   }
       
   public void draw(Graphics g)
   {
       
   }
}

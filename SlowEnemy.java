import java.awt.Graphics;

public class SlowEnemy extends Enemy
{
   private int killCost;
   private final int speed;
   public static int HEALTH = 50;
   private int health;

   public SlowEnemy(Position p)
   {
       super(p);
       speed = 1000;
       setMoveTime(speed);
       health = HEALTH;
       killCost = 200;
   }
   
   public int getHealth()
   {
       return health;
   }
   
   public int getKillCost()
   {
       return killCost;
   }
   
   public int getSpeed()
   {
       return speed;
   }
   
   public void changeHealth(int damage)
   {
        health -= damage;
   }
   
   public void draw(Graphics g)
   {
       
   }
}
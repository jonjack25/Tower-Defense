import java.awt.Graphics;

public class QuickEnemy extends Enemy
{
   private int killCost;
   private final int speed;
   public static int HEALTH = 400;
   private int health;
   
   public QuickEnemy(Position p)
   {
       super(p);
       speed = 250;
       setMoveTime(speed);
       health = HEALTH;
       killCost = 100;
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
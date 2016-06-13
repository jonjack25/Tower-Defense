import java.awt.Graphics;

public class BaseEnemy extends Enemy
{
   private int killCost = 10;
   private final int speed;
   public static int HEALTH = 150;
   private int health;
   
   public BaseEnemy(Position p)
   {
       super(p);
       speed = 500;
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
        health -= damage;;
   }
   
   public void draw(Graphics g)
   {
       
   }
}
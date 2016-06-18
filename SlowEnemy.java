import java.awt.*;
import javax.swing.*;

public class SlowEnemy extends Enemy
{
   //CONSTANTS
   private final int SPEED = 500;
   public static int HEALTH = 500;
   public static int KILL_COST = 35;
   private final Image SLOW = new ImageIcon("resources/slowenemy.png").getImage();

   public SlowEnemy(Position p)
   {
       super(p);
       setMoveTime(SPEED);
       setHealth(HEALTH);
       setKillCost(KILL_COST);
       setImage(SLOW);
   }
}
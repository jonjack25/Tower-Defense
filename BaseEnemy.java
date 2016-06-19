//BaseEnemy - Initializes the values and timer for a BaseEnemy

import java.awt.*;
import javax.swing.*;

public class BaseEnemy extends Enemy
{
   //Constants
   private final int SPEED = 300;
   public static int HEALTH = 250;
   private final int KILL_COST = 30;
   private final Image BASE = new ImageIcon("resources/baseenemy.png").getImage();

   //Constructors
   public BaseEnemy(Position p)
   {
       super(p);
       setMoveTime(SPEED);
       setHealth(HEALTH);
       setKillCost(KILL_COST);
       setImage(BASE);
   }
}

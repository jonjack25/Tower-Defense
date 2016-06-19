//QuickEnemy - Initializes the values and timer for a QuickEnemy

import java.awt.*;
import javax.swing.*;

public class QuickEnemy extends Enemy
{
   //Constants
   private final int SPEED = 150;
   public static int HEALTH = 100;
   private final int KILL_COST = 25;
   private final Image QUICK = new ImageIcon("resources/fastenemy.png").getImage();

   //Constructors
   public QuickEnemy(Position p)
   {
       super(p);
       setMoveTime(SPEED);
       setHealth(HEALTH);
       setKillCost(KILL_COST);
       setImage(QUICK);
   }
}

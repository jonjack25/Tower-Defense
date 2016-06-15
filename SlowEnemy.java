
import java.awt.Graphics;
import java.awt.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class SlowEnemy extends Enemy
{
   private final int SPEED = 1000;
   public static int HEALTH = 1000;
   private final int KILL_COST = 100;
   private final Image SLOW = new ImageIcon("slowenemy.png").getImage();

   public SlowEnemy(Position p)
   {
       super(p);
       setMoveTime(SPEED);
       setHealth(HEALTH);
       setKillCost(KILL_COST);
       setImage(SLOW);
   }
}

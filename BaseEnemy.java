import java.awt.Graphics;
import java.awt.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class BaseEnemy extends Enemy
{
   private final int SPEED = 500;
   public static int HEALTH = 500;
   private final int KILL_COST = 200;
   private final Image BASE = new ImageIcon("basenemy.png").getImage();

   public BaseEnemy(Position p)
   {
       super(p);
       setMoveTime(SPEED);
       setHealth(HEALTH);
       setKillCost(KILL_COST);
       setImage(BASE);
   }
}
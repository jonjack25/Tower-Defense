import java.awt.Graphics;
import java.awt.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class BaseEnemy extends Enemy
{
   private final int SPEED = 300;
   public static int HEALTH = 250;
   public static int KILL_COST = 30;
   private final Image BASE = new ImageIcon("resources/baseenemy.png").getImage();

   public BaseEnemy(Position p)
   {
       super(p);
       setMoveTime(SPEED);
       setHealth(HEALTH);
       setKillCost(KILL_COST);
       setImage(BASE);
   }
}
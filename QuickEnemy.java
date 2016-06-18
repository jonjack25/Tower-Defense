import java.awt.Graphics;
import java.awt.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class QuickEnemy extends Enemy
{
   private final int SPEED = 150;
   public static int HEALTH = 100;
   public static int KILL_COST = 25;
   private final Image QUICK = new ImageIcon("resources/fastenemy.png").getImage();

   public QuickEnemy(Position p)
   {
       super(p);
       setMoveTime(SPEED);
       setHealth(HEALTH);
       setKillCost(KILL_COST);
       setImage(QUICK);
   }
}
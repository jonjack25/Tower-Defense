import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javafx.scene.image.*;

public class GameScreen extends JPanel implements Runnable
{
    public Thread gameLoop = new Thread(this);
    public boolean first = true;
    public static int myWidth, myHeight;
    public static Room room;
    public static Point mouse = new Point(0, 0);
    public static TurretBar tb;
    public static Image[] images = new Image[100];

         
    public GameScreen(GameFrame f)
    {
        f.addMouseListener(new KeyHandler());
        f.addMouseMotionListener(new KeyHandler());
        gameLoop.start();
    }
    
    public void paintComponent(Graphics g)
    {
        if(first)
        {
            myWidth = getWidth();
            myHeight = getHeight();
            define();
            first = false;
        }
        /*
        if(KeyHandler.returnStatus() == 1)
        {
            int posX = (int)mouse.getX();
            int posY = (int)mouse.getY();
            */
           /*
            for(int i = 0; i < Room.blocks.length; i++)
            {
                for(int j = 0; j < Room.blocks[0].length; j++)
                {
                    if(Room.blocks[i][j].contains(mouse))
                    {
                        g.setColor(new Color(0, 0, 0));
                        g.fillRect((int)(Room.blocks[i][j].getX() + .5), (int)(Room.blocks[i][j].getY() + .5), (int)(Room.blocks[i][j].getWidth() + .5), (int)(Room.blocks[i][j].getHeight() + .5));
                        //Room.blocks[i][j].draw(g);
                    }
                    else if(!Room.blocks[i][j].contains(mouse))
                    {
                        Room.blocks[i][j].draw(g);
                    }
                }
            }
           /*
            for(int i = 0; i < TurretBar.btnArray.length; i++)
            {
                if(TurretBar.btnArray[i].contains(GameScreen.mouse))
                {
                    g.setColor(new Color(124, 252, 0, 100));
                    g.fillRect(TurretBar.btnArray[i].x, TurretBar.btnArray[i].y, TurretBar.btnArray[i].width, TurretBar.btnArray[i].height);
                }  
            }
            */
      
        
        
            
        g.setColor(new Color(150, 150, 150));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(new Color(0, 0, 0));
        g.drawLine(0, TurretBar.btnStartY, GameFrame.width, TurretBar.btnStartY);
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, TurretBar.btnStartY + 1, GameFrame.width, GameFrame.height);
        g.setColor(new Color(0, 0, 0));
        //g.drawLine(room.blocks[0][0].x - 1, 0, room.blocks[0][0].x - 1, room.blocks[room.worldHeight - 1][0].y + room.blockSize);
        room.draw(g);
        tb.draw(g);
    }
    
    public void run()
    {
        while(true)
        {
            if(!first)
            {
                /*
                for(int i = 0; i < TurretBar.btnArray.length; i++)
                {
                    if(TurretBar.btnArray[i].contains(GameScreen.mouse) && TurretBar.returnStatus() == 1)
                    {
                        repaint();
                    }                    
                }
                */
            }
            repaint();
        }
    }
    
    public void define()
    {
        room = new Room();
        tb = new TurretBar();
        //ground = new ImageIcon("ground.psd").getImage();
        //ground = createImage(new FilteredImageSource(ground.getSource(), new CropImageFilter(0, 104, 104, 104)));
            
        
        for(int i = 0; i < images.length; i++)
        {
            images[i] = new ImageIcon("C:\\Users\\Rithesh\\Documents\\Java\\TowerDefense\\groundPixel.png").getImage();
            images[i] = createImage(new FilteredImageSource(images[i].getSource(), new CropImageFilter(0, 104 * i, 104, 104)));
        }
        
        
    }
}

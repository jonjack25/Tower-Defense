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
    private Thread gameLoop = new Thread(this);
    private boolean first = false;
    private int myWidth, myHeight;

    private Point mousePoint = new Point(0, 0);
    
    
    private TurretBar tb;

    private boolean menu = true;
    private MainMenu mainMenu;
    private Timer timer;
    
    private Game game; 
    public KeyHandler key;
    private GameFrame frame;

         
    public GameScreen(GameFrame f)
    {
        frame = f;
        game = new Game(this);
        key = new KeyHandler(f, game, this);
        
        mainMenu = new MainMenu(this);
        f.addMouseListener(key);
        f.addMouseMotionListener(key);
        setDoubleBuffered(true);
        //timer = new Timer(.1, );
        
        gameLoop.start();
    }
    
    public void paintComponent(Graphics g)
    {
        if(!menu)
        {
            if(first)
            {
                myWidth = getWidth();
                myHeight = getHeight();
                
                tb = new TurretBar(this);
                key.addTB(tb);
                first = false;
            }
            
            g.setColor(new Color(150, 150, 150));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(new Color(0, 0, 0));
            g.drawLine(0, tb.getBtnStartY(), frame.getWidth(), tb.getBtnStartY());
            g.setColor(new Color(255, 255, 255));
            g.fillRect(0, tb.getBtnStartY() + 1, frame.getWidth(), frame.getHeight());
            g.setColor(new Color(0, 0, 0));
            
            if(key.returnStatus() == 1)
            {
                mousePoint = key.getMouse();
                if(game.getGrid().isValidPosition(game.getGrid().getPosition(mousePoint)))
                {
                    
                }
                else
                {
                    
                }
            }
            tb.draw(g);
            game.updateGame();
            game.draw(g);
        }
        else
        {
            myWidth = getWidth();
            myHeight = getHeight();
            
            mainMenu.draw(g);
        }
    }
    
    public void run()
    {
        while(true)
        {
            if(!first)
            {
                

            }
            repaint();
        }
    }
    
    public int getWidth()
    {
        return myWidth;
    }
    
    public int getHeight()
    {
        return myHeight;
    }
    
    public Point getMousePoint()
    {
        return mousePoint;
    }
    
    public void setMousePoint(Point p)
    {
        mousePoint = p;
    }
    
    public boolean getMenu()
    {
        return menu;
    }
    
    public void setMenu(boolean b)
    {
        menu = b;
    }
    
    public GameFrame getFrame()
    {
        return frame;
    }
    
    public TurretBar getTB()
    {
        return tb;
    }
    
    public MainMenu getMainMenu()
    {
        return mainMenu;        
    }
}

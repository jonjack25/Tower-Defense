import javax.swing.*;
import java.awt.*;
public class MainMenu
{
    //private static int width = GameFrame.width;
    //private static int height = GameFrame.height;
    private Rectangle[] menuOptions;
    private Point mouse = new Point(0, 0);
    private int width = 200;
    private int height = 100;
    private GameScreen screen;
    private GameFrame frame;
    
    public MainMenu(GameScreen gs)
    {
        screen = gs;
        frame = screen.getFrame();
        define();
    }
    
    public MainMenu(GameFrame f)
    {
        f.addMouseListener(new KeyHandler(f));
        f.addMouseMotionListener(new KeyHandler(f));
        frame = f;
        define();
    }
    
    public void define()
    {
        menuOptions = new Rectangle[3];
        menuOptions[0] = new Rectangle(frame.getFrameWidth() / 2 - width / 2, 75, width, height);
        menuOptions[1] = new Rectangle(frame.getFrameWidth() / 2 - width / 2, 225, width, height);
        menuOptions[2] = new Rectangle(frame.getFrameWidth() / 2 - width / 2, 375, width, height);
        //setVisible(true);
    }
    
    public void draw(Graphics g)
    {   
        if(screen.getMenu())
        {
            g.setColor(new Color(0, 0, 0));
            screen.getGame().draw(g);
            screen.getFrame().setBackground(Color.BLACK);
            g.setColor(new Color(255, 255, 255));
            g.drawRect(0, 29 * 20, 1024, 768 - 29 * 20);
            g.fillRect(0, 29 * 20, 1024, 768 - 29 * 20);
            g.setColor(new Color(0, 0, 0));
            for(int i = 0; i < menuOptions.length; i++)
            {
                g.drawRect(menuOptions[i].x, menuOptions[i].y, menuOptions[i].width, menuOptions[i].height);
                
                if(menuOptions[i].contains(screen.getMousePoint()))
                {
                    g.setColor(new Color(0, 0, 0, 100));
                    g.fillRect(menuOptions[i].x, menuOptions[i].y, menuOptions[i].width, menuOptions[i].height);
                }
                else
                    g.fillRect(menuOptions[i].x, menuOptions[i].y, menuOptions[i].width, menuOptions[i].height);
                    
                g.setColor(new Color(0, 0, 0));
            }
            g.setColor(new Color(255, 255, 255));
            g.setFont(new Font("Serif", Font.PLAIN, 40));
            g.drawString("Play", frame.getWidth() / 2 - 30, 25 + height );
            g.drawString("Options", frame.getWidth() / 2 - 60, 175 + height);
            g.drawString("Exit", frame.getWidth() / 2 - 30, 325 + height);
        }
    }
    
    public boolean menuBtn1()
    {
        return menuOptions[0].contains(screen.getMousePoint());
    }
    
    public boolean menuBtn2()
    {
        return menuOptions[1].contains(screen.getMousePoint());
    }
    
    public boolean menuBtn3()
    {
        return menuOptions[2].contains(screen.getMousePoint());
    }
}

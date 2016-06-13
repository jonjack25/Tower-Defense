import javax.swing.*;
import java.awt.*;
public class GameFrame extends JFrame
{
    private String title = "Tower Defense Game";
    private int width = 1024, height = 768;
    private Dimension size = new Dimension(width, height);
    public GameFrame()
    {
        setUpGUI();
        setTitle(title);
        setSize(size);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        //runMenu();
        
    }

    public static void main(String args[])
    {
        GameFrame frame = new GameFrame();
    }
    
    public void setUpGUI()
    {
        //remove(m);
        setLayout(new GridLayout(1, 1, 0, 0));
        
        GameScreen gs = new GameScreen(this);
        add(gs);
        //TurretBar tb = new TurretBar();
        
        setVisible(true);
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
}

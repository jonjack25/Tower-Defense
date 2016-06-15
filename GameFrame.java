import javax.swing.*;
import java.awt.*;
public class GameFrame extends JFrame
{
    private String title = "Tower Defense Game";
    private int width = 1024, height = 768;
    private Dimension size;
    public GameFrame()
    {
        size = new Dimension(width, height);
        setTitle(title);
        setSize(size);
        
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setUpGUI();
        
    }

    public static void main(String args[])
    {
        GameFrame frame = new GameFrame();
    }
    
    public void setUpGUI()
    {
        setLayout(new GridLayout(1, 1, 0, 0));
        
        GameScreen gs = new GameScreen(this);
        add(gs);        
        setVisible(true);
    }
    
    public int getFrameWidth()
    {
        return width;
    }
    
    public int getFrameHeight()
    {
        return height;
    }
}

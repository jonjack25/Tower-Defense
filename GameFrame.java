import javax.swing.*;
import java.awt.*;
public class GameFrame extends JFrame
{
    private String title = "Aftermath";
    private int width = Grid.POSITION_SIZE * 50 - 10/*1024*/, height = Grid.POSITION_SIZE * 29/*768*/;
    private Dimension size;
    private GameScreen gs;
    public GameFrame()
    {
        size = new Dimension(width, height);
        setTitle(title);
        setSize(size);
        gs = new GameScreen(this);
        
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
    
    public void setDimensions(int w, int h)
    {
        width = w;
        height = h;
    }
}
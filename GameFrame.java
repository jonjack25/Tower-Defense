import javax.swing.*;
import java.awt.*;
public class GameFrame extends JFrame
{
    private static String title = "Tower Defense Game";
    public static int width = 1024, height = 768;
    public static Dimension size = new Dimension(width, height);
    
    public GameFrame()
    {
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
        //gs.setSize(this.getWidth(), 600);
        add(gs);
        
        TurretBar tb = new TurretBar();
        setVisible(true);
    }
}

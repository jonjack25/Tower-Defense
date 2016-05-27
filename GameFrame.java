
/**
 * Write a description of class GameFrame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
public class GameFrame extends JFrame
{
    //public GameFrame(String title, GraphicsConfiguration gc)
    {
        //super(title, gc);
    }
    
    public GameFrame(String title)
    {
        super(title);
        //setResizable(false);
        setSize(1024, 760);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String args[])
    {
        setUpGUI();
    }
    
    public static void setUpGUI()
    {
        GameFrame frame = new GameFrame("Tower Defense");
        GameScreen gs = new GameScreen();
        TurretBar tb = new TurretBar();
        InfoBar ib = new InfoBar();
        
        tb.setPreferredSize(new Dimension(0, 300));
        gs.setPreferredSize(new Dimension(0, frame.getHeight() - 300));
        
        gs.setBackground(Color.BLACK);
        tb.setBackground(Color.GRAY);
        ib.setBackground(Color.BLUE);
                
        frame.add(gs, BorderLayout.NORTH);
        frame.add(tb, BorderLayout.SOUTH);
        frame.add(ib, BorderLayout.EAST);  
       
    }
}

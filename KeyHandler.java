import java.awt.event.*;
import java.awt.*;

public class KeyHandler implements MouseMotionListener, MouseListener
{
    public static int btnStatus = 0; //0 = not clicked, 1 = clicked
    public KeyHandler()
    {
        
    }

    public void mouseEntered(MouseEvent arg0)
    {
        
    }
    
    public void mouseExited(MouseEvent arg0)
    {
        
    }
    
    public void mousePressed(MouseEvent arg0)
    {

    }
    
    public void mouseReleased(MouseEvent arg0)
    {
        
    }
    
    public void mouseClicked(MouseEvent arg0)
    {
        if(TurretBar.btnArray[0].contains(GameScreen.mouse) || TurretBar.btnArray[1].contains(GameScreen.mouse) || TurretBar.btnArray[2].contains(GameScreen.mouse))
        {
            if(btnStatus == 0)
                btnStatus = 1;
            else
                btnStatus = 0;
        }
        //TurretBar.clicked();
    }
    
    public void mouseDragged(MouseEvent arg0)
    {
        GameScreen.mouse = new Point(arg0.getX() + (GameFrame.width - GameScreen.myWidth) / 2, arg0.getY() + (GameFrame.height - GameScreen.myHeight) / 2);        
    }
    
    public void mouseMoved(MouseEvent arg0)
    {
        GameScreen.mouse = new Point(arg0.getX() - (GameFrame.width - GameScreen.myWidth) / 2, arg0.getY() - (GameFrame.height - GameScreen.myHeight) / 2);
    }
    
    public static int returnStatus()
    {
        return btnStatus;
    }
}

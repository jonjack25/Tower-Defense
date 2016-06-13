
import java.awt.event.*;
import java.awt.*;

public class KeyHandler implements MouseMotionListener, MouseListener
{
    private int btnStatus = 0; //0 = not clicked, 1 = clicked
    private int basicTurretStatus = 0, strongTurretStatus = 0, fastTurretStatus = 0; //0 when not active, 1 when active (user is placing)
    private GameFrame frame;
    private Game game;
    private GameScreen screen;
    private TurretBar tb;
    private Point mouse = new Point(0,0);
    
    public KeyHandler(GameFrame f, Game g, GameScreen gs)
    {
        frame = f;
        game = g;
        screen = gs;
    }

    public KeyHandler(GameFrame f)
    {
        frame = f;
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
        if(screen.getMenu())
        {
            if(screen.getMainMenu().menuBtn1())
            {
                screen.setMenu(false);
            }
            else if(screen.getMainMenu().menuBtn3())
            {
                screen.setMenu(false);
                frame.dispose();
            }
        }
        else
        {
            Turret t = game.purchaseBaseTurret();
            if(basicTurretStatus == 1)
            {
                if(t == null)
                {
                    btnStatus = 0;
                    basicTurretStatus = 0;
                }
                else
                {
                    
                }
                /*
                if(Position.valid)
                {
                    basicTurretStatus = 0;
                    btnStatus = 0;
                }
                */
               
                if(game.getGrid().isValidPosition(game.getGrid().getPosition(screen.key.getMouse())))
                {
                    game.placeTurret(t, game.getGrid().getPosition(mouse));
                    basicTurretStatus = 0;
                    btnStatus = 0;
                }
            }
            else if(strongTurretStatus == 1)
            {
                //Grid.addTurret();
                if(game.getGrid().isValidPosition(game.getGrid().getPosition(screen.getMousePoint())))
                {
                    game.placeTurret(t, game.getGrid().getPosition(mouse));
                    strongTurretStatus = 0;
                    btnStatus = 0;
                }
            }
            else if(fastTurretStatus == 1)
            {
                //Grid.addTurret();
                if(game.getGrid().isValidPosition(game.getGrid().getPosition(screen.getMousePoint())))
                {
                    game.placeTurret(t, game.getGrid().getPosition(mouse));
                    fastTurretStatus = 0;
                    btnStatus = 0;
                }
            }
            if(btnStatus == 0)
            {
                if(tb.mouseBtn1())
                {
                    basicTurretStatus = 1;
                    btnStatus = 1;
                }
                else if(tb.mouseBtn2())
                {
                    strongTurretStatus = 1;
                    btnStatus = 1;
                }
                else if(tb.mouseBtn3())
                {
                    fastTurretStatus = 1;
                    btnStatus = 1;
                }
            }
        }
        /*
        else if(!GameScreen.menu && (TurretBar.btnArray[0].contains(GameScreen.mouse) || TurretBar.btnArray[1].contains(GameScreen.mouse) || TurretBar.btnArray[2].contains(GameScreen.mouse)))
        {
            if(btnStatus == 0)
                btnStatus = 1;
            else
                btnStatus = 0;
        }
        
        */
        //TurretBar.clicked();
        
    }
    
    public void mouseDragged(MouseEvent arg0)
    {
        mouse = new Point(arg0.getX() + (frame.getFrameWidth() - screen.getMyWidth()) / 2, arg0.getY() + (frame.getFrameHeight() - screen.getMyHeight()) / 2);        
        screen.setMousePoint(mouse);
    }
    
    public void mouseMoved(MouseEvent arg0)
    {
        mouse = new Point(arg0.getX() + (frame.getFrameWidth() - screen.getMyWidth()) / 2, arg0.getY() + (frame.getFrameHeight() - screen.getMyHeight()) / 2);
        screen.setMousePoint(mouse);
    }
    
    public int returnStatus()
    {
        return btnStatus;
    }
    
    public int basicTurretStatus()
    {
        return basicTurretStatus;
    }
    
    public int fastTurretStatus()
    {
        return fastTurretStatus;
    }
    
    public int strongTurretStatus()
    {
        return strongTurretStatus;
    }
    
    public Point getMouse()
    {
        return mouse;
    }
    
    public void addTB(TurretBar t)
    {
        tb = t;
    }
}

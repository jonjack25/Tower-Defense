import java.awt.event.*;
import java.awt.*;

public class KeyHandler implements MouseMotionListener, MouseListener
{
    private int btnStatus = 0; //0 = not clicked, 1 = clicked
    private int basicTurretStatus = 0, strongTurretStatus = 0, fastTurretStatus = 0, hoverStatus = 0; //0 when not active, 1 when active (user is placing)
    private GameFrame frame;
    private Game game;
    private GameScreen screen;
    private TurretBar tb;
    private Point mouse = new Point(0,0);
    private Turret t = null, hoverTurret = null;
    
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
        if(game.getGameOver() == true)
        {
            btnStatus = 0;
            basicTurretStatus = 0;
            strongTurretStatus = 0;
            fastTurretStatus = 0;
            hoverStatus = 0;
            hoverTurret = null;
            if(screen.getTB().restartBtn())
            {
                screen.restartGame();
            }
            return;
        }
        if(screen.getMenu())
        {
            if(screen.getMainMenu().menuBtn1())
            {
                screen.setMenu(false);
            }
            else if(screen.getMainMenu().menuBtn2())
            {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        }
        else
        {
            if(basicTurretStatus == 1)
            {
                if(game.getGrid().isValidPosition(game.getGrid().getPosition(screen.getKey().getMouse())))
                {
                    game.placeTurret(t, game.getGrid().getPosition(mouse));
                    basicTurretStatus = 0;
                    btnStatus = 0;
                }
                else
                {
                    game.cancelBasePurchase();
                    basicTurretStatus = 0;
                    btnStatus = 0;
                    t = null;
                }
            }
            else if(strongTurretStatus == 1)
            {
                if(game.getGrid().isValidPosition(game.getGrid().getPosition(screen.getMousePoint())))
                {
                    game.placeTurret(t, game.getGrid().getPosition(mouse));
                    strongTurretStatus = 0;
                    btnStatus = 0;
                }
                else
                {
                    game.cancelStrongPurchase();
                    strongTurretStatus = 0;
                    btnStatus = 0;
                    t = null;
                }
            }
            else if(fastTurretStatus == 1)
            {
                if(game.getGrid().isValidPosition(game.getGrid().getPosition(screen.getMousePoint())))
                {
                    game.placeTurret(t, game.getGrid().getPosition(mouse));
                    fastTurretStatus = 0;
                    btnStatus = 0;
                }
                else
                {
                    game.cancelFastPurchase();
                    fastTurretStatus = 0;
                    btnStatus = 0;
                    t = null;
                }
            }
            else if(btnStatus == 0)
            {
                if(tb.mouseBtn1())
                {
                    t = game.purchaseBaseTurret();
                    if(t != null)
                    {
                        basicTurretStatus = 1;
                        btnStatus = 1;
                    }
                }
                else if(tb.mouseBtn2())
                {
                    t = game.purchaseStrongTurret();
                    if(t != null)
                    {
                        strongTurretStatus = 1;
                        btnStatus = 1;
                    }
                }
                else if(tb.mouseBtn3())
                {
                    t = game.purchaseFastTurret();
                    if(t != null)
                    {
                        fastTurretStatus = 1;
                        btnStatus = 1;
                    }
                }
                else if(game.findTurret(game.getGrid().getPosition(screen.getMousePoint())) != null)
                {
                   game.upgradeTurret(game.findTurret(game.getGrid().getPosition(screen.getMousePoint())));
                }
            }
            if(screen.getTB().restartBtn())
            {
                screen.restartGame();
            }
        }
    }
    
    public void mouseDragged(MouseEvent arg0)
    {
        hoverTurret = null;
        hoverStatus = 0;
        mouse = new Point(arg0.getX() + (frame.getFrameWidth() - screen.getMyWidth()) / 2, arg0.getY() + (frame.getFrameHeight() - screen.getMyHeight()) / 2 - 50);        
        screen.setMousePoint(mouse);
        if(screen.getGame().findTurret(screen.getGame().getGrid().getPosition(mouse)) != null)
        {
            hoverTurret = screen.getGame().findTurret(screen.getGame().getGrid().getPosition(mouse));
            hoverStatus = 1;
        }
    }
    
    public void mouseMoved(MouseEvent arg0)
    {
        hoverTurret = null;
        hoverStatus = 0;
        mouse = new Point(arg0.getX() + (frame.getFrameWidth() - screen.getMyWidth()) / 2, arg0.getY() + (frame.getFrameHeight() - screen.getMyHeight()) / 2 - 50);
        screen.setMousePoint(mouse);
        if(screen.getGame().findTurret(screen.getGame().getGrid().getPosition(mouse)) != null)
        {
            hoverTurret = screen.getGame().findTurret(screen.getGame().getGrid().getPosition(mouse));
            hoverStatus = 1;
        }
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
    
    public int hoverStatus()
    {
        return hoverStatus;
    }
    
    public Turret getHover()
    {
        return hoverTurret;
    }
    
    public Point getMouse()
    {
        return mouse;
    }
    
    public void setReturnStatus(int i)
    {
        btnStatus = i;
    }
    
    public void addTB(TurretBar t)
    {
        tb = t;
    }
}


import javax.swing.*;
import java.awt.*;
public class TurretBar 
{
    private Rectangle[] btnArray;
    private int turretStoreSize = 3;
    private int btnSizeX = 100, btnSizeY = 100;
    private int space = 10;
    private int btnStartX = 650, btnStartY = 580;
    private int btnStatus = 0; //0 = not clicked, 1 = clicked
    Image basic, strong, fast;
    private GameScreen screen;
    private GameFrame frame;
    public TurretBar(GameScreen gs)
    {
        screen = gs;
        frame = screen.getFrame();
        btnArray = new Rectangle[turretStoreSize];
        basic = new ImageIcon("circleTurretRed.png").getImage();
        strong = new ImageIcon("circleTurretBlue3.png").getImage();
        fast = new ImageIcon("circleTurretGreen.png").getImage();
        for(int i = 0; i < btnArray.length; i++)
        {
            btnArray[i] = new Rectangle(btnStartX + (btnSizeX + space) * i,
                (btnStartY + (frame.getHeight() - screen.getHeight()) / 2), btnSizeX, btnSizeY);
            /*
            btnArray[i].setLocation(btnStartX + (btnSizeX + space) * i,
                (btnStartY + (GameFrame.height - GameScreen.myHeight) / 2));
            btnArray[i].setSize(btnSizeX, btnSizeY);
            */
            
            // x: GameScreen.myWidth / 2 - turretStoreSize * (btnSizeX + space) / 2 + (btnSizeX + space) * i
            
        }
    }
    
    public void draw(Graphics g)
    {
        for(int i = 0; i < btnArray.length; i++)
        {
            //btnArray[i].addMouseListener(new KeyHandler());
            g.setColor(new Color(0, 0, 0));
            if(btnArray[i].contains(screen.key.getMouse()))
            {
                g.setColor(new Color(0, 0, 0, 100));
                g.fillRect(btnArray[i].x, btnArray[i].y, btnArray[i].width, btnArray[i].height);
                
            }
            if(i == 0)
            {
                g.fillRect(btnArray[i].x, btnArray[i].y, btnArray[i].width, btnArray[i].height);
                g.drawImage(basic, btnStartX + (btnSizeX + space) * i,
                (btnStartY + (frame.getFrameHeight() - screen.getMyHeight()) / 2), btnSizeX, btnSizeY, null);
            }
            else if(i == 1)
            {
                g.fillRect(btnArray[i].x, btnArray[i].y, btnArray[i].width, btnArray[i].height);
                g.drawImage(strong, btnStartX + (btnSizeX + space) * i,
                (btnStartY + (frame.getFrameHeight() - screen.getMyHeight()) / 2), btnSizeX, btnSizeY, null);
            }
            else if(i == 2)
            {
                g.fillRect(btnArray[i].x, btnArray[i].y, btnArray[i].width, btnArray[i].height);
                g.drawImage(fast, btnStartX + (btnSizeX + space) * i,
                (btnStartY + (frame.getFrameHeight() - screen.getMyHeight()) / 2), btnSizeX, btnSizeY, null);
            }
                
        }
    }
    
    public void clicked()
    {
        if(btnStatus == 0)
            btnStatus = 1;
        else
            btnStatus = 0;
    }
    
    public int returnStatus()
    {
        return btnStatus;
    }
    
    public int getBtnStartY()
    {
        return btnStartY;
    }
    
    public boolean mouseBtn1()
    {
        return btnArray[0].contains(screen.getMousePoint());
    }
    
    public boolean mouseBtn2()
    {
        return btnArray[1].contains(screen.getMousePoint());
    }
    
    public boolean mouseBtn3()
    {
        return btnArray[2].contains(screen.getMousePoint());
    }
}
    
  
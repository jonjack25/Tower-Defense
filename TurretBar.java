import javax.swing.*;
import java.awt.*;
public class TurretBar 
{
    public static Rectangle[] btnArray;
    public static int turretStoreSize = 3;
    public static int btnSizeX = 50, btnSizeY = 100;
    public static int space = 10;
    public static int btnStartX = 800, btnStartY = 580;
    public static int btnStatus = 0; //0 = not clicked, 1 = clicked
    public TurretBar()
    {
        btnArray = new Rectangle[turretStoreSize];
        define();
    }
    
    public void draw(Graphics g)
    {
        for(int i = 0; i < btnArray.length; i++)
        {
            //btnArray[i].addMouseListener(new KeyHandler());
            g.setColor(new Color(0, 0, 0));
            if(btnArray[i].contains(GameScreen.mouse))
            {
                g.setColor(new Color(0, 0, 0, 100));
                g.fillRect(btnArray[i].x, btnArray[i].y, btnArray[i].width, btnArray[i].height);
                
            }
            g.fillRect(btnArray[i].x, btnArray[i].y, btnArray[i].width, btnArray[i].height);
            
        }
    }
    
    public void define()
    {
        for(int i = 0; i < btnArray.length; i++)
        {
            btnArray[i] = new Rectangle(btnStartX + (btnSizeX + space) * i,
                (btnStartY + (GameFrame.height - GameScreen.myHeight) / 2), btnSizeX, btnSizeY);
            /*
            btnArray[i].setLocation(btnStartX + (btnSizeX + space) * i,
                (btnStartY + (GameFrame.height - GameScreen.myHeight) / 2));
            btnArray[i].setSize(btnSizeX, btnSizeY);
            */
            
            // x: GameScreen.myWidth / 2 - turretStoreSize * (btnSizeX + space) / 2 + (btnSizeX + space) * i
        }
    }
    
    public static void clicked()
    {
        if(btnStatus == 0)
            btnStatus = 1;
        else
            btnStatus = 0;
    }
    
    public static int returnStatus()
    {
        return btnStatus;
    }
}
    
  
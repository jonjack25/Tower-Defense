import javax.swing.*;
import java.awt.*;
public class TurretBar 
{
    private Rectangle[] btnArray;
    private Rectangle restart;
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
        basic = new ImageIcon("resources/circleTurretRed.png").getImage();
        strong = new ImageIcon("resources/circleTurretBlue.png").getImage();
        fast = new ImageIcon("resources/circleTurretGreen.png").getImage();
        for(int i = 0; i < btnArray.length; i++)
        {
            btnArray[i] = new Rectangle(btnStartX + (btnSizeX + space) * i,
                (btnStartY + (frame.getHeight() - screen.getMyHeight()) / 2), btnSizeX, btnSizeY);
        }
    }
    
    public void draw(Graphics g)
    {
        if(screen.getGame().getGameOver() == true)
            return;
        for(int i = 0; i < btnArray.length; i++)
        {
            g.setColor(new Color(0, 0, 0));
            if(btnArray[i].contains(screen.getKey().getMouse()))
            {
                g.setColor(new Color(0, 0, 0, 100));
                g.fillRect(btnArray[i].x, btnArray[i].y, btnArray[i].width, btnArray[i].height);
                g.setColor(new Color(0, 0, 0));
                g.setFont(new Font("Serif", Font.PLAIN, 30));
                if(i == 0)
                {
                    g.drawString("Basic Turret", frame.getWidth() / 2 - 50, btnStartY + 40 + 5);
                    g.drawString("Cost: " + BaseTurret.PURCHASE_COST, frame.getWidth() / 2 - 50, btnStartY + 70 + 5);
                    g.drawString("Damage: " + BaseTurret.DAMAGE, frame.getWidth() / 2 - 50, btnStartY + 100 + 5);
                }
                else if(i == 1)
                {
                    g.drawString("Strong Turret", frame.getWidth() / 2 - 50, btnStartY + 40 + 5);
                    g.drawString("Cost: " + StrongTurret.PURCHASE_COST, frame.getWidth() / 2 - 50, btnStartY + 70 + 5);
                    g.drawString("Damage: " + StrongTurret.DAMAGE, frame.getWidth() / 2 - 50, btnStartY + 100 + 5);
                }
                else if(i == 2)
                {
                    g.drawString("Fast Turret", frame.getWidth() / 2 - 50, btnStartY + 40 + 5);
                    g.drawString("Cost: " + FastTurret.PURCHASE_COST, frame.getWidth() / 2 - 50, btnStartY + 70 + 5);
                    g.drawString("Damage: " + FastTurret.DAMAGE, frame.getWidth() / 2 - 50, btnStartY + 100 + 5);
                }
            }
            if(i == 0)
            {
                g.setColor(new Color(0, 0, 0, 100));
                g.fillRect(btnArray[i].x, btnArray[i].y, btnArray[i].width, btnArray[i].height);
                g.drawImage(basic, btnStartX + (btnSizeX + space) * i,
                (btnStartY + (frame.getHeight() - screen.getHeight()) / 2), btnSizeX, btnSizeY, null);
            }
            else if(i == 1)
            {
                g.setColor(new Color(0, 0, 0, 100));
                g.fillRect(btnArray[i].x, btnArray[i].y, btnArray[i].width, btnArray[i].height);
                g.drawImage(strong, btnStartX + (btnSizeX + space) * i,
                (btnStartY + (frame.getHeight() - screen.getHeight()) / 2), btnSizeX, btnSizeY, null);
            }
            else if(i == 2)
            {
                g.setColor(new Color(0, 0, 0, 100));
                g.fillRect(btnArray[i].x, btnArray[i].y, btnArray[i].width, btnArray[i].height);
                g.drawImage(fast, btnStartX + (btnSizeX + space) * i,
                (btnStartY + (frame.getHeight() - screen.getHeight()) / 2), btnSizeX, btnSizeY, null);
            }
        }
        if(screen.getKey().hoverStatus() == 1)
        {
            g.setColor(new Color(156, 176, 56));
            g.setFont(new Font("Serif", Font.PLAIN, 30));
                
            g.drawString("Damage: " + screen.getKey().getHover().getDamage(), frame.getWidth() / 2 - 100, btnStartY + 40 + 5);
            g.drawString("Upgrade: " + screen.getKey().getHover().getUpgrade(), frame.getWidth() / 2 - 100, btnStartY + 70 + 5);
            g.drawString("Upgrade Cost: " + screen.getKey().getHover().getUpgradeCost(), frame.getWidth() / 2 - 100, btnStartY + 100 + 5);
            
            g.setColor(new Color(255, 255, 255));
            int x = screen.getKey().getHover().getPosition().getXCoordinate() - Grid.POSITION_SIZE / 2 - screen.getKey().getHover().getRadius();
            int y = screen.getKey().getHover().getPosition().getYCoordinate() - Grid.POSITION_SIZE / 2 - screen.getKey().getHover().getRadius();
            int r = screen.getKey().getHover().getRadius() * 2;
            g.drawOval(x, y, r, r);
        }
        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font("Serif", Font.PLAIN, 30));
        g.drawString("Round: " + screen.getGame().getRound(), 30, btnStartY + 60);
        g.drawString("Coins: " + screen.getGame().getCoins(), 30, btnStartY + 90);
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
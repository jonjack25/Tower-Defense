import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
public class Position extends Rectangle
{
    Image grassTile, pathTile, basic, strong, fast;
    private boolean hover = false;
    private int xCoordinate, yCoordinate, entityType, length, width, row, col;
    private int ground;
    

    public Position(int x, int y, int h, int w, int r, int c)
    {
        xCoordinate = x;
        yCoordinate = y;
        length = h;
        width = w;
        entityType = 0;
        row = r;  
        col = c;
        setBounds(x, y, h, w);
        grassTile = new ImageIcon("groundPixel.png").getImage();
        pathTile = new ImageIcon("pathPixel.jpg").getImage();
        basic = new ImageIcon("circleTurretRed.png").getImage();
        strong = new ImageIcon("circleTurretBlue.png").getImage();
        fast = new ImageIcon("circleTurretGreen.png").getImage();
    }

    public void draw(Graphics g)
    {
        if(ground == 0)
            g.drawImage(grassTile, x, y, width, height, null);
        else if(ground == 1)
            g.drawImage(pathTile, x, y, width, height, null);
        
        if(hover)
        {   
            g.setColor(new Color(0, 255, 0));
            g.drawRect(x, y, width, height);
            g.fillRect(x, y, width, height);
        }
        
       
        /*
        if(KeyHandler.returnStatus() == 1)
        {
            if(Grid.grid[i][j].contains(GameScreen.mouse))
            {
               checkTurretPlace(i, j);
               if(valid)
                   g.setColor(new Color(0, 255, 0));
               else
                   g.setColor(new Color(255, 0, 0));
             
                g.drawRect(x, y, width, height);
                g.fillRect(x, y, width, height);
                
                // for 2x2:
                g.drawRect(x - Grid.positionSize, y - Grid.positionSize, width, height);
                g.fillRect(x - Grid.positionSize, y - Grid.positionSize, width, height);
                
                g.drawRect(x, y - Grid.positionSize, width, height);
                g.fillRect(x, y - Grid.positionSize, width, height);
                
                g.drawRect(x - Grid.positionSize, y, width, height);
                g.fillRect(x - Grid.positionSize, y, width, height);
                
                // for 3x3:
                g.drawRect(x - Grid.positionSize * 2, y - Grid.positionSize * 2, width, height);
                g.fillRect(x - Grid.positionSize * 2, y - Grid.positionSize * 2, width, height);
                
                g.drawRect(x, y - Grid.positionSize * 2, width, height);
                g.fillRect(x, y - Grid.positionSize * 2, width, height);
                
                g.drawRect(x - Grid.positionSize * 2, y, width, height);
                g.fillRect(x - Grid.positionSize * 2, y, width, height);
                
                g.drawRect(x - Grid.positionSize, y - Grid.positionSize * 2, width, height);
                g.fillRect(x - Grid.positionSize, y - Grid.positionSize * 2, width, height);
                
                g.drawRect(x - Grid.positionSize * 2, y - Grid.positionSize, width, height);
                g.fillRect(x - Grid.positionSize * 2, y - Grid.positionSize, width, height);
            }
            else
            {
                 //g.drawImage(pathTile, x, y, width, height, null);
                
                 if(Grid.path[j][i] == 0)
                    g.drawImage(grassTile, x, y, width, height, null);
                 else if(Grid.path[j][i] == 1)
                    g.drawImage(pathTile, x, y, width, height, null);
                 /*
                 else
                 {
                    for(Turret t : game.turrets)
                        t.draw(g);
                    for(Enemy e: game.enemies)
                        e.draw(g);
                    for(Bullet b : game.bullets)
                        b.draw(g);
                 }
                    

                    
                 /*
                 else if(!(i == 0 || i == 1 || j == 0 || j == 1) && Grid.path[j][i] == 2 && Grid.path[j - 1][i - 1] == 2 && Grid.path[j][i - 1] == 2 && Grid.path[j - 1][i] == 2 &&
                        Grid.path[j - 2][i - 2] == 2 && Grid.path[j - 1][i - 2] == 2 && Grid.path[j - 2][i - 1] == 2 && Grid.path[j][i - 2] == 2 && Grid.path[j - 2][i] == 2)
                 {
                    for(int r = 2; r >= 0; r--)
                    {
                        for(int c = 2; c >= 0; c--)
                        {
                            g.drawImage(grassTile, x - r * width, y - c * height, width, height, null);
                        }
                    }
                    //g.drawImage(grassTile, x, y, width, height, null);
                    Graphics2D g2d = (Graphics2D)g.create();
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                    g2d.drawImage(basic, x - 2 * width, y - 2 * height, width * 3, height * 3, null);
                 }
                 else if(!(i == 0 || i == 1 || j == 0 || j == 1) && Grid.path[j][i] == 3 && Grid.path[j - 1][i - 1] == 3 && Grid.path[j][i - 1] == 3 && Grid.path[j - 1][i] == 3 &&
                        Grid.path[j - 2][i - 2] == 3 && Grid.path[j - 1][i - 2] == 3 && Grid.path[j - 2][i - 1] == 3 && Grid.path[j][i - 2] == 3 && Grid.path[j - 2][i] == 3)
                 {
                    for(int r = 2; r >= 0; r--)
                    {
                        for(int c = 2; c >= 0; c--)
                        {
                            g.drawImage(grassTile, x - r * width, y - c * height, width, height, null);
                        }
                    }
                    //g.drawImage(grassTile, x, y, width, height, null);
                    Graphics2D g2d = (Graphics2D)g.create();
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                    g2d.drawImage(strong, x - 2 * width, y - 2 * height, width * 3, height * 3, null);
                 }
                 else if(!(i == 0 || i == 1 || j == 0 || j == 1) && Grid.path[j][i] == 4 && Grid.path[j - 1][i - 1] == 4 && Grid.path[j][i - 1] == 4 && Grid.path[j - 1][i] == 4 &&
                        Grid.path[j - 2][i - 2] == 4 && Grid.path[j - 1][i - 2] == 4 && Grid.path[j - 2][i - 1] == 4 && Grid.path[j][i - 2] == 4 && Grid.path[j - 2][i] == 4)
                 {
                    for(int r = 2; r >= 0; r--)
                    {
                        for(int c = 2; c >= 0; c--)
                        {
                            g.drawImage(grassTile, x - r * width, y - c * height, width, height, null);
                        }
                    }
                    //g.drawImage(grassTile, x, y, width, height, null);
                    Graphics2D g2d = (Graphics2D)g.create();
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                    g2d.drawImage(fast, x - 2 * width, y - 2 * height, width * 3, height * 3, null);
                 }
                 
            }
        }
        else
        {
            //g.drawImage(pathTile, x, y, width, height, null);
            if(Grid.path[j][i] == 1)
                g.drawImage(pathTile, x, y, width, height, null);
            else //if(Grid.path[j][i] == 1) 
                g.drawImage(grassTile, x, y, width, height, null);
            /*
            else if(!(i == 0 || i == 1 || j == 0 || j == 1) && Grid.path[j][i] == 2 && Grid.path[j - 1][i - 1] == 2 && Grid.path[j][i - 1] == 2 && Grid.path[j - 1][i] == 2 &&
                        Grid.path[j - 2][i - 2] == 2 && Grid.path[j - 1][i - 2] == 2 && Grid.path[j - 2][i - 1] == 2 && Grid.path[j][i - 2] == 2 && Grid.path[j - 2][i] == 2 && valid)
            {
               for(int r = 2; r >= 0; r--)
               {
                   for(int c = 2; c >= 0; c--)
                   {
                       g.drawImage(grassTile, x - r * width, y - c * height, width, height, null);
                   }
               }
               //g.drawImage(grassTile, x, y, width, height, null);
               Graphics2D g2d = (Graphics2D)g.create();
               g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
               g2d.drawImage(basic, x - 2 * width, y - 2 * height, width * 3, height * 3, null);
            }
            else if(!(i == 0 || i == 1 || j == 0 || j == 1) && Grid.path[j][i] == 3 && Grid.path[j - 1][i - 1] == 3 && Grid.path[j][i - 1] == 3 && Grid.path[j - 1][i] == 3 &&
                   Grid.path[j - 2][i - 2] == 3 && Grid.path[j - 1][i - 2] == 3 && Grid.path[j - 2][i - 1] == 3 && Grid.path[j][i - 2] == 3 && Grid.path[j - 2][i] == 3)
            {
               for(int r = 2; r >= 0; r--)
               {
                   for(int c = 2; c >= 0; c--)
                   {
                       g.drawImage(grassTile, x - r * width, y - c * height, width, height, null);
                   }
               }
               //g.drawImage(grassTile, x, y, width, height, null);
               Graphics2D g2d = (Graphics2D)g.create();
               g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
               g2d.drawImage(strong, x - 2 * width, y - 2 * height, width * 3, height * 3, null);
            }
            else if(!(i == 0 || i == 1 || j == 0 || j == 1) && Grid.path[j][i] == 4 && Grid.path[j - 1][i - 1] == 4 && Grid.path[j][i - 1] == 4 && Grid.path[j - 1][i] == 4 &&
                   Grid.path[j - 2][i - 2] == 4 && Grid.path[j - 1][i - 2] == 4 && Grid.path[j - 2][i - 1] == 4 && Grid.path[j][i - 2] == 4 && Grid.path[j - 2][i] == 4)
            {
               for(int r = 2; r >= 0; r--)
               {
                   for(int c = 2; c >= 0; c--)
                   {
                       g.drawImage(grassTile, x - r * width, y - c * height, width, height, null);
                   }
               }
               //g.drawImage(grassTile, x, y, width, height, null);
               Graphics2D g2d = (Graphics2D)g.create();
               g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
               g2d.drawImage(fast, x - 2 * width, y - 2 * height, width * 3, height * 3, null);
            }
            
        }
        //g.drawRect(x, y, width, height);
      */
    }
    
    public int getXCoordinate()
    {
        return xCoordinate;
    }
    
    public int getYCoordinate()
    {
        return yCoordinate;
    }
    
    public int getRow()
    {
        return row;
    }
    
    public int getCol()
    {
        return col;
    }
    
    public int getType()
    {
        return entityType;
    }
    
    public void setType(int type)
    {
        entityType = type;
    }
    
    public boolean equals(Position p)
    {
        if(this.xCoordinate == p.xCoordinate && this.yCoordinate == p.yCoordinate)
            return true;  
        return false;
    }
    
    public boolean isEmpty()
    {
        return entityType == 0;
    }
    
    /*
    public static void checkTurretPlace(int i, int j)
    {
        if(i == 0 || j == 0 || i == 1 || j == 1)
        {
            //g.setColor(new Color(255, 0, 0));
            valid = false;
        }
        else if(Grid.path[j][i] == 2 || Grid.path[j - 1][i - 1] == 2 || Grid.path[j][i - 1] == 2 || Grid.path[j - 1][i] == 2 ||
                Grid.path[j - 2][i - 2] == 2 || Grid.path[j - 1][i - 2] == 2 || Grid.path[j - 2][i - 1] == 2 || Grid.path[j][i - 2] == 2 || Grid.path[j - 2][i] == 2)
        {
            valid = false;
        }
        else if(Grid.path[j][i] == 3 || Grid.path[j - 1][i - 1] == 3 || Grid.path[j][i - 1] == 3 || Grid.path[j - 1][i] == 3 ||
                Grid.path[j - 2][i - 2] == 3 || Grid.path[j - 1][i - 2] == 3 || Grid.path[j - 2][i - 1] == 3 || Grid.path[j][i - 2] == 3 || Grid.path[j - 2][i] == 3)
        {
            valid = false;
        }
        else if(Grid.path[j][i] == 4 || Grid.path[j - 1][i - 1] == 4 || Grid.path[j][i - 1] == 4 || Grid.path[j - 1][i] == 4 ||
                Grid.path[j - 2][i - 2] == 4 || Grid.path[j - 1][i - 2] == 4 || Grid.path[j - 2][i - 1] == 4 || Grid.path[j][i - 2] == 4 || Grid.path[j - 2][i] == 4)
        {
            valid = false;
        }
        else if(Grid.path[j][i] == 1 || Grid.path[j - 1][i - 1] == 1 || Grid.path[j][i - 1] == 1 || Grid.path[j - 1][i] == 1 ||
                Grid.path[j - 2][i - 2] == 1 || Grid.path[j - 1][i - 2] == 1 || Grid.path[j - 2][i - 1] == 1 || Grid.path[j][i - 2] == 1 || Grid.path[j - 2][i] == 1)//green, second line for 3x3   
        {    
            //g.setColor(new Color(255, 0, 0)); //red
            valid = false;
        }
        else //if(Grid.path[j][i] == 0 || Grid.path[j - 1][i - 1] == 0 || Grid.path[j][i - 1] == 0 || Grid.path[j - 1][i] == 0)
        {    
            //g.setColor(new Color(0, 255, 0));
            valid = true;
        }
        
    }
    */
    
    public void setGround(int g)
    {
        ground = g;
    }
    
    public void setHover(boolean h)
    {
        hover = h;
    }
}


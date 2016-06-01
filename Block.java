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
public class Block extends Rectangle
{
    public int groundID;
    public int airID;
    Image grassTile, pathTile;
    /**
     * Constructor for objects of class Block
     */
    public Block(int x, int y, int height, int width, int gID, int aID)
    {
        setBounds(x, y, width, height);
        groundID = gID;
        airID = aID;
        grassTile = new ImageIcon("groundPixel.png").getImage();
        pathTile = new ImageIcon("pathPixel.jpg").getImage();
    }

    public void draw(Graphics g, int i, int j)
    {
        if(KeyHandler.returnStatus() == 1)
        {
            if(Room.blocks[i][j].contains(GameScreen.mouse))
            {
                //if(Room.path[j][i] == 0 || Room.path[j - 1][i - 1] == 0 || Room.path[j][i - 1] == 0 || Room.path[j - 1][i] == 0)
                  //  g.setColor(new Color(0, 255, 0));
                if(i == 0 || j == 0)
                    g.setColor(new Color(255, 0, 0));
                else
                {
                    if(Room.path[j][i] == 1 || Room.path[j - 1][i - 1] == 1 || Room.path[j][i - 1] == 1 || Room.path[j - 1][i] == 1)//green
                        g.setColor(new Color(255, 0, 0)); //red
                    else //if(Room.path[j][i] == 0 || Room.path[j - 1][i - 1] == 0 || Room.path[j][i - 1] == 0 || Room.path[j - 1][i] == 0)
                        g.setColor(new Color(0, 255, 0));
                }
             
                g.drawRect(x, y, width, height);
                g.fillRect(x, y, width, height);
                
                g.drawRect(x - Room.blockSize, y - Room.blockSize, width, height);
                g.fillRect(x - Room.blockSize, y - Room.blockSize, width, height);
                
                g.drawRect(x, y - Room.blockSize, width, height);
                g.fillRect(x, y - Room.blockSize, width, height);
                
                g.drawRect(x - Room.blockSize, y, width, height);
                g.fillRect(x - Room.blockSize, y, width, height);
            }
            else
            {
                 //g.drawImage(pathTile, x, y, width, height, null);
                
                 if(Room.path[j][i] == 0)
                    g.drawImage(grassTile, x, y, width, height, null);
                 else if(Room.path[j][i] == 1)
                    g.drawImage(pathTile, x, y, width, height, null);
                    
            }
        }
        else
        {
            //g.drawImage(pathTile, x, y, width, height, null);
            
            if(Room.path[j][i] == 0)
                g.drawImage(grassTile, x, y, width, height, null);
            else if(Room.path[j][i] == 1) 
                g.drawImage(pathTile, x, y, width, height, null);
           
        }
        //g.drawRect(x, y, width, height);
    }
}

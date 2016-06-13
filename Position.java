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
        g.drawRect(x, y, width, height);
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
        hover = false;
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
    
    public void setGround(int g)
    {
        ground = g;
    }
    
    public void setHover(boolean h)
    {
        hover = h;
    }
}
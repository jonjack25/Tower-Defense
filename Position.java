import java.awt.*;
public class Position extends Rectangle
{
    private int xCoordinate, yCoordinate, entityType, length, width, row, col;

    public Position(int x, int y, int l, int w, int r, int c)
    {
        xCoordinate = x;
        yCoordinate = y;
        length = l;
        width = w;
        entityType = 0;
        row = r;
        col = c;
    }
    
    public Position(int x, int y, int l, int w, int t, int r, int c)
    {
        xCoordinate = x;
        yCoordinate = y;
        length = l;
        width = w;
        entityType = t;
        row = r;
        col = c;
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
    
    public void draw(Graphics g)
    {
        g.drawRect(xCoordinate, yCoordinate, length, width);
    }
}

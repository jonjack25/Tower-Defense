import java.awt.*;
public class Position extends Rectangle
{
    private final int length = 20, width = 20;
    private int xCoordinate, yCoordinate, entityType;

    public Position(int x, int y, int t)
    {
        xCoordinate = x;
        yCoordinate = y;
        entityType = t;
    }
    
    public int getXCoordinate()
    {
        return xCoordinate;
    }
    
    public int getYCoordinate()
    {
        return yCoordinate;
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

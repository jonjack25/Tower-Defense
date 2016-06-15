import java.awt.*;
public class Base extends Entity
{
    private Position pos;
    private int length;
    private int width;
    public Base(Position p, int l, int w)
    {
        pos = p;
        length = l;
        width = w;
    }
    
    public Position getPosition()
    {
        return pos;
    }
    
    public int getLength()
    {
        return length;
    }
    
    public int getWidth()
    {
        return width;
    }

    public void draw(Graphics g)
    {
        
    }
}
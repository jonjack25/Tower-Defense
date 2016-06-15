import java.awt.*;
import javax.swing.*;
public class Base extends Entity
{
    private Position pos;
    private int length;
    private int width;
    private final Image image = new ImageIcon("base.png").getImage();
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
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g2d.drawImage(image, getPosition().getXCoordinate(), getPosition().getYCoordinate(), (int)getPosition().getWidth() * width, (int)getPosition().getHeight() * length, null);
    }
}

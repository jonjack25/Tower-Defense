import java.awt.*;
public class Grid
{
    private Position[][] grid;
    public Grid(int w, int l)
    {
        grid = new Position[w][l];
        for(int i = 0; i < w; i++)
        {
            for(int j = 0; j < l; j++)
            {
                grid[w][l] = new Position(w, l, 0);
            }
        }
    }
    
    public void setFilled(Position p, Entity e)
    {
        
    }
    
    public boolean isEmpty(int x, int y)
    {
        return grid[x][y].isEmpty();
    }
    
    public void remove(int x, int y)
    {
        grid[x][y].setType(0);
    }
    
    public void draw(Graphics g)
    {
        for(Position[] p : grid)
            for(Position pos : p)
                pos.draw(g);
    }
}

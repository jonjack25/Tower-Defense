//GamePath - represents the path to be travelled by each Enemy. Has 3 arrays to determine turning points, axis,
//           and direction. Calculates next Position to travel to based on position in all 3 arrays

import java.util.*;
import java.util.List;
import java.awt.*;
public class GamePath
{
    //Private Fields
    
    public static int[] path = new int[]{16, 15, 8, 22, 28, 11, 31, 8, 37, 8, 40, 22, 49};;
    public static int[] axis = new int[]{1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1};
    public static int[] directions = new int[]{1, 1, -1, 1, 1, -1, 1, -1, 1, 1, 1, 1, 1};
    private int axisPosition;
    private int dirPosition;
    private int pathWidth;
    private int posInPath;
    private boolean turn = false;
    
    //Constructors
    
    public GamePath()
    {
        axisPosition = 0;
        dirPosition = 0;
        posInPath = 0;
        pathWidth = 4;
    }
    
    //Accessor Methods
    
    public int getAxis()
    {
        return axis[axisPosition];
    }
    
    public int getDirection()
    {
        return directions[dirPosition];
    }
    
    public boolean getTurn()
    {
        return turn;
    }
    
    //Methods
    
    //Precondition - p != null
    //Postcondition - returns the next point on the Grid based on the respective position in the path, 
    //                axis, and directions arrays
    public Point getNextMove(Position p)
    {
        turn = false;
        int xTarget = -1, yTarget = -1, targetExtend;
        
        //Handles every combination of axis and direction
        if(axis[axisPosition] == 1)
        {
            //Determines how far into the path an enemy travels before turning
            targetExtend = (int)(Math.random() * pathWidth);
            if(directions[dirPosition] == 1)
            {
                if(p.getCol() < path[posInPath] + targetExtend)
                {
                    //While the Position is behind a target Position, keep moving forward
                    yTarget = p.getCol() + directions[dirPosition];
                    xTarget = p.getRow();
                }
                else
                {
                    //If the turning point has been passed, advance the path, axis, and direction 
                    //targets
                    axisPosition++;
                    dirPosition++;
                    posInPath++;
                    yTarget = p.getCol();
                    xTarget = p.getRow();
                    turn = true;
                }
            }
            else if(directions[dirPosition] == -1)
            {
                if(p.getCol() > path[posInPath] - targetExtend)
                {
                    yTarget = p.getCol() + directions[dirPosition];
                    xTarget = p.getRow();
                }
                else
                {
                    axisPosition++;
                    dirPosition++;
                    posInPath++;
                    yTarget = p.getCol();
                    xTarget = p.getRow();
                    turn = true;
                }
            }
        }
        else if(axis[axisPosition] == -1)
        {
            targetExtend = (int)(Math.random() * pathWidth);
            if(directions[dirPosition] == 1)
            {
                if(p.getRow() < path[posInPath] + targetExtend)
                {
                    yTarget = p.getCol();
                    xTarget = p.getRow() + directions[dirPosition];
                }
                else
                {
                    axisPosition++;
                    dirPosition++;
                    posInPath++;
                    yTarget = p.getCol();
                    xTarget = p.getRow();
                    turn = true;
                }
            }
            else if(directions[dirPosition] == -1)
            {
                if(p.getRow() > path[posInPath] - targetExtend)
                {
                    yTarget = p.getCol();
                    xTarget = p.getRow() + directions[dirPosition];
                }
                else
                {
                    axisPosition++;
                    dirPosition++;
                    posInPath++;
                    yTarget = p.getCol();
                    xTarget = p.getRow();
                    turn = true;
                }
            }
        }

        return new Point(xTarget, yTarget);
    }
}

import java.util.*;
import java.util.List;
import java.awt.*;
public class GamePath
{
    public static int[] path = new int[]{16, 15, 8, 22, 28, 11, 31, 8, 37, 8, 40, 22, 49};;
    public static int[] axis = new int[]{1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1};
    public static int[] directions = new int[]{1, 1, -1, 1, 1, -1, 1, -1, 1, 1, 1, 1, 1};
    public static int PIXELS = 4;
    private int axisPosition;
    private int dirPosition;
    private int pathWidth;
    private int posInPath;
    private boolean turn = false;
    public GamePath()
    {
        axisPosition = 0;
        dirPosition = 0;
        posInPath = 0;
        pathWidth = 4;
    }
    
    public Point getNextMove(Position p)
    {
        turn = false;
        int xTarget = -1, yTarget = -1, targetExtend;
        if(axis[axisPosition] == 1)
        {
            targetExtend = (int)(Math.random() * pathWidth);
            if(directions[dirPosition] == 1)
            {
                if(p.getCol() < path[posInPath] + targetExtend)
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
//         if(axis[axisPosition] == -1)
//         {
//             targetExtend = (int)(Math.random() * pathWidth * Grid.POSITION_SIZE);
//             if(directions[dirPosition] == 1)
//             {
//                 if(p.getYCoord() < path[posInPath] * Grid.POSITION_SIZE + targetExtend)
//                 {
//                     yTarget = p.getYCoord() + directions[dirPosition] * PIXELS;
//                     xTarget = p.getXCoord();
//                 }
//                 else
//                 {
//                     axisPosition++;
//                     dirPosition++;
//                     posInPath++;
//                     yTarget = p.getYCoord();
//                     xTarget = p.getXCoord();
//                 }
//             }
//             else if(directions[dirPosition] == -1)
//             {
//                 if(p.getYCoord() > path[posInPath] * Grid.POSITION_SIZE - targetExtend)
//                 {
//                     yTarget = p.getYCoord() + directions[dirPosition] * PIXELS;
//                     xTarget = p.getXCoord();
//                 }
//                 else
//                 {
//                     axisPosition++;
//                     dirPosition++;
//                     posInPath++;
//                     yTarget = p.getYCoord();
//                     xTarget = p.getXCoord();
//                 }
//             }
//         }
//         else if(axis[axisPosition] == 1)
//         {
//             targetExtend = (int)(Math.random() * pathWidth * Grid.POSITION_SIZE);
//             if(directions[dirPosition] == 1)
//             {
//                 if(p.getXCoord() < path[posInPath] * Grid.POSITION_SIZE + targetExtend)
//                 {
//                     yTarget = p.getYCoord();
//                     xTarget = p.getXCoord() + directions[dirPosition] * PIXELS;
//                 }
//                 else
//                 {
//                     axisPosition++;
//                     dirPosition++;
//                     posInPath++;
//                     yTarget = p.getYCoord();
//                     xTarget = p.getXCoord();
//                 }
//             }
//             else if(directions[dirPosition] == -1)
//             {
//                 if(p.getXCoord() > path[posInPath] * Grid.POSITION_SIZE - targetExtend)
//                 {
//                     yTarget = p.getYCoord();
//                     xTarget = p.getXCoord() + directions[dirPosition] * PIXELS;
//                 }
//                 else
//                 {
//                     axisPosition++;
//                     dirPosition++;
//                     posInPath++;
//                     yTarget = p.getYCoord();
//                     xTarget = p.getXCoord();
//                 }
//             }
//         }
        return new Point(xTarget, yTarget);
    }
    
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
    
    public int oscillate(int p)
    {
        return -1;
    }
}

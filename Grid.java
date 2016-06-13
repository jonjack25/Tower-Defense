import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class Grid
{
    private int worldWidth = 50;
    private int worldHeight = 29;
    private int positionSize = 20;
    private int frameWidth;
    private int frameHeight;
    Image grassTile, pathTile;
    
    private Position[][] grid;
    //public ArrayList turrets = new ArrayList();
    
    private int[][] path =     {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                      {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                      {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                      {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                                      {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                                      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                                      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                                      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                                      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                                      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                                      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                                      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                                      {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                                      {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                                      {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                                      {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                                      {0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                                      {0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                                      {0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                                      {0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                                      {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                      {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                      {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                      {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
    public Grid(GameScreen screen)
    {
        grid = new Position[worldHeight][worldWidth]; 
        for(int i = 0; i < grid.length; i++)
        {   
            for(int j = 0; j < grid[0].length; j++)
            {
                grid[i][j] = new Position((screen.getWidth() / 2) - (worldWidth * positionSize / 2) + (i * positionSize), j * positionSize, positionSize, positionSize, j, i);
            }
        }
        grassTile = new ImageIcon("groundPixel.png").getImage();
        pathTile = new ImageIcon("pathPixel.jpg").getImage();
    }
    
    public Position getPosition(Point p)
    {
        return grid[(int)p.getX() / positionSize][(int)p.getY() / positionSize];
    }
    
    public int getSize()
    {
        return positionSize;
    }
    
    public void setFilled(Position p, Entity e)
    {
        if(e == null)
            p.setType(0);
        else if(e instanceof Turret)
            p.setType(1);
        else if(e instanceof Enemy)
            p.setType(2);
        else if(e instanceof Bullet)
            p.setType(3);
    }
    
    public boolean isValidPosition(Position p)
    {
       int x = p.getRow(), y = p.getCol();
       if(x == 0 || y == 0 || x == 1 || y == 1)
       {
          return false;
       }
       else
       {
           for(int i = x; i > x - 3; i--)
           {
               for(int j = y; j > x - 3; j--)
               {
                   if(!(grid[i][j].isEmpty()) || isInPath(grid[i][j]))
                   {
                       return false;
                   }
               }
           }
       }
       for(int i = x; i > x - 3; i--)
       {
           for(int j = y; j > x - 3; j--)
           {
               grid[i][j].setHover(true);
           }
       }
       return true;
    }
    
    public boolean isEmpty(Position p)
    {
        return p.isEmpty();
    }
    
    public boolean isInPath(Position p)
    {
        return path[p.getRow()][p.getCol()] == 1;
    }
    
    public boolean isInPath(int i, int j)
    {
        return true;
    }
    
    public void remove(Position p)
    {
        p.setType(0);
    }
    
    public Position[][] getGameGrid()
    {
        return grid;
    }
        
    public void draw(Graphics g)
    {
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                if(path[i][j] == 0)
                    grid[i][j].setGround(0);
                else if(path[i][j] == 1)
                    grid[i][j].setGround(1);
            }
        }
    }
    
    /*
    public static void addTurret()
    {
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                if(grid[i][j].contains(GameScreen.mouse))
                {
                    Position.checkTurretPlace(i, j);
                    if(Position.valid)
                    {
                        if(KeyHandler.basicTurretStatus == 1)
                        {
                            
                            /*
                            path[j][i] = 2;
                            path[j][i - 1] = 2;
                            path[j - 1][i] = 2;
                            path[j - 1][i - 1] = 2;
                            path[j - 2][i] = 2;
                            path[j][i - 2] = 2;
                            path[j - 2][i - 1] = 2;
                            path[j - 1][i - 2] = 2;
                            path[j - 2][i - 2] = 2;
                            */
                           /*
                        }
                        else if(KeyHandler.strongTurretStatus == 1)
                        {
                            path[j][i] = 3;
                            path[j][i - 1] = 3;
                            path[j - 1][i] = 3;
                            path[j - 1][i - 1] = 3;
                            path[j - 2][i] = 3;
                            path[j][i - 2] = 3;
                            path[j - 2][i - 1] = 3;
                            path[j - 1][i - 2] = 3;
                            path[j - 2][i - 2] = 3;
                        }
                        else if(KeyHandler.fastTurretStatus == 1)
                        {
                            path[j][i] = 4;
                            path[j][i - 1] = 4;
                            path[j - 1][i] = 4;
                            path[j - 1][i - 1] = 4;
                            path[j - 2][i] = 4;
                            path[j][i - 2] = 4;
                            path[j - 2][i - 1] = 4;
                            path[j - 1][i - 2] = 4;
                            path[j - 2][i - 2] = 4;
                        }
                        /*
                        path[j][i] = Game.numTur++;
                        path[j][i - 1] = Game.numTur++;
                        path[j - 1][i] = Game.numTur++;
                        path[j - 1][i - 1] = Game.numTur++;
                        path[j - 2][i] = Game.numTur++;
                        path[j][i - 2] = Game.numTur++;
                        path[j - 2][i - 1] = Game.numTur++;
                        path[j - 1][i - 2] = Game.numTur++;
                        path[j - 2][i - 2] = Game.numTur++;
                        */
                       /*
                    }
                    else
                        continue;
                }
            }
        }
                   /*
                    path[pX][pY] = 2;
                    path[pX + 1][pY] = 2;
                    path[pX][pY + 1] = 2;
                    path[pX + 1][pY + 1] = 2;
                    path[pX + 2][pY] = 2;
                    path[pX + 2][pY + 1] = 2;
                    path[pX][pY + 2] = 2;
                    path[pX + 1][pY + 2] = 2;
                    path[pX + 2][pY + 2] = 2;
                    
                    */
                   /*
                    path[pY][pX] = 2;
                    path[pY + 1][pX] = 2;
                    path[pY][pX + 1] = 2;
                    path[pY + 1][pX + 1] = 2;
                    path[pY + 2][pX] = 2;
                    path[pY + 2][pX + 1] = 2;
                    path[pY][pX + 2] = 2;
                    path[pY + 1][pX + 2] = 2;
                    path[pY + 2][pX + 2] = 2;
                    */
                    /*
                }
            }
        }
        */
        
    }
    

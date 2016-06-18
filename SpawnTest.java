

import java.util.*;
import java.util.List;
//import java.util.Timer;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SpawnTest
{
    public static int count = 5;
    public static void testSpawn()
    {
        List<Enemy> enemies = new ArrayList<Enemy>();
        Timer timer = new Timer(3000, new ActionListener()
        {
                public void actionPerformed(ActionEvent e)
                {
                    if(count > 0)
                    {   enemies.add(new BaseEnemy(new Position(0, 0, 0, 0, 0, 0)));
                        System.out.println("Spawned: " + enemies.size());
                        count--;
                    }
                }
        });
        timer.start();
    }
}

//Sound - The Sound Class takes in a string and plays a .wav file from the resources folder

import java.applet.*;

public class Sound
{
   //Private Fields
   private AudioClip music;
   
   //Constructors
   public Sound(String s)
   {
       music = Applet.newAudioClip(Sound.class.getResource(s));
   }
   
   //Methods
   
   //Postcondition - Loops the specified AudioClip
   public void start()
   {
       music.loop();
   }
   
   //Postcondition - Stops the specified AudioClip
   public void stop()
   {
       music.stop();
   }
}

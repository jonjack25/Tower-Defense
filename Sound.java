import java.applet.*;
import javax.sound.sampled.Clip;

public class Sound
{
   private AudioClip music;
   public Sound(String s)
   {
       music = Applet.newAudioClip(Sound.class.getResource(s));
   }
   
   public void start()
   {
       music.loop();
   }
   
   public void stop()
   {
       music.stop();
   }
}
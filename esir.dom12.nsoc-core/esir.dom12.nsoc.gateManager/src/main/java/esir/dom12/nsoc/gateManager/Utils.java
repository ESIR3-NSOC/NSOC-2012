package esir.dom12.nsoc.gateManager;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Utils
{
  public static Image load(String path)
  {
    BufferedImage image = null;

    try
    {
      InputStream in = Utils.class.getClassLoader().getResourceAsStream(path);
      image = ImageIO.read(in);
      in.close();
    }
    catch (Exception e)
    {
      throw new RuntimeException("Could not load image with path: " + path, e);
    }

    return image;

  }

  public static ImageIcon loadIcon(String path)
  {
    return new ImageIcon(load(path));

  }
  
  /**
   * Play wav, au or aiff sounds, no mp3
   * @param path : directory of sound
   */
  public static void loadAudioClip(String path)
  {
    try
    {
      URL url = Utils.class.getClassLoader().getResource(path);
      
      // Open audio clip and load from the audio input stream.
      AudioClip ac = Applet.newAudioClip(url);
      ac.play();
    }
    catch (Exception e)
    {
      throw new RuntimeException("Could not load clip with path: " + path, e);
    }
  }

}

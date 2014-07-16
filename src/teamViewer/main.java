package teamViewer;

import teamViewer.download.ScreenShoterable;
import teamViewer.download.SerializedBufferedImage;
import teamViewer.server.MyScreenStoter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.rmi.RemoteException;

/**
 * Created by student on 7/16/2014.
 */
public class main {
    public static void main(String[] args) throws IOException, AWTException, ClassNotFoundException {
        ScreenShoterable screenShoterable = new MyScreenStoter();
        SerializedBufferedImage serializedBufferedImage = screenShoterable.get();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("1"));
        oos.writeObject(serializedBufferedImage);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("1"));
        SerializedBufferedImage o = (SerializedBufferedImage) ois.readObject();

        ImageIO.write(o.getImage(), "png", new File("screenshot.png"));
        int p = 0;
    }
}

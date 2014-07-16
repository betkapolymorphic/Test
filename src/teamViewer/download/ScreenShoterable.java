package teamViewer.download;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by student on 7/16/2014.
 */
public interface ScreenShoterable extends Remote {
    SerializedBufferedImage get() throws RemoteException, AWTException;
}
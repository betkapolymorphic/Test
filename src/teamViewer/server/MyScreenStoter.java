package teamViewer.server;

import teamViewer.download.ScreenShoterable;
import teamViewer.download.SerializedBufferedImage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by student on 7/16/2014.
 */
public class MyScreenStoter extends UnicastRemoteObject implements ScreenShoterable {
    Robot robot = null;
    public MyScreenStoter() throws RemoteException, AWTException {
        robot = new Robot();
    }

    @Override
    public teamViewer.download.SerializedBufferedImage get() throws RemoteException, AWTException {
        System.out.println("creating shot");
        BufferedImage image = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

        return new SerializedBufferedImage(image);
    }
}

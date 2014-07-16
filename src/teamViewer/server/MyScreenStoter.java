package teamViewer.server;

import teamViewer.ScreenShoterable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by student on 7/16/2014.
 */
public class MyScreenStoter extends UnicastRemoteObject implements ScreenShoterable {
    Robot robot = null;
    protected MyScreenStoter() throws RemoteException, AWTException {
        robot = new Robot();
    }

    @Override
    public BufferedImage get() throws RemoteException, AWTException {
        BufferedImage image = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        return image;
    }
}

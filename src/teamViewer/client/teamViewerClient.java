package teamViewer.client;



import teamViewer.ScreenShoterable;
import teamViewer.download.teamViewerConstant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * Created by student on 7/16/2014.
 */
public class teamViewerClient {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        System.out.println("Connecting to server...");
        Scanner sc = new Scanner(System.in);
        Registry localhost = null;

            localhost = LocateRegistry.getRegistry(teamViewerConstant.HOST, teamViewerConstant.PORT);
            ScreenShoterable shoterable = (ScreenShoterable) localhost.lookup(teamViewerConstant.ID);
            System.out.println("Connected!");



        while (true) {
            System.out.println("Pres button + enter");
            sc.next();
            try{
                BufferedImage bufferedImage = shoterable.get();
                ImageIO.write(bufferedImage, "png", new File("/screenshot.png"));
                System.out.println("Saved!");
            } catch (AWTException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

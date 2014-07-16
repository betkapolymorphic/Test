package teamViewer.server;


import teamViewer.ScreenShoterable;
import teamViewer.download.teamViewerConstant;

import java.awt.*;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by student on 7/16/2014.
 */
public class teamViewerServer {
    public static void main(String[] args) {
        System.out.println("running server...");
        try {
            ScreenShoterable shoter = new MyScreenStoter();
            Registry registry = LocateRegistry.createRegistry(65000);

            registry.bind(teamViewerConstant.ID,shoter);
            System.out.println("Server runned!");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}

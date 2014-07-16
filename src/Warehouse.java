
import java.rmi.*;

/**
 * Created by student on 7/16/2014.
 */
public interface Warehouse extends Remote {
    abstract double getPrice(String description) throws RemoteException;
}

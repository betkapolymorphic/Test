import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by student on 7/16/2014.
 */
public class WarehouseServer {
    public static void main(String[] args) throws RemoteException, NamingException, InterruptedException, AlreadyBoundException, MalformedURLException {
        System.out.println("init cantralwarehouse...");

        MyWarehouse centralWarehouse = new MyWarehouse();
        Registry registry = LocateRegistry.createRegistry(65000);
        registry.bind(WarehouseConstant.WAREHOUSE_ID,centralWarehouse);
        centralWarehouse.prices.put("Oracle",-1.1);
        System.out.println("binding server to register...");



        System.out.println("Waiting for invocation from client...");
    }
}

import javax.naming.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by student on 7/16/2014.
 */
public class WarehouseClient {
    public static void main(String[] args) throws NamingException, RemoteException, NotBoundException {
        Context context = new InitialContext();

        System.out.println("rmi servers...");
        Registry localhost = LocateRegistry.getRegistry(WarehouseConstant.WAREHOUSE_HOST, WarehouseConstant.WAREHOUSE_PORT);
        for(String s :localhost.list()) {
            System.out.println(s);
        }
        Warehouse house = (Warehouse) localhost.lookup(WarehouseConstant.WAREHOUSE_ID);
        System.out.println("price"+house.getPrice("Oracle"));
        /*
        NamingEnumeration<NameClassPair> list = context.list("rmi://localhost");
        while (list.hasMoreElements()) {
            System.out.println(list.nextElement().getName());
        }
        System.out.println("-----------------------------------------------");

        System.out.println("connet to server");
        String url = "rmi://localhost:65000/central_warehouse";
        Warehouse centWarehouse = (Warehouse) context.lookup(url);
        String descr = "Nike";
        double price = centWarehouse.getPrice(descr);
        System.out.println(descr+" "+price);*/
    }
}

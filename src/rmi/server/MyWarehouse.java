package rmi.server;

import rmi.download.Warehouse;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by student on 7/16/2014.
 */
public class MyWarehouse extends UnicastRemoteObject implements Warehouse {
    protected MyWarehouse() throws RemoteException {
        //super();
        initPrices();
    }

    protected MyWarehouse(int port) throws RemoteException {
        super(port);
        initPrices();
    }

    protected MyWarehouse(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
        initPrices();
    }

    Map<String,Double> prices = new HashMap<String, Double>();
    void initPrices(){
        prices = new HashMap<String, Double>();
        prices.put("Oracle",39.2);
        prices.put("Adidas",73.2);
    }
    @Override
    public double getPrice(String description) throws RemoteException {
        Double price = prices.get(description);
        return price == null ? 0 : price;
    }
}

package Algorithm;
import java.awt.*;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created by Beta on 9/2/14.
 */
public class Clicker {
    public static void main(String[] args) throws InterruptedException, AWTException, IOException {
        int count = 50000;
        int fail = 0;
        long time = 0;
        for(int i=0;i<count;i++) {
            URLConnection connection
                    = new URL("http://colorchicken.esy.es/rus/parse.php?id=24801024014017").openConnection();
            connection.setDoInput(true);
            connection.connect();
            long start = System.nanoTime();
            Scanner scanner = new Scanner(connection.getInputStream());
            long end = System.nanoTime();
            long traceTime = end-start;
            time +=traceTime;
            if(scanner.hasNext());
            else {
                fail++;
            }
            System.out.println(" i = "+i);
            System.out.println("fail persantage  : " +fail + " " + count);
            double sec = (double)time/i / 1000000000.0;
            System.out.println("time average : " + sec);
        }


    }
}

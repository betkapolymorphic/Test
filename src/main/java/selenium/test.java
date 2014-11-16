package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created by Beta on 9/14/14.
 */
public class test {
    public static void main(String[] args) throws IOException {
        Scanner sc= new Scanner(new FileInputStream("zip.txt"));
        int  i = 1;
        while (sc.hasNext()){

            URLConnection connection = new URL("http://apdt.com/trainer-search/us/results/?zip=" +
                    sc.nextLine() + "&dist=100&cert=0").openConnection();
            connection.setDoInput(true);
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNext())
                System.out.println(scanner.nextLine());

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~"+i++);
        }
    }
}

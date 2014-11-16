package Algorithm;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Random;

/**
 * Task 5.2.5
 * Created by Beta on 8/26/14.
 */
public class ExpectedValuePermition {
    public static int[] generateArray(int size) {
        Random r = new Random();
        int[] A = new int[size];
        for(int i=0;i<A.length;i++) {
            A[i]=r.nextInt(100);
        }
        return A;
    }
    public static void main(String[] args) {


    }
}

package Algorithm;

import java.util.Random;

/**
 * Created by Beta on 11/16/14.
 */
public class StolenValues {

    static int getMaxVal(int[] values){
        if(values.length==1){
            return values[0];
        }else if(values.length==2){
            return Math.max(values[0],values[1]);
        }
        int val1 = 0;
        int val2 = values[0];
        int val3 = values[1];

        for(int i=2;i<values.length;i++){
            int val = Math.max(val1,val2);
            val1 = val2;
            val2 = val3;
            val3 = val+values[i];
        }

        return Math.max(val2,val3);
    }
    static int maxStolenValue(int[] values)
    {
        int length = values.length;
        if(length == 0)
            return 0;

        int value1 = values[0];
        if(length == 1)
            return value1;

        int value2 = Math.max(values[0], values[1]);
        if(length == 2)
            return value2;

        int value = 0;
        for(int i = 2; i < length; ++i)
        {
            value = Math.max(value2, value1 + values[i]);
            value1 = value2;
            value2 = value;
        }

        return value;
    }

    public static void main(String[] args) {
        Random r = new Random();

        int max = (int) Math.sqrt(Integer.MAX_VALUE);
        for(int i=0;i<10000000;i++){
            if(i%10000==0){
                System.out.println(i);
            }
            int[] values = new int[Math.abs(r.nextInt(max))+2];
            for(int j=0;j<values.length;j++){
                values[j]= Math.abs(r.nextInt(max));
            }
            boolean q = getMaxVal(values) == maxStolenValue(values);
            if(!q){
                System.out.println("!");
                boolean  p = getMaxVal(values) == maxStolenValue(values);
            }
        }
    }
}

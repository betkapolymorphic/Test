package Algorithm;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Beta on 10/16/14.
 */
public class TEST {
    public static ArrayList<ArrayList<Integer>> load(int i){
        ArrayList<ArrayList<Integer>> out = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        try {
            BufferedReader reader
                    = new BufferedReader(new FileReader(i+".mp3"));
            int symb = 0;
            while(symb!=-1){
                for(int j=0;j<i;j++){
                    symb = reader.read();
                    list.add(new Integer(symb));
                }
                out.add((ArrayList<Integer>) list.clone());
                list.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;

    }
    public static void main(String[] args) throws IOException {

        ArrayList<ArrayList<Integer>> load = load(10);
        int count = 0;
        System.out.println(load.size());
        for(ArrayList<Integer>i : load){
            for(Integer integer : i){
                System.out.print(integer + " ");
            }
            count++;
            System.out.println();
            if(count%3==0){

                System.out.println("~~~~~~~~~~~~~~");
            }
        }

    }
}

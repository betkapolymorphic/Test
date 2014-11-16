package Algorithm;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Beta on 10/15/14.
 */
public class GenerateHard {
    static int[] indexes;
    static Random r = new Random();
    static void shuffleIndex(){

        for(int i = 0;i<indexes.length;i++){
            int randPos = r.nextInt(indexes.length - i);
            int tmp = indexes[i+randPos];
            indexes[i+randPos] = indexes[i];
            indexes[i] = tmp;
        }
    }
    static void shuffleIndex(ArrayList<Integer>indexes){
        Random r  =new Random();
        for(int i = 0;i<indexes.size();i++){
            int randPos = r.nextInt(indexes.size() - i);
            int tmp = indexes.get(i+randPos);
            indexes.set(i+randPos,indexes.get(i));
            indexes.set(i,tmp);
        }
    }
    static void generate(int i,boolean[] used,
                         ArrayList<Integer> permut,ArrayList<ArrayList<Integer>> out){
        if(out.size()>3000000){
            return;
        }
        if(permut.size() == i ){
            out.add((ArrayList<Integer>) permut.clone());
        }
        for(int j = 0 ;j<i;j++){
            //shuffleIndex();


            if(!used[j] && j!=permut.size()){
                permut.add(j);
                used[j] = true;
                generate(i,used,permut,out);
                used[j] = false;
                permut.remove(permut.size()-1);
            }
        }

    }
    private static void generateTablePermut(int n,boolean[] used,
                                           ArrayList<Integer> permut,ArrayList<ArrayList<Integer>> out){
        if(permut.size() == n){
            out.add((ArrayList<Integer>) permut.clone());
        }
        for(int j = 0 ;j<used.length;j++){

            if(!used[j]){
                permut.add(j);
                used[j] = true;
                generateTablePermut(n, used, permut, out);
                used[j] = false;
                permut.remove(permut.size()-1);
            }
        }
    }
    private static boolean isGoodPermut(ArrayList<ArrayList<Integer>> AllDigitPermutation,
                                       ArrayList<Integer> combinations){


        for(int combinationIndex=1;combinationIndex < combinations.size();combinationIndex++){
            ArrayList<Integer> current = AllDigitPermutation.get(combinations.get(combinationIndex));
            for(int prevIndex = 0;prevIndex<combinationIndex;prevIndex++){
                ArrayList<Integer> prev = AllDigitPermutation.get(combinations.get(prevIndex));
                for(int i = 0;i<current.size();i++){
                    if(current.get(i).equals(prev.get(i))){
                        return false;
                    }
                }

            }
        }
        return true;
    }
    public static void generateTable(ArrayList<ArrayList<Integer>> lists,int h){
        if(lists.size()!=0){
            boolean[] booleans =new boolean[lists.size()];
            ArrayList<ArrayList<Integer>> out = new ArrayList<ArrayList<Integer>>();
            generateTablePermut(h,booleans,new ArrayList<Integer>(),out);
            int count = 0;
            for(ArrayList<Integer> l :out){
                if(!isGoodPermut(lists,l)){
                    continue;
                }
                System.out.println("~~~~~~~~~~~~~");
                for(Integer integer :l){
                    for(Integer val :lists.get(integer)){
                        System.out.print(val+" ");
                    }
                    System.out.println();
                }
                System.out.println("~~~~~~~~~~~~~"+count++);


            }
        }
    }

    static boolean isREALLYGOODPERMUT(ArrayList<ArrayList<Integer>> AllDigitPermutation,
                                      ArrayList<Integer> combinations){
        for(int i=0;i<AllDigitPermutation.size();i++){
            ArrayList<Integer> current = AllDigitPermutation.get(i);
            for(int j=0;j<current.size();j++){

                /*int relVal = combinations.get(j);
                if(current.get(relVal).equals(j)){
                    return false;
                }*/
                if(current.get(j).equals(combinations.get(j))){
                    return false;
                }
            }
        }
        //for(int i)

        return true;
    }
    public static ArrayList<ArrayList<Integer>>generate(){
        ArrayList<Integer> integers = new ArrayList<Integer>();

        for(int c=0;c<Glists.size();c++){
            integers.add(c);
        }
        ArrayList<ArrayList<Integer>> AllDigitPermutation = new ArrayList<ArrayList<Integer>>();
        shuffleIndex(integers);


        for(int j = 0;j<integers.size() && AllDigitPermutation.size()<3;j++){
            ArrayList<Integer> candidate = Glists.get(integers.get(j));
            if(isREALLYGOODPERMUT(AllDigitPermutation,candidate)){
                AllDigitPermutation.add(candidate);
            }
        }
        return AllDigitPermutation;
    }

    public static void generateANDSER(){
        ArrayList<Integer> integers = new ArrayList<Integer>();

        for(int c=0;c<Glists.size();c++){
            integers.add(c);
        }
        ArrayList<ArrayList<Integer>> AllDigitPermutation = new ArrayList<ArrayList<Integer>>();
        shuffleIndex(integers);


        for(int j = 0;j<integers.size() && AllDigitPermutation.size()<3;j++){
            ArrayList<Integer> candidate = Glists.get(integers.get(j));
            if(isREALLYGOODPERMUT(AllDigitPermutation,candidate)){
                AllDigitPermutation.add(candidate);
            }
        }
    }
    private static  ArrayList<ArrayList<Integer>> Glists;//GLOBAL VAL
    public static void compute(int i,int J){
        boolean[] b = new boolean[i];
        indexes = new int[i];
        for(int j = 0;j<i;++j){
            b[j] = false;
            indexes[j] = j;
        }

        Glists = new ArrayList<ArrayList<Integer>>();
        generate(i,b,new ArrayList<Integer>(),Glists);
    }
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
    public static void init(int i,int J){
        boolean[] b = new boolean[i];
        indexes = new int[i];
        for(int j = 0;j<i;++j){
            b[j] = false;
            indexes[j] = j;
        }
        Glists = new ArrayList<ArrayList<Integer>>();
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        //PrintWriter pw =new PrintWriter(new FileOutputStream("10.mp3"));
        compute(11,3);
        //init(10,3);
        //Glists = load(10);

        /*for (ArrayList<Integer> integers : Glists) {
            for(Integer i : integers){
                pw.print((char)i.intValue());
            }
        }
        pw.close();*/

        System.out.println("COmputed");
        /*for(int i=1;i<=10;i++){
            for(int j=1;j<=3;j++){
                compute(i,j);
                /*ObjectOutputStream oos
                        = new ObjectOutputStream(new FileOutputStream("outSer/"+i+"_"+j+".ser"));
                oos.writeObject(Glists);
                oos.close();

                System.out.println(i+"_"+j);
            }
        }*/
        final HashSet< ArrayList<ArrayList<Integer>>> toWrite = new HashSet<ArrayList<ArrayList<Integer>>>();
        final boolean []interrupt = new boolean[1];
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("STARTED!");
                while (true){
                    Scanner sc = new Scanner(System.in);
                    String next = sc.next();
                    if(next.equals("exit")){
                        interrupt[0]=true;
                    }
                    System.out.println(toWrite.size());
                }
            }
        });
        t.start();
        while (toWrite.size()<=100000 && !interrupt[0]){
            //System.out.println(toWrite.size());
            ArrayList<ArrayList<Integer>> generate = generate();
            toWrite.add((ArrayList<ArrayList<Integer>>) generate.clone());
        }
        PrintWriter pw =new PrintWriter(new FileOutputStream("11s.mp3"));
        for(ArrayList<ArrayList<Integer>> lists : toWrite){
            for(ArrayList<Integer> list : lists){
                for(Integer integer : list){
                    pw.print((char)integer.intValue());
                }
            }
        }
        pw.close();
        System.out.println("WRITED");


        t.join();
        Thread.sleep(999999999);



        /*int i = 11;
        int J = 3;
        boolean[] b = new boolean[i];
        indexes = new int[i];
        for(int j = 0;j<i;++j){
            b[j] = false;
            indexes[j] = j;
        }
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();



        generate(i,b,new ArrayList<Integer>(),lists);
        ArrayList<Integer> integers = new ArrayList<Integer>();

        for(int c=0;c<lists.size();c++){
            integers.add(c);
        }
        ArrayList<ArrayList<Integer>> AllDigitPermutation = new ArrayList<ArrayList<Integer>>();
        shuffleIndex(integers);


        for(int j = 0;j<integers.size() && AllDigitPermutation.size()<3;j++){
            ArrayList<Integer> candidate = lists.get(integers.get(j));
            if(isREALLYGOODPERMUT(AllDigitPermutation,candidate)){
                AllDigitPermutation.add(candidate);
            }
        }


        for(ArrayList<Integer> l :AllDigitPermutation){
            for(Integer integer :l){
                System.out.print(integer+" ");
            }
            System.out.println();
        }*/
        //generateTable(lists,J);
        /*for(ArrayList<Integer> l :lists){
            for(Integer integer :l){
                System.out.print(integer+" ");
            }
            System.out.println();
        }*/

        //correct(lists);
        //correct(lists);

        /*for(ArrayList<Integer> l :lists){
            for(Integer integer :l){
                System.out.print(integer+" ");
            }
            System.out.println();
        }*/



    }
}

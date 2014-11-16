package Algorithm;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Beta on 10/15/14.
 */
public class GenerateAndSave {
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
    static int count = 0;
    static ArrayList<ArrayList<Integer>> AllDigitPermutation = new ArrayList<ArrayList<Integer>>();
    private static void generateTablePermut(int n,boolean[] used,
                                            ArrayList<Integer> permut,
                                ArrayList<ArrayList<Integer>> comb){
        Stack<Integer> stack = new Stack<Integer>();
        int startPosition = 0;
        stack.push(startPosition);
        used[startPosition]=true;
        permut.add(startPosition);
        int maxVal = startPosition;
        int c = 0;

        while (maxVal<used.length){
            boolean doSMTH = false;
            if(permut.size() == n){
                AllDigitPermutation.clear();
                for(int j = 0;j<n;j++){
                    ArrayList<Integer> candidate = Glists.get(permut.get(j));
                    if(isREALLYGOODPERMUT(AllDigitPermutation,candidate)){
                        AllDigitPermutation.add(candidate);
                    }else{
                        if(j==1 && permut.get(j)<used.length){
                            permut.set(j,permut.get(j)+1);
                            c = stack.pop();
                            used[c]=false;
                            permut.remove(permut.size()-1);
                            c++;
                            used[j] = true;
                            stack.pop();
                            stack.push(permut.get(j));
                            break;
                        }

                    }
                }
                if(AllDigitPermutation.size()==n){
                    /*for(ArrayList<Integer> list: AllDigitPermutation){
                        for(Integer integer :list){
                            System.out.print(integer + " ");
                        }
                        System.out.println();
                    }*/
                    count++;
                    comb.add((ArrayList<Integer>) permut.clone());
                    int p=1;
                }

                c = stack.pop();
                used[c]=false;
                permut.remove(permut.size()-1);
                c++;
            }

            for(int j = c ;j<used.length;j++){
                if(!used[j]){
                    if(permut.size()>0){
                        if(permut.get(permut.size()-1)>j)
                            continue;
                    }
                    doSMTH = true;
                    permut.add(j);
                    used[j] = true;
                    stack.push(j);
                    break;
                }
            }



            if(c == used.length){
                doSMTH = true;
                int val = permut.get(permut.size()-1);
                used[val]=false;
                permut.set(permut.size()-1,val+1);
                used[val+1] = true;
                c=0;
            }
            if(!doSMTH){
                startPosition = ++maxVal;
                if(maxVal>=used.length){
                    return;
                }
                for(Integer i : permut){
                    used[i]=false;
                }

                stack.clear();
                permut.clear();
                stack.push(startPosition);
                used[startPosition]=true;
                permut.add(startPosition);

            }

        }
    }

    static boolean isREALLYGOODPERMUT(ArrayList<ArrayList<Integer>> AllDigitPermutation,
                                      ArrayList<Integer> combinations){
        for (ArrayList<Integer> current : AllDigitPermutation) {
            for (int j = 0; j < current.size(); j++) {
                if (current.get(j).equals(combinations.get(j))) {
                    return false;
                }
            }
        }
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
    public static void main(String[] args) throws IOException {
        //compute(7,3);

        compute(9,3);
        boolean[]b = new boolean[Glists.size()];
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    Scanner sc = new Scanner(System.in);
                    sc.next();
                    System.out.println(count);
                }
            }
        }).start();
        generateTablePermut(3,b,new ArrayList<Integer>(),lists);
            int  p =2;




        ArrayList<ArrayList<Integer>> generate = generate();
        /*for(ArrayList<Integer> l :generate){
            for(Integer integer :l){
                System.out.print(integer+" ");
            }
            System.out.println();
        }*/
        //generate = generate();


    }
}

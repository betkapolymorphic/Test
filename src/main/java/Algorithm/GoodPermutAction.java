package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Beta on 10/15/14.
 */
public class GoodPermutAction {
    static void shuffleIndex(ArrayList<Integer>indexes){
        Random r  =new Random();
        for(int i = 0;i<indexes.size();i++){
            int randPos = r.nextInt(indexes.size() - i);
            int tmp = indexes.get(i+randPos);
            indexes.set(i+randPos,indexes.get(i));
            indexes.set(i,tmp);
        }
    }
    static int[] indexes;
    static   Random r = new Random();
    static void shuffleIndex(){

        for(int i = 0;i<indexes.length;i++){
            int randPos = r.nextInt(indexes.length - i);
            int tmp = indexes[i+randPos];
            indexes[i+randPos] = indexes[i];
            indexes[i] = tmp;
        }
    }
    static boolean containInColumn(ArrayList<ArrayList<Integer>> lists,int row,
                                   int col,int val){
        for(int i = 0;i<row;i++){
            if(lists.get(i).get(col).equals(val)){
                return true;
            }
        }
        return false;
    }
    static void correct(ArrayList<ArrayList<Integer>> lists){
        for(int i = 1;i<lists.size();i++){

            ArrayList<Integer> prev_integers = lists.get(i-1);
            ArrayList<Integer> integers = lists.get(i);

            for(int j = 0;j<integers.size();j++){
                boolean flag = false;
                if(integers.get(j) == j){
                    shuffleIndex();
                    for(int q = 0;q<indexes.length;q++){
                        if(indexes[q]!=j){
                            int tmp = integers.get(j);
                            integers.set(j,integers.get(indexes[q]));
                            integers.set(indexes[q],tmp);
                        }
                    }
                }
                //integers.get(j).equals(prev_integers.get(j))
                if(containInColumn(lists,i,j,integers.get(j))){

                    for(int count = 0;count<integers.size();count++){

                        if(integers.get(j)==count){
                            continue;
                        }

                        if(!containInColumn(lists, j, count,integers.get(count))){
                            int tmp = integers.get(j);
                            integers.set(j,integers.get(count));
                            integers.set(count,tmp);
                            //j=0;
                        }

                    }
                }

            }
        }
    }


    static boolean isGoodPlaceToSwap(ArrayList<ArrayList<Integer>> prevPermut
            ,int Index1,int Val1,int Index2,int Val2){
        for(int i =0 ;i<prevPermut.size();i++){
            if(prevPermut.get(i).get(Index2).equals(Val1)){
                return false;
            }
            if(prevPermut.get(i).get(Index1).equals(Val2)){
                return false;
            }
        }
        if(Val2==Index1 || Val1 == Index2){
            return false;
        }
        return true;
    }
    static void fixError(ArrayList<ArrayList<Integer>> prevPermut,
                         ArrayList<Integer> error,int prevPermutRow){
        for(int i =0 ;i<error.size();i++){
            for(int j=0;j<prevPermutRow;j++){
                try{
                    prevPermut.get(j).get(i).equals(error.get(i));
                }catch (Exception e){
                    int p =1;
                }
                if(prevPermut.get(j).get(i).equals(error.get(i))){
                    for(int pretIndex = 0;pretIndex<error.size();pretIndex++){
                        if(isGoodPlaceToSwap(prevPermut,pretIndex,error.get(pretIndex),
                                i,error.get(i))){
                            int tmp = error.get(i);
                            error.set(i,error.get(pretIndex));
                            error.set(pretIndex,tmp);
                        }
                    }

                }
            }
        }
    }
    static ArrayList<Integer> createPermut(ArrayList<ArrayList<Integer>> prevPermut,int n){
        ArrayList<Integer> toReturn = new ArrayList<Integer>();
        ArrayList<Integer> randIndexes = new ArrayList<Integer>();

        for(int i = 0;i<n;i++){
            randIndexes.add(i);
        }
        shuffleIndex(randIndexes);

        while(!randIndexes.isEmpty()){
            boolean doSMTH = false;
           for(int i=0;i<randIndexes.size();i++){
               boolean flag = false;
               int pretended = randIndexes.get(i);
               //ПРоходимся по предыдущим записям
               for(int j=0;j<prevPermut.size();j++){
                   if(!flag && prevPermut.get(j).get(toReturn.size()).equals(pretended)){
                       flag = true;
                   }
               }
               if(pretended==toReturn.size()){
                   if(toReturn.size()==0)
                        continue;
                   else{
                       toReturn.add(toReturn.size(),pretended);
                       randIndexes.remove(i);
                       fixError(prevPermut,toReturn,prevPermut.size());
                       continue;
                   }
               }

               if(!flag){

                   toReturn.add(toReturn.size(),pretended);
                   randIndexes.remove(i);
               }else{
                   toReturn.add(toReturn.size(),pretended);
                   randIndexes.remove(i);
                   fixError(prevPermut,toReturn,prevPermut.size());
               }

           }


        }
        return  toReturn;
    }
    static boolean isCorrectPermut( ArrayList<ArrayList<Integer>> out){
        for(int i=0;i<out.size();i++){
            for(int j = 0;j<out.get(i).size();j++){
                for(int q = 0;q<i;q++){
                    if(out.get(i).get(j).equals(out.get(q).get(j))){
                        return false;
                    }
                    if(out.get(i).get(j).equals(j)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    static void generate(int I,int J){
        int n  = I;
        indexes = new int[n];
        ArrayList<Integer> integers = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> out  = new ArrayList<ArrayList<Integer>>();

        for(int i = 0;i<n;i++){
            integers.add(i);
        }
        for(int i =0 ;i<J;i++){
            out.add(createPermut(out,n));
        }
        while(!isCorrectPermut(out) ){
            out.clear();
            out.add(createPermut(out,n));
        }

        for(ArrayList<Integer> list : out){
            for(Integer integer : list){
                System.out.print(integer + " ");
            }
            System.out.println();
        }


        //for(int i = 0;i<)
    }

    public static void main(String[] args) {
        for(int i =0;i<10;i++){
            generate(5,3);
            System.out.println("~~~~~~~~~~~~~~~");
        }
    }
}

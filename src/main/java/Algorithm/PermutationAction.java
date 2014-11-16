package Algorithm;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Beta on 10/15/14.
 */
public class PermutationAction {
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
    static void generate(int i,boolean[] used,int compareSize,
                         ArrayList<Integer> permut,ArrayList<Integer> out){

        if(permut.size() == i){
               out.addAll((ArrayList<Integer>) permut.clone());

        }
        for(int c = 0 ;c<i;c++){
            shuffleIndex();
            int j = indexes[c];

            if(!used[j] && j!=permut.size()-1){
                permut.add(j);
                used[j] = true;
                generate(i,used,compareSize,permut,out);
                used[j] = false;
                permut.remove(permut.size()-1);
            }
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
                //integers.get(j).equals(prev_integers.get(j))
                if(containInColumn(lists,i,j,integers.get(j))){

                    for(int count = 0;count<integers.size();count++){
                        if(i==2){
                            int p = 1;
                        }
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

    public static void main(String[] args) {
        int i = 3;
        boolean[] b = new boolean[i];
        indexes = new int[i];
        for(int j = 0;j<i;++j){
            b[j] = false;
            indexes[j] = j;
        }
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> out = new ArrayList<Integer>();

            for(int c = 0;c<3;c++){
                generate(i,b,c,new ArrayList<Integer>(),out);
                lists.add((ArrayList<Integer>) out.clone());

                out.clear();
            }
            //correct(lists);
            //correct(lists);

        for(ArrayList<Integer> l :lists){
            for(Integer integer :l){
                System.out.print(integer+" ");
            }
            System.out.println();
        }
    }
}

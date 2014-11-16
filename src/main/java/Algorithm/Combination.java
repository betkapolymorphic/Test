package Algorithm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Beta on 10/15/14.
 */
public class Combination implements Serializable{
    static int count = 0;
    private static void generateTablePermut(int n,boolean[] used,
                                            ArrayList<Integer> permut){

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
                    doSMTH = true;
                   /*for(int i=0;i<permut.size();i++){
                       System.out.print(permut.get(i) + " ");
                   }*/
                    //System.out.println();
                    count++;
                    c = stack.pop();
                    used[c]=false;
                    permut.remove(permut.size()-1);
                    c++;
                    //continue;
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
    public static void generatePermut(int n,boolean[] used,
                                      ArrayList<Integer> permut){
        if(permut.size()==n){
            for(int i=0;i<permut.size();i++){
                System.out.print(permut.get(i)+" ");
            }
            System.out.println();
        }
        for(int i=0;i<used.length;i++){
            if(!used[i]){
                if(permut.size()>0){
                       if(permut.get(permut.size()-1)>i)
                           continue;
                }
                used[i]=true;
                permut.add(i);
                generatePermut(n, used, permut);
                used[i]=false;
                permut.remove(permut.size()-1);
            }
        }
    }
    public static void main(String[] args) {
        int n = 3;
        boolean[] b = new boolean[800];
        generateTablePermut(n,b,new ArrayList<Integer>());
        System.out.println(count);
    }
}

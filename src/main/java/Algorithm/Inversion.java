package Algorithm;

import java.util.Random;

public class Inversion {
    static int permition_count = 0;
    public static void merger(int[] arr,int l,int mid,int r) {
        int pos1 = l;
        int pos2 = mid + 1;
        int [] A = new int[r - l + 1];
        int pos = 0;

        while (pos1 <= mid || pos2<=r) {
            if(pos1<= mid && pos2 <= r && arr[pos1] < arr[pos2] ) {
                permition_count += r - pos2 + 1;

            }
            if((pos2 > r) || (pos1<= mid && arr[pos1] < arr[pos2])) {
                A[pos++]  = arr[pos1++];
                // System.out.print("_"+A[pos-1]);
            } else {
                A[pos++] = arr[pos2++];
                //  System.out.print("+"+A[pos-1]);
            }
        }
        System.arraycopy(A,0,arr,l,A.length);
    }
    public static void permitions(int[] arr,int l
            ,int r) {
        if(l>=r) {
            return;
        }
        int mid = (l+r)/2;
        permitions(arr , l , mid);
        permitions(arr, mid + 1 , r);
        merger(arr,l ,mid , r);


    }
    static int getPermition(int []arr) {
        int count = 0;
        for(int  i = 0;i < arr.length;i++) {
            for(int j = i+1;j<arr.length;j++) {
                if(arr[i] < arr[j]) {
                    count++;
                }
            }
        }
        return count;
    }
    public static void main(String[] args) {

        Random r = new Random();
        int n = 20000;
        while (n-->0){
            int[] A=  new int[500 + r.nextInt(138)];
            for(int i=0;i<A.length;i++) {
                A[i] = r.nextInt();
            }
            permition_count = 0;
            permitions(A,0,A.length-1);
            assert(permition_count != getPermition(A));
        }

        System.out.println(permition_count);

    }
}
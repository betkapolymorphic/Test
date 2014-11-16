package Algorithm;

/**
 * Created by Beta on 11/16/14.
 */
public class CutRod {
    static int DynamicCutRod(int l,int[]prices){
        int[] A = new int[l+1];

        A[0] = 0;

        for(int i=0;i<l;i++){
            int max = Integer.MIN_VALUE;
            for(int j=0;j<=i;j++){
                if(max < Math.max(prices[i],prices[j]+A[i-j])){
                    max = Math.max(prices[i],prices[j]+A[i-j]);
                }
            }
            A[i+1] = max;
        }

        return A[l];
    }
    public static void main(String[] args) {
        int[] price = {1,5,8,9,10,17,17,20,24,30};
        System.out.println(DynamicCutRod(5,price));
    }
}

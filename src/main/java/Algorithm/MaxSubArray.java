package Algorithm;

/**
 * Created by Beta on 8/14/14.
 */
public class MaxSubArray {
    static class SubArrayStructure
    {
        int left;
        int right;
        int sum;

        @Override
        public String toString() {
            return "SubArrayStructure{" +
                    "left=" + left +
                    ", right=" + right +
                    ", sum=" + sum +
                    '}';
        }

        SubArrayStructure(int left, int right, int sum) {
            this.left = left;
            this.right = right;
            this.sum = sum;
        }
    }
    public static SubArrayStructure find_max_crossing_array(int[] arr,int l,int m,int r) {

        int left_index = l;
        int max_left_sum = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = m; i >= l; i-- ) {
            sum += arr[i];
            if(sum > max_left_sum) {
                max_left_sum = sum;
                left_index = i;
            }
        }

        sum = 0;
        int right_index = r;
        int max_right_sum = Integer.MIN_VALUE;
        for(int i = m + 1;i <= r; i++) {
            sum += arr[i];
            if(sum > max_right_sum) {
                max_right_sum = sum;
                right_index = i;
            }
        }

        return new SubArrayStructure(left_index,right_index,max_left_sum+max_right_sum);
    }
    public static SubArrayStructure find_max_subarray(int[] arr,int l,int r) {
        if(l>=r) {
            return new SubArrayStructure(l, r ,arr[l]);
        }

        int mid = (l + r)/2;

        SubArrayStructure subarray_left = find_max_subarray(arr, l, mid);
        SubArrayStructure subarray_right = find_max_subarray(arr, mid + 1, r);
        SubArrayStructure max_crossing_array = find_max_crossing_array(arr, l, mid, r);

        if(subarray_left.sum > subarray_right.sum
                && subarray_left.sum > max_crossing_array.sum) {
            return subarray_left;
        } else if (subarray_right.sum > subarray_left.sum &&
                subarray_right.sum > max_crossing_array.sum) {
            return subarray_right;
        } else {
            return max_crossing_array;
        }
    }
    public static void main(String[] args) {
        int []A = {2,-6,1,3,-1,5,-2,9,11,-1};
        System.out.println(find_max_subarray(A, 0, A.length - 1));
    }
}

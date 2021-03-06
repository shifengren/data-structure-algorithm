package algorithm.search;

import java.util.Scanner;

/**
 * 题目：输入n个整数，输出其中最小的k个。
 * 例如输入1，2，3，4，5，6，7和8这8个数字，则最小的4个数字为1，2，3和4。
 */
public class FindMinimalKElements {

    /**
     * Utilize quicksort idea
     *
     * @param ary
     * @return index range
     */
    static int findKMinElems(int[] ary, int length, int k) {
        if(k > length || null == ary) return -1;

        int end = partition(ary,0, length-1, k-1);
        while(true){
            if(end == k) break;
            else if(end > k){
                end = partition(ary, 0, end, k-1);
            }else{
                end = partition(ary, end, length-1, k-1);
            }
        }
        return end;
    }

    static int partition(int[] ary, int left, int right, int k) {
        int tmp;
        // simple use media data as pivot
        int pivot = ary[k];
        while (left <= right) {
            while (ary[left] < pivot) left++;
            while (ary[right] > pivot) right--;
            if (left <= right) {
                tmp = ary[left];
                ary[left] = ary[right];
                ary[right] = tmp;
                ++left;
                --right;
            }
        }
        return left;
    }


    private static int findKth(int[] array, int k) {
        int i = 0, j = array.length - 1;
        int p;
        while (true) {
            p = partition(array, i, j);
            if (p > k) j = p - 1;
            else if (p < k) i = p + 1;
            else break;
        }
        return array[p];
    }
    private static int partition(int[] array, int low, int high) {
        // use element at position `low` as privot element
        int pivot = array[low];
        int i = low;
        int j = high + 1;
        while (i < j) {
            for (i++; i < high && array[i] < pivot; i++) ;
            for (j--; j > low && array[j] > pivot; j--) ;
            if (i < j) swap(array, i, j);
        }
        swap(array, low, j);
        return j;
    }
    private static void swap(int[] array, int i, int j) {
        int t = array[j];
        array[j] = array[i];
        array[i] = t;
    }

    public static void main(String[] args) {
        Scanner rd = new Scanner(System.in);

//        int n = rd.nextInt();
//        int[] ary = new int[n];
//        for (int i = 0; i < n; ++i) {
//            ary[i] = rd.nextInt();
//        }
        int[] ary = new int[]{6, 4, 3, 2, 1, 5};
        int end = findKMinElems(ary, ary.length, 4);
        System.out.println("end:" + end);
        for(int i=0; i<end; ++i){
            System.out.print(ary[i] +",");
        }

//        rd.close();
    }
}

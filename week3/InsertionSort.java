package week3;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Jake on 2/6/2017.
 */
public class InsertionSort {

    public static void sort(int[] array){
        int len = array.length;

        for(int i = 0; i<len; i++){
            for(int j = i; j>0; j--){
                if(array[j] < array[j-1]){
                    exchange(array, j-1, j);
                }
            }
        }

    }

    public static void exchange(int[] array, int i, int j){
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }


    public static int[] generateArray(int n){
        int[] array = new int[n];

        Random r = new Random();
        for(int i=0; i<n; i++){
            array[i] = r.nextInt(100);
        }

        return array;
    }

    public static void main(String[] args){
        int[] array = generateArray(15);
        System.out.println(Arrays.toString(array));
        sort(array);
        System.out.println(Arrays.toString(array));


    }

}

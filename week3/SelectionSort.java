package week3;

import edu.princeton.cs.algs4.Selection;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Jake on 2/4/2017.
 */
public class SelectionSort{

    public static void sort(int[] array) {
        int i = 0;
        int n = array.length;
        int min;

        while (i < n) {
            min = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            exchange(array, i, min);
            i++;
        }
    }

    private static void exchange(int[] array, int i, int j){
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

        int[] array = SelectionSort.generateArray(50);

        System.out.println(Arrays.toString(array));
        SelectionSort.sort(array);

        System.out.println(Arrays.toString(array));
    }
}

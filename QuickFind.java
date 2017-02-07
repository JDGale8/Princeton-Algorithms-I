/**
 * Created by Jake on 1/28/2017.
 *
 * Quick Find
 * Very expensive, takes O(N^2) time and quadratic is not very good
 */
public class QuickFind {
    private int[] array;

    public QuickFind(int n){
        array = new int[n];
        for(int i=0; i<n; i++){
            array[i] = i;
        }
    }

    private boolean connected(int p, int q){
        return array[p] == array[q];
    }

    public void union(int p, int q){
        if(!connected(p, q)){
            int qValue = array[q];
            int pValue = array[p];

            for(int i=0; i<array.length; i++){

                if(array[i] == pValue){
                    array[i] = qValue;
                }
            }
        }

    }
}

/**
 * Created by Jake on 1/28/2017.
 *
 * How do we want to get rid of the O(N^2) problem with Quick Find
 *
 * This algorithm, instead of using an id array to show which are connected where
 * we can have a tree based system, and for each of the ids, show which ID is the parent
 *
 * It's called quick union because the union action is very fast
 * Though the find is not so much
 *
 * Trees can possibly get too tall - finding an object at the bottom would involve N array access
 * O(N) is too small for this algorithm
 *
 *
 *  There are some changes made below, and that is to check which tree is smaller before connecting them
 *  and always putting the smaller one first, this way the trees never get too long
 *  Now it goes to O(lgN)
 */

package week1;




public class QuickUnion {
    private int[] array;
    private int[] size;

    public QuickUnion(int n){

        array = new int[n];
        size  = new int[n];
        for(int i=0; i<n; i++) {
            array[i] = i;
            size[i]  = 1;
        }
    }

    private int root(int i){
        while (i != array[i]) i = array[i];
        return i;
    }

    private boolean connected(int p, int q){
        return root(p) == root(q);
    }
    public void union(int p, int q){
        if(!connected(p, q)){
            int i = root(p);
            int j = root(q);
            if(size[p] < size[q]){
                array[i] = j;       // Make the root of p point to the root of q
                size[i] += size[j];
            }
            else {
                array[j] = i;       // Make the root of p point to the root of q
                size[j] += size[i];
            }
        }
    }


    // With path Compression
    // Since, when we move up a tree, we have to touch every element, why not make them all
    // point to the tree, instead of having a long tree, we get at most a tree of height 2

    private int rootPC(int i){
        while (i != array[i]){
            array[i] = array[array[i]] ;
            i = array[i];
        }
        return i;
    }


    private void unionPC(int p, int q){
        if(!connected(p, q)){
            int i = root(p);
            int j = root(q);
            if(size[p] < size[q]){
                array[i] = j;       // Make the root of p point to the root of q
                size[i] += size[j];
            }
            else {
                array[j] = i;       // Make the root of p point to the root of q
                size[j] += size[i];
            }
        }
    }


    public static void main(String args[]){
        QuickUnion qu = new QuickUnion(8);
        qu.union(1,2);
        qu.union(2,3);

        System.out.println(qu.connected(1,4));

    }
}

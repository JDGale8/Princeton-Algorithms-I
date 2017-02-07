package week2;

import edu.princeton.cs.algs4.StdIn;

/**
 * Created by Jake on 2/2/2017.
 */
public class Permutation {

    public static void main(String[] args){
        RandomizedQueue<String> rq = new RandomizedQueue<>();

//        Scanner in = new Scanner(System.in);
        int    k = StdIn.readInt();
        while(!StdIn.isEmpty()) {
            String s = StdIn.readString();
            rq.enqueue(s);
        }

        for(int i=0; i<k; i++){
            System.out.println(rq.dequeue());
        }

    }
}

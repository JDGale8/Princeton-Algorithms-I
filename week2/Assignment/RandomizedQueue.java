package week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Jake on 2/3/2017.
 *
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
        private final int DEFAULT_CAPACITY = 16;
        private Item[]  queue;
        private int     n;


        public RandomizedQueue(){
            queue = (Item[]) new Object[DEFAULT_CAPACITY];
            n = 0;
        }

        public void enqueue(Item item){
            if( item == null){
                throw new NullPointerException("cannot enter a null value into the queue");
            }
            if(n == queue.length-1){
                Item[] tmp = (Item[]) new Object[queue.length*2];
                for(int i=0; i<n; i++){
                    tmp[i] = queue[i];
                }
                queue = tmp;
            }
            queue[n] = item;
            n ++;
        }

        public Item dequeue(){
            if(isEmpty()){
                throw new NoSuchElementException("cannot dequeue, queue is empty");
            }

            int randomInt = StdRandom.uniform(0, n);
            Item result = queue[randomInt];



            if(randomInt != n-1){
                queue[randomInt] = queue[n-1];   // since order doesn't matter, we can get the last item and put it in it's place
            }

            queue[n-1] = null;                      // prevents loitering
            n--;

            if(n == queue.length/4) {
                Item[] tmp = (Item[]) new Object[queue.length / 2];
                for (int i = 0; i < n; i++) {
                    tmp[i] = queue[i];
                }
                queue = tmp;
            }
            return result;
            }

        public Item sample(){                   // return but do not delete an item
            if(isEmpty()){
                throw new NoSuchElementException("cannot dequeue, queue is empty");
            }
            int randomInt = StdRandom.uniform(0, n);
            return queue[randomInt];
        }

        public boolean isEmpty(){
            return n == 0;
        }

        public int size(){
            return n;
        }

        public Iterator<Item> iterator(){

            return new ArrayIterable();
        }

        private class ArrayIterable implements Iterator<Item>{
            private Item[]  tmp     = (Item[]) new Object[n];  // create a temporary array
            private int     tempN   = 0;

            private ArrayIterable(){
                for(int i=0; i< n; i++){
                    tmp[i] = queue[i];     // initialise a temporary queue with every iterable (random shuffle?)
                }
                StdRandom.shuffle(tmp);
            }

            @Override
            public boolean hasNext(){
                return tempN < n;
            }

            @Override
            public Item next(){
                if(hasNext()){
                    return tmp[tempN++];
                }
                else{
                    throw new NoSuchElementException();
                }
            }

            @Override
            public void remove(){
                throw new UnsupportedOperationException("Remove not supported for this class");
            }
        }

}

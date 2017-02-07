package week2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Jake on 2/2/2017.
 */
class RandomizedQueueTest {

    @Test
    void enqueueAndDequeueWork() {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        rq.enqueue("Hello");
        assertEquals(rq.dequeue(), "Hello");
    }


    @Test
    void sampleRuns() {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        rq.enqueue("Hello");
        rq.enqueue("World");
        assertTrue(rq.sample() != null);
    }

    @Test
    void iterates(){
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        rq.enqueue("Hello");
        rq.enqueue("World");

        for( Object item : rq){
            System.out.println((String)item);
        }
    }

    @Test
    void doubleIterates(){
        RandomizedQueueWithLinkedList<String> rq = new RandomizedQueueWithLinkedList<>();
        rq.enqueue("Hello");
        rq.enqueue("World");

        for( String item : rq){
            System.out.println(item);
            for( String other : rq){
                System.out.println(other);
            }
        }
    }


    @Test
    void printsAllValues(){
        RandomizedQueueWithLinkedList<String> rq = new RandomizedQueueWithLinkedList<>();

        rq.enqueue("Hello");
        rq.enqueue("World");
        rq.enqueue("How");
        rq.enqueue("Are");
        rq.enqueue("You");
        int n = rq.size();
        for(int i=0; i<n; i++){
            System.out.println(rq.dequeue());
            System.out.println(rq.size());
        }
    }


    @Test
    void dequeueRemovesItems(){
        RandomizedQueueWithLinkedList<String> rq = new RandomizedQueueWithLinkedList<>();

        rq.enqueue("Hello");
        rq.enqueue("World");
        rq.enqueue("How");
        rq.enqueue("Are");
        rq.enqueue("You");

        for( String item : rq){
            System.out.println(rq.dequeue());
            System.out.println(rq.sample());
        }

    }
}
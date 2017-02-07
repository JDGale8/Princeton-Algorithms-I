package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by Jake on 2/2/2017.
 *
 */

public class RandomizedQueueWithLinkedList<E> implements Iterable<E> {
    private Node first;
    private Node last;
    private int  n;

    public RandomizedQueueWithLinkedList() {             // construct an empty randomized queue

    }

    private class Node{
        private E    item;
        private Node next;
        private Node prev;
    }


    public boolean isEmpty() {             // is the queue empty?
        return first == null;
    }

    public int size() {                    // return the number of items on the queue
        return n;
    }
    public void enqueue(E item) {          // add the item
        if (item == null){
            throw new java.lang.NullPointerException();
        }
        Node oldLast = last;

        last = new Node();
        last.item = item;
        last.next = null;
        if(oldLast!=null){
            oldLast.next = last;
        }
        else{
            first = last;
        }
        last.prev = oldLast;
        n++;
    }

    public E dequeue() {                   // remove and return a random item
        if(isEmpty()){
            throw new NoSuchElementException("cannot dequeue, queue is empty");
        }

        int randomInt = StdRandom.uniform(0, n);
        Node current  = first;

        for(int i=0; i<randomInt; i++){
            current   = current.next;
        }
        if(current.prev!=null){
            current.prev.next = current.next;
        }
        E resItem = current.item;
        n--;
        return resItem;
    }

    public E sample() {                    // return (but do not remove) a random item

        int randomInt = StdRandom.uniform(0, n);
        Node current  = first;

        for(int i=0; i<randomInt; i++){
            current   = current.next;
        }
        return current.item;
    }

    @Override
    public Iterator<E> iterator() {        // return an independent iterator over items in random order
        return new ListIterator();
    }

    public class ListIterator implements Iterator<E>{
        private Node current = first;
        @Override
        public boolean hasNext(){
            return current.next != null;
        }

        @Override
        public E next(){
            if(!hasNext()) throw new NoSuchElementException();
            E nextItem = current.next.item;
            current = current.next;
            return nextItem;
        }

        @Override
        public void remove(){
            throw new UnsupportedOperationException("Remove is not supported in this class");
        }

    }

    public static void main(String[] args) {  // unit testing (optional)

    }

}

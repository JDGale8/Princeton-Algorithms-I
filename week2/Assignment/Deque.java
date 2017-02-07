package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Jake on 2/2/2017.
 */
public class Deque<Item> implements Iterable<Item> {

    private Node first = null;
    private Node  last = null;
    private int n;

    private class Node{
        private Item item;
        private Node next;
        private Node prev;
    }

    public Deque() {                            // construct an empty deque
        first = null;
        last  = null;
        n     = 0;
    }

    public boolean isEmpty(){                   // is the deque empty?
        return first == null;
    }

    public int size() {                         // return the number of items on the deque
        return n;
    }

    public void addFirst(Item item) {              // add the item to the front
        if (item == null){
            throw new NullPointerException();
        }

        Node oldFirst = first;

        first = new Node();

        first.item = item;
        first.next = oldFirst;
        first.prev = null;
        if(oldFirst != null) {
            oldFirst.prev = first;
        }
        if(first.next == null){
            last = first;
        }
        n++;
    }

    public void addLast(Item item) {               // add the item to the end
        if (item == null){
            throw new NullPointerException();
        }
        Node oldLast = last;

        last = new Node();
        last.item = item;
        last.next = null;
        if(oldLast != null) {
            oldLast.next = last;
        }
        else{
            first = last;
        }
        last.prev = oldLast;
        n++;
    }

    public Item removeFirst() {                    // remove and return the item from the front
        if (isEmpty()){
            throw new NoSuchElementException("Cannot remove first, array is empty");
        }

        Item firstResult = first.item;
        first = first.next;
        if(first != null) {
            first.prev = null;
            if(first.next == null) {
                last = first;
            }
        }
        else {
            last = null;
        }
        n--;
        return firstResult;
    }

    public Item removeLast() {                     // remove and return the item from the end
        if (isEmpty()){
            throw new NoSuchElementException("Cannot remove last, array is empty");
        }
        Item lastResult = last.item;
        last = last.prev;
        if (last!= null){
            last.next = null;
            if(last.prev == null){
                first = last;
            }
        }
        else{
            first = last = null;
        }

        n--;
        return lastResult;
    }

    @Override
    public Iterator<Item> iterator() {             // return an iterator over items in order from front to end
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if(!hasNext()) throw new NoSuchElementException();
            Item nextItem = current.item;
            current = current.next;
            return nextItem;
        }


        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove is not supported in this class");
        }
    }

}


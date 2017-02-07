package week2;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * Created by Jake on 1/31/2017.
 *
 *
 *  This is a queue, and now is generic for all object types
 */

public class LinkedListQueue<Item> implements Iterable {

    // Queues are first in, first out. FIFO

    // NOTE HERE Java does not allow a generic array to be produced

    // Item[] array = new Item[CAPACITY] will NOT work
    // Instead we have to create an array of Objects and cast it down like so:

    // Item[] array = (Item[]) new Object[CAPACITY];
    // We dont like casts though, because it is a weak thing generally

    // if Item is a primitive type, we have to use wrappers for int and double and stuff
    // That's where Integer and Double comes in handy

    private Node first = null;
    private Node last  = null;

    public class Node{
        private   Item item;
        private   Node next;
    }

    public void enqueue(Item item){
        Node oldLast = last;

        last      = new Node();
        last.item = item;
        last.next = null;
        if  (isEmpty()) first = last;
        else     oldLast.next = last;
    }

    public Item deque(){
        if (isEmpty()) throw new RuntimeException("Cannot deque, queue is empty");

        Item returnValue = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        return returnValue;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public Item peek(){
        if (isEmpty()) throw new RuntimeException("Cannot peek, queue is empty");
        return last.item;

    }


    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private Node current = first;

        public boolean hasNext(){
            return current != null;
        }

        public Item next(){
            if (isEmpty()) throw new RuntimeException("Queue is empty");
               Item item = current.item;
            current      = current.next;
            return item;
        }

    }

}

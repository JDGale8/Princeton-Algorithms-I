/**
 * Created by Jake on 1/31/2017.
 *
 *  A linked list is an iterable set of lists, where each item, called a Node, will have two pieces of information
 *  It's details (in the case below a String value) and the next item on the list.
 *
 *  The end of the list always points to null
 *  And if the list hasn't been implemented the first item will be null as well
 */

package week2;

import org.omg.SendingContext.RunTime;

import java.util.Iterator;

public class LinkedListStack{

    private Node first = null;

    private class Node{
        private String item; // This can be whatever object you want to link between
        private   Node next;

    }

    public LinkedListStack(){
    }

    public LinkedListStack(String[] items){
        for ( String item : items){
            push(item);
        }
    }

    public boolean isEmpty(){
        return first == null;
    }

    public void push(String item){

        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;

    }

    public String pop(){
        if(isEmpty()) throw new RuntimeException("Cannot Pop, the list is empty");
        Node oldFirst = first;
        first = first.next;

        return oldFirst.item;
    }

    public String peek(){
        return first.item;
    }

    public Iterator iterator(){
        return new reverseIterator();
    }

    public class reverseIterator implements Iterator{

        Node current = first;

        public boolean hasNext(){
            return current != null;
        }

        public String next(){
            if(isEmpty()) { throw new RuntimeException("Queue is empty"); }
            String item = current.item;
            current = current.next;
            return item;
        }

    }
}

package week2;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Jake on 2/2/2017.
 *
 *
 */
class DequeTest {

    @Test
    void addFirst() {
        Deque<String> dq = new Deque<>();
        dq.addFirst("Hello");
        assertEquals(dq.removeFirst(), "Hello");
    }

    @Test
    void addLast() {
        Deque<String> dq = new Deque<>();
        dq.addFirst("Hello");
        dq.addLast("World");
        assertEquals(dq.removeLast(), "World");
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    void removeFirstOnEmptyListThrowsException(){
        Deque<String> dq = new Deque<>();
        assertThrows(NoSuchElementException.class, dq::removeFirst);
    }

    @Test
    void addLastDoesntBreak(){
        Deque<String> dq = new Deque<>();;
        dq.addLast("World");
        assertFalse(dq.isEmpty());
    }


    @Test
    void createsTwoDequesAtOnce(){
        Deque<String> dq1 = new Deque<>();
        Deque<String> dq2 = new Deque<>();

        dq1.addFirst("hello");
        assertThrows(NoSuchElementException.class, dq2::removeFirst);
    }

    @Test
    void addingAndRemovingFirstKeepsArrayEmpty(){
        Deque<String> dq = new Deque<>();
        assertTrue(dq.isEmpty());
        dq.addFirst("Hello");
        assertEquals(dq.removeFirst(), "Hello");
        assertTrue(dq.isEmpty());
    }

    @Test
    void addingAndRemovingLastKeepsArrayEmpty(){
        Deque<String> dq = new Deque<>();
        assertTrue(dq.isEmpty());
        dq.addFirst("Hello");
        dq.removeLast();
        assertTrue(dq.isEmpty());
    }

    @Test
    void addingTwoFirstKeepsCorrectOrder(){
        Deque<String> dq = new Deque<>();

        dq.addFirst("World");
        dq.addFirst("Hello");
        assertEquals(dq.removeFirst(), "Hello");

    }

    @Test
    void addingTwoLastKeepsCorrectOrder(){
        Deque<String> dq = new Deque<>();

        dq.addLast("Hello");
        dq.addLast("World");
        assertEquals(dq.removeLast(), "World");
    }

    @Test
    void addingAndRemovingItemsLastIncrementsN(){
        Deque<String> dq = new Deque<>();
        assertEquals(0, dq.size());
        dq.addFirst("World");
        assertEquals(1, dq.size());
        dq.addFirst("Hello");
        assertEquals(2, dq.size());
        dq.removeFirst();
        assertEquals(1, dq.size());
    }

    @Test
    void addingAndRemovingItemsFirstIncrementsN(){
        Deque<String> dq = new Deque<>();
        assertEquals(0, dq.size());
        dq.addLast("World");
        assertEquals(1, dq.size());
        dq.addLast("Hello");
        assertEquals(2, dq.size());
        dq.removeLast();
        assertEquals(1, dq.size());
    }

    @Test
    void printsAllValues(){
        Deque<String> dq = new Deque<>();
        dq.addLast("Hello");
        dq.addLast("World");
        dq.addLast("How");
        dq.addLast("Are");
        dq.addLast("You");
        for(int i=0; i<dq.size(); i++){
            System.out.println(dq.removeLast());
        }
    }


}
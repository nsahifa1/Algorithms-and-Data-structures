package priorityqueue;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class PriorityQueueTest {

    private PriorityQueue<Integer> pq;

    @Before
    public void setup() {
        pq = new PriorityQueue<Integer>();
    }

    @Test
    public void testHeapProperty() {
        Integer[] elems = {3, 2, 5, 6, 7, 9, 4, 8, 1};

        for (int e : elems) pq.add(e);
        for (int i=1;i<=9;++i) assertEquals(i, pq.pop().intValue());
    }

    @Test
    public void testHeapifyConstructor() {
        Integer[] elems = {3, 2, 5, 6, 7, 9, 4, 8, 1};
        PriorityQueue<Integer> q = new PriorityQueue<Integer>(elems);

        for (int i=1;i<=9;++i) assertEquals(i, q.pop().intValue());
    }

    @Test
    public void testHeapifyConstructor2() {
        Random rnd = new Random();
        int size = rnd.nextInt()%100;
        size *= size;

        Integer[] elems = randomArray(size);
        PriorityQueue<Integer> q = new PriorityQueue<Integer>(elems);

        PriorityQueue<Integer> q2 = new PriorityQueue<Integer>(size);
        for (int e : elems) q2.add(e);

        assertTrue(q2.isMinHeap(0));
        while (!q2.isEmpty()){
            assertEquals(q.pop(), q2.pop());
        }
    }

    @Test
    public void testEmpty() {
        assertEquals(pq.size(), 0);
        assertTrue(pq.isEmpty());

        assertEquals(pq.pop(), null);
        assertEquals(pq.top(), null);
    }

    // Helper method for generating random Integer Array
    // For internal usage only
    private Integer[] randomArray(int size){
        // Creating random object
        Random rd = new Random();

        Integer[] elems = new Integer[size];
        for (int i=0;i<size;++i){
            // Storing random integers in our array
            elems[i] = rd.nextInt();
        }
        return elems;
    }

    @Test
    public void testClear() {
        String[] elems = {"abc", "bvg", "yut", "kiu", "tfg"};
        PriorityQueue<String> q = new PriorityQueue<String>(elems);

        q.clear();
        assertEquals(q.size(), 0);
        assertTrue(q.isEmpty());
    }

    // TODO : Fix the issue java.lang.AssertionError
    @Test
    public void testContainment() {
        String[] elems = {"abc", "bvg", "yut", "kiu", "tfg"};
        PriorityQueue<String> q = new PriorityQueue<String>(elems);

        assertTrue(q.contains("abc"));
        q.remove("abc");
        assertFalse(q.contains("abc"));

        q.remove("tfg");
        assertFalse(q.contains("tfg"));
    }
}

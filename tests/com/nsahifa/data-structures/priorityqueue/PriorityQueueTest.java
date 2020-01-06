package priorityqueue;

import org.junit.Before;
import org.junit.Test;

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

}

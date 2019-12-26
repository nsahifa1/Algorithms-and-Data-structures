package queue;

import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class QueueTest {

    private Queue<Integer> queue;

    @Before
    public void setup() {
        queue = new Queue<Integer>();
    }

    @Test
    public void testEmptyQueue() {
        assertTrue(queue.isEmpty());
        assertTrue(queue.size() == 0);
    }

    @Test(expected = RuntimeException.class)
    public void testPopOnEmptyQueue(){
        queue.pop();
    }

    @Test(expected = RuntimeException.class)
    public void testPeekOnEmpty() {
        queue.front();
    }

    @Test
    public void testPush() {
        queue.push(4);
        assertTrue(queue.pop() == 4);
        assertTrue(queue.size() == 0);
    }

    @Test
    public void testFront() {
        queue.push(3);
        assertTrue(queue.pop() == 3);
        assertTrue(queue.isEmpty());
    }
}

package linkedlists;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DoublyLinkedListTest {

    private DoublyLinkedList<Integer> list;

    @Before
    public void setup() {
        list = new DoublyLinkedList<Integer>();
    }

    @Test
    public void testEmptyList() {
        assertTrue(list.isEmpty());
        assertEquals(list.size(), 0);
    }

    @Test(expected = RuntimeException.class)
    public void testRemoveFirstEmpty() {
        list.removeFirst();
    }

    @Test(expected = RuntimeException.class)
    public void testRemoveLastEmpty() {
        list.removeLast();
    }

    @Test
    public void testAddFirst() {
        list.add(3);
        assertEquals(list.size(), 1);

        list.add(2);
        assertEquals(list.size(), 2);
    }

    @Test
    public void testRemoveFirst() {
        list.add(1);
        assertTrue(list.removeFirst() == 1);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testRemoveLast() {
        list.add(2);
        assertTrue(list.removeLast() == 2);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testPeek() {
        list.add(1);
        assertTrue(list.peekFirst() == 1);
        assertTrue(list.peekLast() == 1);

        list.add(2);
        assertTrue(list.peekFirst() == 1);
        assertTrue(list.peekLast() == 2);

        list.addFirst(3);
        assertTrue(list.peekFirst() == 3);
        assertTrue(list.peekLast() == 2);
    }

    @Test
    public void remove() {
        DoublyLinkedList<String>  list = new DoublyLinkedList<String>();
        list.add("test1");
        list.add("test2");
        list.add("test3");
        list.remove("test1");
        list.remove("test2");
        list.remove("test3");
        assertTrue(list.size() == 0);
    }

    @Test
    public void testRemoveAt() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.removeAt(0);
        list.removeAt(2);

        assertTrue(list.peekFirst() == 2);
        assertTrue(list.peekLast() == 3);

        list.removeAt(0);
        list.removeAt(0);

        assertTrue(list.size() == 0);
    }
}

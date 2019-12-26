package dynamicarray;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DynamicArrayTest {

    private DynamicArray<Integer> arr;

    @Before
    public void setup() {
        arr = new DynamicArray<Integer>();
    }

    @Test
    public void testEmptyArray() {
        assertTrue(arr.isEmpty());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemovingEmpty(){
        arr.removeAt(0);
    }

    @Test(expected = Exception.class)
    public void testIndexOutOfBounds(){
        arr.add(-1);
        arr.add(-3);
        arr.removeAt(2);
    }

    @Test
    public void testRemoving() {
        int[] ints = {1, 2, 3, 4, 5};

        for (int e : ints) arr.add(e);

        boolean ret = arr.remove(3);
        assertTrue(ret);

        ret = arr.remove(10);
        assertFalse(ret);

        ret = arr.remove(3);
        assertFalse(ret);
    }

    @Test
    public void testAddingElements() {
        int[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        for (int i=0;i<ints.length;++i) arr.add(ints[i]);

        for (int i=0;i<ints.length;++i) assertEquals(arr.getAt(i).intValue(), ints[i]);
    }
}
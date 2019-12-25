package dynamicarray;

import org.junit.Test;

import static org.junit.Assert.*;

public class DynamicArrayTest {

    @Test
    public void testEmptyArray() {
        DynamicArray<Integer> arr = new DynamicArray<Integer>();
        assertTrue(arr.isEmpty());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemovingEmpty(){
        DynamicArray<Integer> arr = new DynamicArray<Integer>();
        arr.removeAt(0);
    }

    @Test(expected = Exception.class)
    public void testIndexOutOfBounds(){
        DynamicArray<Integer> arr = new DynamicArray<Integer>();
        arr.add(-1);
        arr.add(-3);
        arr.removeAt(2);
    }

    @Test
    public void testRemoving() {
        DynamicArray<Integer> arr = new DynamicArray<Integer>();
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
        DynamicArray<Integer> arr = new DynamicArray<Integer>();
        int[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        for (int i=0;i<ints.length;++i) arr.add(ints[i]);

        for (int i=0;i<ints.length;++i) assertEquals(arr.getAt(i).intValue(), ints[i]);
    }
}
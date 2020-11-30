package unionfind;

import org.junit.Test;
import static org.junit.Assert.*;

public class UnionFindTest {

    int sz = 5;

    @Test
    public void testComponentSize() {
        UnionFind uf = new UnionFind(6);

        for (int i=0; i<sz; ++i){
            assertEquals(uf.componenentSize(i), 1);
        }

        uf.merge(0, 1);
        assertEquals(uf.componenentSize(0), 2);
        assertEquals(uf.componenentSize(1), 2);
        assertEquals(uf.componenentSize(2), 1);
        assertEquals(uf.componenentSize(3), 1);
        assertEquals(uf.componenentSize(4), 1);

        uf.merge(1, 2);
        assertEquals(uf.componenentSize(0), 3);
        assertEquals(uf.componenentSize(1), 3);
        assertEquals(uf.componenentSize(2), 3);
        assertEquals(uf.componenentSize(3), 1);
        assertEquals(uf.componenentSize(4), 1);
    }

    @Test
    public void testConnectivity() {
        UnionFind uf = new UnionFind(5);

        uf.merge(0, 3);

        assertTrue(uf.connected(0, 3));
        assertFalse(uf.connected(0, 1));
    }

    @Test
    public void testSize() {
        UnionFind uf = new UnionFind(sz);

        assertEquals(uf.size(), 5);
        assertEquals(uf.components(), 5);

        uf.merge(1, 2);
        assertEquals(uf.components(), 4);
        assertEquals(uf.size(), 5);

        uf.find(4);
        assertEquals(uf.components(), 4);
        assertEquals(uf.size(), 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorException(){
        new UnionFind(-2);
    }
}

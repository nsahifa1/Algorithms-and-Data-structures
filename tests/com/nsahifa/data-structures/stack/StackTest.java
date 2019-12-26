package stack;

import org.junit.Before;
import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.*;

public class StackTest {

    private Stack<Integer> stack;

    @Before
    public void setup() {
        stack = new Stack<Integer>();
    }

    @Test
    public void testEmptyStack(){
        assertTrue(stack.isEmpty());
        assertTrue(stack.size() == 0);
    }

    @Test(expected = EmptyStackException.class)
    public void testPopOnEmptyStack() {
        stack.pop();
    }

    @Test(expected = EmptyStackException.class)
    public void testTopOnEmptyStack() {
        stack.top();
    }

    @Test
    public void testPopOffStack(){
        stack.push(2);
        assertTrue(stack.top() == 2);
        stack.pop();
        assertTrue(stack.isEmpty());
    }
}

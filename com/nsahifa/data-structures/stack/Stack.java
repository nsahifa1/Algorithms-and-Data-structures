/**
 * Stack implementation using Doubly Linked List implementation
 * @author : nabil sahifa
 * @email : nabilsahifa@gmail.com
 */
package stack;

import linkedlists.DoublyLinkedList;

import java.util.EmptyStackException;
import java.util.Iterator;

public class Stack<T> implements Iterable<T> {

    private DoublyLinkedList<T> list = new DoublyLinkedList();

    // Create an empty stack
    public Stack() {}

    // Create a stack with an initial element
    public Stack(T elem){
        push(elem);
    }

    // Returns the size of the stack
    public int size() {
        return list.size();
    }

    // Check if the stack is empty
    public boolean isEmpty(){
        return size() == 0;
    }

    // Push an element on the stack
    public void push(T elem){
        list.add(elem);
    }

    // Pop an element off the stack
    public T pop(){
        // Check if the stack is empty
        if (isEmpty()) throw new EmptyStackException();
        return list.removeLast();
    }

    // Peek the top of the stack without removing an element
    public T top(){
        // Check if the stack is empty
        if (isEmpty()) throw new EmptyStackException();
        return list.peekLast();
    }

    // Allow iterating through the stack using
    // the list iterator previously implemented
    // TODO : check for concurrence modification error
    public Iterator<T> iterator() {
        return list.iterator();
    }
}

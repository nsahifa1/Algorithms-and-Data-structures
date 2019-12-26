/**
 * Stack implementation using Doubly Linked List implementation
 * @author : nabil sahifa
 * @email : nabilsahifa@gmail.com
 */
package queue;

import linkedlists.DoublyLinkedList;
import java.util.Iterator;

public class Queue<T> implements Iterable<T> {

    private DoublyLinkedList<T> list = new DoublyLinkedList<T>();

    public Queue() {}

    public Queue(T elem){
        push(elem);
    }

    // Return the size of the queue
    public int size() {
        return list.size();
    }

    // Check if the queue is empty
    public boolean isEmpty(){
        return size() == 0;
    }

    // Peek the element at the front of the queue
    // without polling it
    public T front(){
        // Check if the queue is empty
        if (isEmpty()) throw new RuntimeException("Queue Empty");
        return list.peekFirst();
    }

    // pop an element from the front of the queue
    public T pop() {
        // Chcek if the queue is empty
        if (isEmpty()) throw new RuntimeException("Queue Empty");
        return list.removeFirst();
    }

    // Add an element to the queue
    public void push(T elem){
        list.add(elem);
    }

    // Allow iterating through the queue
    // using the list iterator
    public Iterator<T> iterator() {
        return list.iterator();
    }
}

/**
 * Doubly linked list implementation
 * @author : nabil sahifa
 * @email : nabilsahifa@gmail.com
 */
package linkedlists;

import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {

    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    // Internal node class to represent data
    private static class Node<T> {
        private T data;
        private Node<T> prev, next;

        public Node(T data, Node<T> prev, Node<T> next){
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data.toString() +
                    '}';
        }
    }

    // Empty the linked list, complexity : O(n)
    public void clear() {
        Node<T> iter = head;
        while (iter != null){
            Node<T> next = iter.next;
            iter.prev = iter.next = null;
            iter.data = null;
        }
        head = tail = iter = null;
        size = 0;
    }

    // Return the size of the linked list
    public int size() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // Add element to the tail of the list, complexity : O(1)
    public void add(T elem) {
        addLast(elem);
    }

    // Add an element to the head of the linked list, complexity : O(1)
    public void addFirst(T elem){
        if (isEmpty()){
            head = tail = new Node<T>(elem, null, null);
        }
        else{
            head.prev = new Node<T>(elem, null, head);
            head = head.prev;
        }
        size++;
    }

    // add an element to the tail of the linked list, complexity : O(1)
    public void addLast(T elem) {
        if (isEmpty()){
            head = tail = new Node<T>(elem, null, null);
        }
        else {
            tail.next = new Node<T>(elem, tail, null);
            tail = tail.next;
        }
        size++;
    }

    // Returns the value of the first node, complexity : O(1)
    public T peekFirst() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        return head.data;
    }

    // Returns the value of the last node, complexity : O(1)
    public T peekLast() {
        if (isEmpty()) throw  new RuntimeException("Empty list");
        return tail.data;
    }

    // Remove the first element of the linked list, complexity : O(1)
    public T removeFirst(){
        if (isEmpty()) throw new RuntimeException("Empty list");

        // We extract the data from the head node
        // then we move the head pointer forwards one node
        T data = head.data;
        head = head.next;
        size--;

        // If the list is empty, set the tail to null
        if (isEmpty()) tail = null;
        else head.prev = null;
        return data;
    }

    // Remove the last value at the tail of the linked list, complexity : O(1)
    public T removeLast(){
        if (isEmpty()) throw new RuntimeException("Empty list");

        // We extract the data from the tail node
        // then we move the tail pointer backwards one node
        T data = tail.data;
        tail = tail.prev;
        size--;

        if (isEmpty()) head = null;
        else tail.next = null;
        return data;
    }

    // Remove a node from the linked list, complexity : O(1)
    // for internal usage only
    private T remove(Node<T> node){

        // If the node to remove is at the head
        if (node.prev == null) return removeFirst();

        // If the node to remove is at the tail
        if (node.next==null) return removeLast();

        node.next.prev = node.prev;
        node.prev.next = node.next;

        T data = node.data;
        node.data = null;
        node = node.prev = node.next = null;

        size--;
        return data;
    }

    // Remove a node at a particular index, complexity : O(n)
    public T removeAt(int index){
        if (index<0 || index>=size) {
            throw new IllegalArgumentException();
        }

        Node<T> iter;
        int i;
        for (i=0, iter = head; i!=index; ++i){
            iter = iter.next;
        }
        return remove(iter);
    }

    // Remove a particular object from the linked list, complexity : O(n)
    public boolean remove (Object obj){
        Node<T> iter = head;

        if (obj == null){
            for (iter = head; iter != null; iter = iter.next){
                if (iter.data == null){
                    remove(iter);
                    return true;
                }
            }
        }
        else{
            for (iter = head; iter != null; iter = iter.next){
                if (obj.equals(iter.data)){
                    remove(iter);
                    return true;
                }
            }
        }
        return false;
    }

    // Find the index of a particular value in the linked list
    // complexity : O(n)
    public int indexOf(Object obj){
        int index = 0;
        Node<T> iter = head;
        if (obj == null){
            for (iter = head; iter != null; iter = iter.next, index++){
                if (iter.data == null)
                    return index;
            }
        }
        else {
            for (iter = head; iter != null; iter = iter.next, index++) {
                if (obj.equals(iter.data))
                    return index;
            }
        }
        return -1;
    }

    // Check if a value is contained within the linked list
    // Complexity : O(1)
    public boolean contains(Object obj){
        return indexOf(obj) != -1;
    }

    // TODO : check for concurrence modification error
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Node<T> iter = head;

            public boolean hasNext() {
                return iter != null;
            }

            public T next() {
                T data = iter.data;
                iter = iter.next;
                return data;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> iter = head;
        while (iter != null){
            sb.append(iter.data + ", ");
            iter = iter.next;
        }
        sb.append("]");
        return sb.toString();
    }
}

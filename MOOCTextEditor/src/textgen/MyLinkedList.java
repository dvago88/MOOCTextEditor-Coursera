package textgen;

import java.util.AbstractList;


/**
 * A class that implements a doubly linked list
 *
 * @param <E> The type of the elements stored in the list
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MyLinkedList<E> extends AbstractList<E> {
    LLNode<E> head;
    LLNode<E> tail;
    LLNode<E> transicion;
    int size;


    /**
     * Create a new empty LinkedList
     */
    public MyLinkedList() {
        size = 0;
        head = new LLNode<>(null);
        tail = new LLNode<>(null);
        transicion = new LLNode<>(null);
        head.next = tail;
        tail.prev = head;
    }

    /**
     * Appends an element to the end of the list
     *
     * @param element The element to add
     */
    public boolean add(E element) {
        // TODO: Terminar de implementar este metodo
        LLNode<E> newNode = new LLNode<>(element);
        if (element == null) throw new NullPointerException("You can't add nulls...");

        if (size == 0) {
            newNode.prev = tail.prev;
            newNode.next = tail;
            tail.prev = newNode;
            head.next = newNode;

            transicion.prev = newNode;
            transicion.next = newNode.next;
            size++;
        } else {
            newNode.next = transicion.next;
            tail.prev = newNode;
            newNode.prev = transicion.prev;
            transicion.prev.next = newNode;

            transicion.next = newNode.next;
            transicion.prev = newNode;
            size++;
        }


        return true;
    }

    /**
     * Get the element at position index
     *
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public E get(int index) {
        // TODO: Terminar de implementar este metodo

        if (index < 0 || index > size - 1) throw new IndexOutOfBoundsException("index is out of bounds");
        LLNode<E> iterador;
        iterador = head;
        for (int i = 0; i <= index; i++) {
            iterador = iterador.next;
        }
        return iterador.data;
    }

    /**
     * Add an element to the list at the specified index
     *
     * @param index   The     index where the element should be added
     * @param element The element to add
     */
    public void add(int index, E element) {
        // TODO: Implement this method
    }


    /**
     * Return the size of the list
     */
    public int size() {
        // TODO: Implement this method
        return -1;
    }

    /**
     * Remove a node at the specified index and return its data element.
     *
     * @param index The index of the element to remove
     * @return The data element removed
     * @throws IndexOutOfBoundsException If index is outside the bounds of the list
     */
    public E remove(int index) {
        // TODO: Implement this method
        return null;
    }

    /**
     * Set an index position in the list to a new element
     *
     * @param index   The index of the element to change
     * @param element The new element
     * @return The element that was replaced
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public E set(int index, E element) {
        // TODO: Implement this method
        return null;
    }
}

class LLNode<E> {
    LLNode<E> prev;
    LLNode<E> next;
    E data;

    // TODO: Add any other methods you think are useful here
    // E.g. you might want to add another constructor

    public LLNode(E e) {
        this.data = e;
        this.prev = null;
        this.next = null;
    }

}

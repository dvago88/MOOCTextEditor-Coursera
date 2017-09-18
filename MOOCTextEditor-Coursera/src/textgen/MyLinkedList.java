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
    private int size;

    /**
     * Create a new empty LinkedList
     */
    public MyLinkedList() {
        size = 0;
        head = new LLNode<>(null);
        tail = new LLNode<>(null);
        head.next = tail;
        tail.prev = head;
    }

    /**
     * Appends an element to the end of the list
     *
     * @param element The element to add
     */
    public boolean add(E element) {
        LLNode<E> newNode = new LLNode<>(element);
        if (element == null) throw new NullPointerException("You can't add nulls...");

        newNode.next = tail;
        newNode.prev = tail.prev;
        tail.prev.next = newNode;
        tail.prev = newNode;
        size++;
        return true;
    }

    /**
     * Get the element at position index
     *
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public E get(int index) {
        if (index < 0 || index > size - 1) throw new IndexOutOfBoundsException("index is out of bounds");
        LLNode<E> iterador;
        iterador = head;
        for (int i = 0; i <= index; i++) {
            iterador = iterador.next;
        }
        return iterador.data;
    }

    private LLNode getNode(int index) {
        if (index < 0 || index > size - 1) throw new IndexOutOfBoundsException("index is out of bounds");
        LLNode<E> iterador;
        iterador = head;
        for (int i = 0; i <= index; i++) {
            iterador = iterador.next;
        }
        return iterador;
    }

    /**
     * Add an element to the list at the specified index
     *
     * @param index   The     index where the element should be added
     * @param element The element to add
     */
    public void add(int index, E element) {
        if (element == null) throw new NullPointerException("You can't add nulls...");
        LLNode<E> newNode = new LLNode<>(element);
        if (size != 0) {
            LLNode<E> oldNode = getNode(index);

            newNode.prev = oldNode.prev;
            newNode.next = oldNode;
            oldNode.prev.next = newNode;
            oldNode.prev = newNode;
            size++;
        } else {
            add(element);
        }
    }


    /**
     * Return the size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Remove a node at the specified index and return its data element.
     *
     * @param index The index of the element to remove
     * @return The data element removed
     * @throws IndexOutOfBoundsException If index is outside the bounds of the list
     */
    public E remove(int index) {
        LLNode<E> toRemoveNode = getNode(index);

        toRemoveNode.prev.next = toRemoveNode.next;
        toRemoveNode.next.prev = toRemoveNode.prev;
        size--;

        return toRemoveNode.data;
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
        if (element == null) throw new NullPointerException("You can't add nulls...");

        LLNode<E> updatedNode = getNode(index);

        E dataToReturn = updatedNode.data;
        updatedNode.data = element;

        return dataToReturn;
    }
}

class LLNode<E> {
    LLNode<E> prev;
    LLNode<E> next;
    E data;

    // E.g. you might want to add another constructor

    public LLNode(E e) {
        this.data = e;
        this.prev = null;
        this.next = null;
    }

}

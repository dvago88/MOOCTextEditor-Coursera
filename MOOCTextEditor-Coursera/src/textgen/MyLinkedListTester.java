/**
 *
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;


import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 */
public class MyLinkedListTester {

    private static final int LONG_LIST_LENGTH = 10;

    MyLinkedList<String> shortList;
    MyLinkedList<Integer> emptyList;
    MyLinkedList<Integer> longerList;
    MyLinkedList<Integer> list1;

    MyLinkedList<Integer> removedor;
    MyLinkedList<Integer> removedorVacio;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        // Feel free to use these lists, or add your own
        shortList = new MyLinkedList<>();
        shortList.add("A");
        shortList.add("B");
        emptyList = new MyLinkedList<>();
        longerList = new MyLinkedList<>();
        removedor = new MyLinkedList<>();
        removedorVacio = new MyLinkedList<>();
        for (int i = 0; i < LONG_LIST_LENGTH; i++) {
            longerList.add(i);
        }
        list1 = new MyLinkedList<>();
        list1.add(65);
        list1.add(21);
        list1.add(42);

        removedor.add(0);
        removedor.add(1);
    }


    /**
     * Test if the get method is working correctly.
     */
    /*You should not need to add much to this method.
     * We provide it as an example of a thorough test. */
    @Test
    public void testGet() {
        //test empty list, get should throw an exception
        try {
            emptyList.get(0);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }

        // test short list, first contents, then out of bounds
        assertEquals("Check first", "A", shortList.get(0));
        assertEquals("Check second", "B", shortList.get(1));

        try {
            shortList.get(-1);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            shortList.get(2);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }
        // test longer list contents
        for (int i = 0; i < LONG_LIST_LENGTH; i++) {
            assertEquals("Check " + i + " element", (Integer) i, longerList.get(i));
        }

        // test off the end of the longer array
        try {
            longerList.get(-1);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            longerList.get(LONG_LIST_LENGTH);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {
        }

    }


    /**
     * Test removing an element from the list.
     * We've included the example from the concept challenge.
     * You will want to add more tests.
     */
    @Test
    public void testRemove() {
        int a = list1.remove(0);
        assertEquals("Remove: check a is correct ", 65, a);
        assertEquals("Remove: check element 0 is correct ", (Integer) 21, list1.get(0));
        assertEquals("Remove: check size is correct ", 2, list1.size());

        try {
            removedorVacio.remove(0);
            fail("No se lanzo indexoutofboudsexecption");
        } catch (IndexOutOfBoundsException ie) {

        }

        removedor.remove(0);
        removedor.remove(0);
        try {
            removedor.remove(0);
            fail("No se lanzo exception cuando se trato de remover en 0");
        } catch (IndexOutOfBoundsException ie) {

        }

        try{
            list1.remove(-1);
            fail("You didn't catch iobe at remove");
        }catch (IndexOutOfBoundsException iobe){

        }
    }

    /**
     * Test adding an element into the end of the list, specifically
     * public boolean add(E element)
     */
    @Test
    public void testAddEnd() {
        shortList.add("C");
        assertEquals("revisa si ingreso elemento al final","C",shortList.get(shortList.size()-1));
        try{
            shortList.add(null);
            fail("You didn't catch nullpoiner ar addEnd");
        }catch (NullPointerException npe){

        }
    }


    /**
     * Test the size of the list
     */
    @Test
    public void testSize() {
        assertEquals("Correct size", 3, list1.size());
        assertEquals("Should be zero", 0, emptyList.size());
    }


    /**
     * Test adding an element into the list at a specified index,
     * specifically:
     * public void add(int index, E element)
     */
    @Test
    public void testAddAtIndex() {
        shortList.add("C");
        shortList.add("D");
        shortList.add("F");
        shortList.add("G");
        shortList.add("H");
        shortList.add(4, "E");

        assertEquals("Debe ser E", "E", shortList.get(4));
        assertEquals("Debe ser F", "F", shortList.get(5));
        assertEquals("Debe ser G", "G", shortList.get(6));
        assertEquals("Debe ser H", "H", shortList.get(7));
        assertEquals("Debe ser D", "D", shortList.get(3));
        try{
            shortList.add(4,null);
            fail("You didn't catche it");
        }catch (NullPointerException npe){

        }
        try{
            shortList.add(-1,"Z");
            fail("You didn't catch the exception");
        }catch (IndexOutOfBoundsException ie){

        }
        try{
            shortList.add(shortList.size()+1,"Z");
            fail("didn't catch upper bound");
        }catch (IndexOutOfBoundsException ie){

        }
        try{
            shortList.add(4,null);
            fail("Didn't catch nullpointer");
        }catch (NullPointerException npe){

        }
    }

    /**
     * Test setting an element in the list
     */
    @Test
    public void testSet() {
        assertEquals("prueba si devuelve lo correcto", "B", shortList.set(1, "C"));
        assertEquals("comprueba que si meta lo correcto", "C", shortList.get(1));

        try {
            shortList.set(3, "c");
            fail("No se esta revisando el IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException ie) {
        }
        try{
            shortList.set(-1,"c");
            fail("You didn't catch it");
        }catch (IndexOutOfBoundsException ie){

        }
        try{
            shortList.set(1,null);
            fail("Didn't catch nullpointer");
        }catch (NullPointerException npe){

        }
    }
}

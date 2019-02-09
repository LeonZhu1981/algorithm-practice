package net.zhuxiaodong.algorithm.practice.array;

import java.lang.IllegalArgumentException;
import java.lang.Exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import net.zhuxiaodong.algorithm.practice.array.MyGenericArrayList;

/**
 * Unit test for MyGenericArrayList.
 */
@DisplayName("Unit test for MyGenericArrayList")
public class MyGenericArrayListTest {
    @Test
    @DisplayName("General test case")
    public void testGeneral() {
        MyGenericArrayList<Integer> list = new MyGenericArrayList<>(10);
        assertTrue(list.isEmpty());
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.count());
        assertEquals(2, list.get(1));
        assertFalse(list.isEmpty());
        assertTrue(list.contains(1));
        assertEquals(2, list.find(3));
        assertFalse(list.contains(10000));

        list.set(1, 5);
        assertEquals(5, list.get(1));
        
        list.addFirst(10);
        assertEquals(4, list.count());
        assertEquals(1, list.get(1));
        assertEquals(5, list.get(2));

        assertEquals(10, list.removeFirst());
        assertEquals(3, list.count());

        assertEquals(3, list.removeLast());
        assertEquals(2, list.count());

        list.insert(1, 100);
        list.insert(2, 101);
        assertEquals(4, list.count());
        assertEquals(100, list.get(1));
        assertEquals(101, list.get(2));
        assertEquals(5, list.get(3));

        assertTrue(list.removeElement(101));
        assertFalse(list.removeElement(102));
        assertEquals(3, list.count());
        assertEquals(5, list.get(2));
    }

    @Test
    @DisplayName("Resize for add test case")
    public void testResizeForAdd() {
        MyGenericArrayList<Integer> list = new MyGenericArrayList<>(1);
        assertEquals(1, list.capacity());
        list.add(1);
        assertEquals(1, list.capacity());
        list.add(2);
        assertEquals(2, list.capacity());
        list.add(3);
        assertEquals(4, list.capacity());
        list.addFirst(10);
        assertEquals(4, list.capacity());
        list.add(100);
        assertEquals(8, list.capacity());
    }

    @Test
    @DisplayName("Resize for remove test case")
    public void testResizeForRemove() {
        MyGenericArrayList<Integer> list = new MyGenericArrayList<>(1);
        assertEquals(1, list.capacity());
        list.add(1);
        assertEquals(1, list.capacity());
        list.add(2);
        assertEquals(2, list.capacity());
        list.add(3);
        assertEquals(4, list.capacity());
        list.addFirst(10);
        assertEquals(4, list.capacity());
        list.add(100);
        assertEquals(8, list.capacity());

        list.removeLast();
        list.removeLast();
        list.removeLast();
        assertEquals(4, list.capacity());
        list.removeLast();
    }

    @Test
    @DisplayName("CheckIndex exception test case")
    public void testCheckIndexException() {
        MyGenericArrayList<Integer> list = new MyGenericArrayList<>();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> list.get(1));
        assertEquals("out of range index", exception.getMessage());

        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> list.get(-1));
        assertEquals("out of range index", exception2.getMessage());
    }

    @Test
    @DisplayName("CheckIndexForRemove exception test case")
    public void testCheckIndexForRemoveException() {
        MyGenericArrayList<Integer> list = new MyGenericArrayList<>();
        list.add(1);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> list.remove(2));
        assertEquals("out of range index", exception.getMessage());

        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> list.remove(-1));
        assertEquals("out of range index", exception2.getMessage());
    }

    @Test
    @DisplayName("toString test case")
    public void testToString() {
        MyGenericArrayList<Integer> list = new MyGenericArrayList<>(5);
        list.add(1);
        list.add(2);
        list.add(3);
        
        assertEquals("Array size = 3, capacity = 5 \n[1, 2, 3]", list.toString());
    }
}
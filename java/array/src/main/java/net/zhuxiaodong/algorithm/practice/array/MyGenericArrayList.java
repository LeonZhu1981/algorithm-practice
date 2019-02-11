package net.zhuxiaodong.algorithm.practice.array;

import java.lang.IllegalArgumentException;
import java.lang.StringBuilder;

/**
 * For my algorithm practice, implement a generic array.
 * For example:
 * <pre>
 *    MyGenericArrayList<Integer> a = new MyGenericArrayList(100);
 *    a.add(1);
 * </pre>
 *
 * @author  leon zhu
 * @version %I%, %G%
 * @see java standard ArrayList http://hg.openjdk.java.net/jdk8/jdk8/jdk/file/tip/src/share/classes/java/util/ArrayList.java
 */
public class MyGenericArrayList<T> {
    private int size;
    private T[] data;
    private static final int initializeCapacity = 10;
    private final int initializeReCapacity = 2;

    /**
     * Constructor.
     */
    public MyGenericArrayList() {
        this(initializeCapacity);
    }

    /**
     * Constructor.
     * @param     capacity initialize capacity.
     */
    public MyGenericArrayList(int capacity) {
        this.size = 0;
        this.data = (T[]) new Object[capacity];
    }

    
    /**
     * Returns the element size of array.
     *
     * @return    the element size of array.
     */
    public int count() {
        return size;
    }

    /**
     * Returns the array capacity.
     *
     * @return    the array capacity.
     */
    public int capacity() {
        return data.length;
    }

    /**
     * Returns whether the array is empty.
     *
     * @return    whether the array is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    public void set(int index, T e) {
        checkIndex(index);
        data[index] = e;
    }

    public T get(int index) {
        checkIndex(index);
        return data[index];
    }

    public boolean contains(T e) {
        return find(e) >= 0 ? true : false;
    }

    public int find(T e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public void insert(int index, T e) {
        checkIndex(index);

        if (size == data.length) {
            resize(initializeReCapacity * data.length);
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    public void addFirst(T e) {
        insert(0, e);
    }

    public void add(T e) {
        insert(size, e);
    }

    public T remove(int index) {
        checkIndexForRemove(index);
        T result = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;

        if (size == data.length / 4 
            && data.length / 2 != 0) {
            resize(data.length / 2);
        }

        return result;
    }

    public T removeFirst() {
        return remove(0);
    }

    public T removeLast() {
        return remove(size - 1);
    }

    public boolean removeElement(T e) {
        int index = find(e);
        if (index >= 0) {
            remove(index);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Array size = %d, capacity = %d \n", size, data.length));
        builder.append('[');
        for (int i = 0; i < size; i++) {
            builder.append(data[i]);
            if (i < size - 1) {
                builder.append(", ");
            }
        }
        builder.append(']');
        return builder.toString();
    }

    /**
     * Check index is validate.
     * 
     * @param     index  the index of the array.
     * @exception IllegalArgumentException 
     *              if the index is not in the range <code>0</code> 
     *              to <code>length()-1</code>.
     */
    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("out of range index");
        }
    }

    /**
     * Check index is validate when remove operation.
     * 
     * @param     index  the index of the array.
     * @exception IllegalArgumentException 
     *              if the index is not in the range <code>0</code> 
     *              to <code>length()</code>.
     */
    private void checkIndexForRemove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("out of range index");
        }
    }

    /**
     * Use System.arraycopy to copy to new array.
     * 
     * @param     newCapacity  new capacity.
     */
    private void resize(int newCapacity) {
        T[] newData = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}
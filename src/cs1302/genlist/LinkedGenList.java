package cs1302.genlist;

import cs1302.genlist.Container;
import cs1302.genlistadt.GenList;
import cs1302.genlist.GenListIterator;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.BinaryOperator;
import java.util.function.IntFunction;

// import java.util.function.*;

/**
 * Implements a generic GenList interface along with a generic node {@code Container}
 * to create a generic list object.
 *
 * @param T  the reference type of the list.
 */
public class LinkedGenList<T> implements GenList<T> {

    /** Internal size counter.*/
    private int size;

    /** Head node of the list.*/
    private Container<T> head;
    private Container<T> temp;

    /**
     * Default constructor constructing a {@code LinkedGenList} object
     * with size initialized to 0.
     */
    public LinkedGenList() {

        this.size = 0;
        this.head = new Container<T>();
        this.temp = new Container<T>();

    } // LinkedGenList

    /**
     * Copy constructor creating a deep copied {@code LinkedGenList} object.
     *
     * @param other the list to copy of interface type {@code GenList<U>}
     * @param <U> type such that U extends T.
     */
    public <U extends T> LinkedGenList(GenList<U> other) {
        this.size = 0;
        this.head = new Container<T>();
        this.temp = new Container<T>();
        add(other);
    } // LinkedGenList

    /**
     * Returns a {@code Container<T>} object at the tail of the list.
     * This represents the last item in the list.
     *
     * @return  {@code tail} the last item in the list or null
     * if there are no items in the list.
     */
    private Container<T> getTail() {
        Container<T> tail = new Container<T>();
        tail = head;

        while (tail.getNext() != null) {
            tail = tail.getNext();
        }

        return tail;
    }


    /**
     * {@inheritDoc}
     */
    public int size() {
        if (size >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        return size;
    } // size

    /**
     * {@inheritDoc}
     */
    public boolean isEmpty() {

        if (size == 0) {
            return true;
        }

        return false;
    } // isEmpty

    /**
     * {@inheritDoc}
     */
    public boolean contains(T o) {
        if (o == null) {
            throw new NullPointerException("not yet implemented");
        }

        temp = head;
        for (int i = 0; i < size; i++) {
            if (get(i).equals(o)) {
                return true;
            }
        }

        return false;
    } // contains

    /**
     * {@inheritDoc}
     */
    public <U extends T> boolean add(GenList<U> list) {
        if (list == null) {
            throw new NullPointerException("obj is null");
        }

        if (list.size() == 0) {
            return false;
        }

        GenList<U> l = new LinkedGenList<U>();

        // account for self-referencing by creating a new list
        for (int i = 0; i < list.size(); i++) {
            l.add(list.get(i));
        }

        for (int i = 0; i < l.size(); i++) {
            add(l.get(i));
        }


        return true;
    } // add


    /**
     * {@inheritDoc}
     */
    public <U extends T> boolean add(int index, GenList<U> list) {
        if (list == null) {
            throw new NullPointerException("obj is null");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index is out of range");
        }

        if (list.size() == 0) {
            return false;
        }

        GenList<U> l = new LinkedGenList<U>();

        // account for self-referencing by creating a new list
        for (int i = 0; i < list.size(); i++) {
            l.add(list.get(i));
        }

        for (int i = 0; i < l.size(); i++) {
            add(index, l.get(i));
            index++;
        }

        return true;
    } // add


    /**
     * {@inheritDoc}
     */
    public boolean add(int index, T obj) {
        if (obj == null) {
            throw new NullPointerException("obj is null");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index is out of range");
        }

        Container<T> n = new Container<T>(obj);
        temp = head;

        if (index == 0) {
            head = n;
            n.setNext(temp);

        } else {
            for (int i = 0; i < index - 1; i++) {
                temp = temp.getNext();
            }

            n.setNext(temp.getNext());
            temp.setNext(n);
        }

        size++;
        return true;
    } // add

    /**
     * {@inheritDoc}
     */
    public boolean add(T obj) {
        if (obj == null) {
            throw new NullPointerException("obj is null");
        }

        // if size is 0, set contents of head directly
        if (size == 0) {
            head.set(obj);

        } else {
            Container<T> n = new Container<T>(obj);
            temp = getTail();
            temp.setNext(n);
        }

        size++;
        return true;

    } // add

    /**
     * {@inheritDoc}
     */
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("index is out of range");
        }

        temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }

        return temp.get();
    } // get

    /**
     * {@inheritDoc}
     */
    public T set(int index, T obj) {
        if (obj == null) {
            throw new NullPointerException("obj is null");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index is out of range");
        }

        if (index == 0) {
            T o = head.get();
            head.set(obj);
            return o;
        }

        temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }

        T o = temp.get();
        temp.set(obj);
        return o;
    } // set

    /**
     * {@inheritDoc}
     */
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index is out of range");
        }

        // bump head up one if the first index
        if (index == 0) {
            T o = head.get();
            head = head.getNext();
            size--;
            return o;
        }

        temp = head;
        // temp to index-1 --> after to 2 after temp --> grab
        // the removed --> temp = after -->
        for (int i = 0; i < index - 1; i++) {
            temp = temp.getNext();
        }

        Container<T> after = temp.getNext().getNext();
        T o = temp.getNext().get();
        temp.setNext(after);
        size--;
        return o;


    } // remove

    /**
     * {@inheritDoc}
     */
    public int indexOf(T obj) {
        if (obj == null) {
            throw new NullPointerException("specified string is null");
        }

        temp = head;
        for (int i = 0; i < size; i++) {
            if (temp.get().equals(obj)) {
                return i;
            }
            temp = temp.getNext();
        }
        return -1;
    } // indexOf

    /**
     * {@inheritDoc}
     */
    public void clear() {
        head = new Container<T>();
        size = 0;
    } // clear

    /**
     * {@inheritDoc}
     */
    public GenList<T> distinct() {
        throw new UnsupportedOperationException("not yet implemented");
    } // distinct

    /**
     * {@inheritDoc}
     */
    public GenList<T> reverse() {
        GenList<T> r = new LinkedGenList<T>();

        for (int i = size - 1; i >= 0; i--) {
            r.add(get(i));
        }

        return r;
    } // reverse

    /**
     * {@inheritDoc}
     */
    public String makeString(String sep) {
        if (size == 0) {
            return "";
        }

        String s = "";
        int sepLen = sep.length();
        temp = head;

        for (int i = 0; i < this.size; i++) {
            s += temp.get();
            s += sep;
            temp = temp.getNext();
        }

        return s.substring(0, s.length() - sepLen);

    } // makeString

    /**
     * {@inheritDoc}
     */
    public GenList<T> splice(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("illegal endpoint value");
        }

        GenList<T> s = new LinkedGenList<T>();
        temp = head;

        for (int i = 0; i < fromIndex; i++) {
            temp = temp.getNext();
        }

        for (int i = fromIndex; i < toIndex; i++) {
            s.add(temp.get());
            temp = temp.getNext();
        }

        return s;
    } // splice

    /**
     * {@inheritDoc}
     */
    public T min(Comparator<T> c) {
        if (c == null) {
            throw new NullPointerException("specified comparator is null");
        }
        if (size == 0) {
            return null;
        }

        T min = get(0);

        for (int i = 1; i < size; i++) {
            int val = c.compare(min, get(i));
            if (val > 0) {
                min = get(i);
            }
        }

        return min;
    } // min

    /**
     * {@inheritDoc}
     */
    public T max(Comparator<T> c) {
        if (c == null) {
            throw new NullPointerException("specified comparator is null");
        }
        if (size == 0) {
            return null;
        }

        T max = get(0);

        for (int i = 1; i < size; i++) {
            int val = c.compare(max, get(i));
            if (val < 0) {
                max = get(i);
            }
        }

        return max;
    } // max

    /**
     * {@inheritDoc}
     */
    public GenList<T> filter(Predicate<T> p) {
        if (p == null) {
            throw new NullPointerException("specified predicate is null");
        }

        // append elements to genlist if they pass the test
        GenList<T> n = new LinkedGenList<T>();

        for (int i = 0; i < size; i++) {
            T e = get(i);

            if (p.test(e)) {
                n.add(e);
            }
        }

        return n;
    } // filter

    /**
     * {@inheritDoc}
     */
    public <R> GenList<R> map(Function<T,R> f) {
        if (f == null) {
            throw new NullPointerException("specified function is null");
        }

        GenList<R> r = new LinkedGenList<R>();

        for (int i = 0; i < size; i++) {
            r.add(f.apply(get(i)));
        }

        if (r == null) {
            throw new NullPointerException("result of apply using the function is null");
        }

        return r;
    } // map

    /**
     * {@inheritDoc}
     */
    public boolean allMatch(Predicate<T> p) {

        for (int i = 0; i < size; i++) {
            if (!p.test(get(i))) {
                return false;
            }
        }

        return true;

    } // allMatch

    /**
     * {@inheritDoc}
     */
    public boolean anyMatch(Predicate<T> p) {

        for (int i = 0; i < size; i++) {
            if (p.test(get(i))) {
                return true;
            }
        }

        return false;

    } // anyMatch

    /**
     * {@inheritDoc}
     */
    public T[] toArray(IntFunction<T[]> gen) {
        T[] n = gen.apply(size);

        temp = head;
        for (int i = 0; i < size; i++) {
            n[i] = temp.get();
            temp = temp.getNext();
        }

        return n;
    } // toArray

    /**
     * {@inheritDoc}
     */
    public T reduce(T start, BinaryOperator<T> f) {
        if  (f == null) {
            throw new NullPointerException("specified function is null");
        }

        T result = start;
        for (T e : this) {
            result = f.apply(result, e);
        }

        if (result == null) {
            throw new NullPointerException("specified result is none");
        }

        return result;
    } // reduce


    /**
     * {@inheritDoc}
     */
    @Override
    public String makeString() {
        if (this.size == 0) {
            return "";
        }

        String s = "";

        temp = head;
        for (int i = 0; i < size; i++) {
            s += temp.get().toString();
            temp = temp.getNext();
        }

        return s;
    }

    /**
     * {@inheritDoc}
     */
    public String makeString(String start, String sep, String end) {
        if (this.size == 0) {
            return "";
        }

        String s = "";
        temp = head;

        s += start;

        for (int i = 0; i < size - 1; i++) {
            s += temp.get();
            s += sep;
            temp = temp.getNext();
        }

        s += temp.get();
        s += end;

        return s;
    }

    /**
     * Creates a {@code GenList} object for use in other methods as a shortcut
     * to assigning it in that method.
     *
     * @return a new gen list containing the contents of this list.
     */
    private GenList<T> toNewList() {
        GenList<T> n = new LinkedGenList<T>();

        for (int i = 0; i < size; i++) {
            n.add(this.get(i));
        }

        return n;
    }

    @Override
    public Iterator<T> iterator() {
        return new GenListIterator<T>(toNewList());
    }
}

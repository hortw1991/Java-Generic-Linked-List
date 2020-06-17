package cs1302.genlist;

import java.util.Iterator;
import java.util.NoSuchElementException;
import cs1302.genlistadt.GenList;

/**
 * Implements the iterator class for use with generic GenLists.
 */
public class GenListIterator<T> implements Iterator<T> {
    private int pos = 0;
    private int size;

    GenList<T> list;

    /**
     * Constructs a new {@code GenList<T>} iterator object.
     *
     * @param list  a {@code GenList<T>} used to set instance vars.
     */
    public GenListIterator(GenList<T> list) {
        this.size = list.size();
        this.list = list;
    }

    /**
     * Determines if there are more elements available for iteration.
     *
     * @return  true if the list has more elements.
     */
    public boolean hasNext() {
        return pos < size;
    }

    /**
     * Returns the next element in the list.
     *
     * @return  the next element in the list.
     */
    public T next() {
        if (!(this.hasNext())) {
            throw new NoSuchElementException();
        }

        T elem = list.get(pos);
        pos++;
        return elem;
    }
}

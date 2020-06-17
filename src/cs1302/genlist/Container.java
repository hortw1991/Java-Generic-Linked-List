package cs1302.genlist;

/**
 * Represents a generic container for different object types.
 *
 * @param T a generic representing the content type.
 */
public class Container <T> {
    
    /** A reference to the contents of this container. */
    private T contents;

    /** A reference to another {@code Container}. */
    private Container<T> next;

    /**
     * Constructs a {@code Container} with both {@code contents} and {@code next} set
     * to {@code null}.
     */
    public Container() {
        this.contents = null;
        this.next = null;
    }

    /**
     * Constructs a {@code Container} with the {@code contents} properly set and the
     * {@code next} property unset.
     *
     * @param contents reference to the associated type.
     * @throws NullPointerException if {@code contents} is {@code null}.
     */
    public Container(T contents) {
        if (contents == null) {
            throw new NullPointerException("contents are null");
        }

        set(contents);
        this.next = null;
    }

    /**
     * Constructs a {@code Container} with the {@code contents} properly set and the
     * {@code next} property set.
     *
     * @param contents reference to the associated type,
     * @param next reference to the next container.
     * @throws NullPointerException if {@code contents} is {@code null}.
     */
    public Container(T contents, Container<T> next) {
        if (contents == null) {
            throw new NullPointerException("contents are null");
        }
        set(contents);
        this.next = next;
    }

    /**
     * Returns the value of the {@code next} property.
     *
     * @return the reference to the next container.
     */
    public Container<T> getNext() {
        return this.next;
    }

    /**
     * Sets the value of the {@code next} property for this container.
     *
     * @param next a reference to the next container.
     */
    public void setNext(Container<T> next) {
        this.next = next;
    }

    /**
     * Sets the value of the {@code contents} property for this container.
     *
     * @param contents reference to the associated type.
     * @throws NullPointerException is the {@code contents} is {@code null}.
     */
    public void set(T contents) {
        if (contents == null) {
            throw new NullPointerException("contents is null");
        }

        this.contents = contents;
    }

    /**
     * Returns the value of the {@code contents} property for this container.
     *
     * @return the reference to the associated object.
     */
    public T get() {
        return contents;
    }
}

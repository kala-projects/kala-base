package asia.kala.collection.mutable;

import asia.kala.collection.Enumerator;
import asia.kala.collection.IndexedSeq;
import asia.kala.collection.TraversableOnce;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@SuppressWarnings("unchecked")
public final class ArrayBuffer<E> extends AbstractBuffer<E> implements IndexedSeq<E>, Serializable {
    private static final long serialVersionUID = 6323541372807433607L;
    private static final int hashMagic = -1142720889;

    private static final int DEFAULT_CAPACITY = 16;

    @NotNull
    private Object[] elements;
    private int size;

    ArrayBuffer(@NotNull Object[] elements, int size) {
        assert elements != null;

        this.elements = elements;
        this.size = size;
    }

    public ArrayBuffer() {
        this(MArray.EMPTY_ARRAY, 0);
    }

    public ArrayBuffer(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("illegal initialCapacity: " + initialCapacity);
        }

        this.elements = initialCapacity == 0 ? MArray.EMPTY_ARRAY : new Object[initialCapacity];
        this.size = 0;
    }

    @NotNull
    @Contract("_ -> new")
    public static <E> ArrayBuffer<E> of(E... elements) {
        Objects.requireNonNull(elements);
        int length = elements.length;
        if (length == 0) {
            return new ArrayBuffer<>(MArray.EMPTY_ARRAY, 0);
        }
        Object[] newValues = new Object[length];
        System.arraycopy(elements, 0, newValues, 0, length);
        return new ArrayBuffer<>(newValues, length);
    }


    private void grow() {
        grow(size + 1);
    }

    private void grow(int minCapacity) {
        Object[] newArray = growArray(minCapacity);
        if (elements.length != 0) {
            System.arraycopy(elements, 0, newArray, 0, size);
        }
        elements = newArray;
    }

    private Object[] growArray(int minCapacity) {
        int oldCapacity = elements.length;
        if (oldCapacity == 0) {
            return new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }

        int newCapacity = Math.max(Math.max(oldCapacity, minCapacity), oldCapacity + oldCapacity >> 1);
        return new Object[newCapacity];
    }

    private void checkInBound(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
    }

    //
    // -- Buffer
    //

    @Override
    public final void append(E value) {
        if (size == elements.length) {
            grow();
        }
        elements[size++] = value;
    }

    @Override
    public final void appendAll(@NotNull TraversableOnce<? extends E> collection) {
        Objects.requireNonNull(collection);

        int ks = collection.knownSize();
        if (ks > 0 && size + ks > elements.length) {
            grow(size + ks);
        }
        for (E e : collection) {
            this.append(e);
        }
    }

    @Override
    public final void prepend(E value) {
        Object[] values = elements;
        if (size == values.length) {
            values = growArray(size + 1);
        }
        System.arraycopy(elements, 0, values, 1, size);
        values[0] = value;
        this.elements = values;
        ++size;
    }

    @Override
    public final void prependAll(@NotNull TraversableOnce<? extends E> collection) {
        Objects.requireNonNull(collection);
        if (collection instanceof IndexedSeq<?>) {
            IndexedSeq<?> seq = (IndexedSeq<?>) collection;
            int s = seq.size();
            Object[] values = elements;
            if (values.length <= size + s) {
                values = growArray(size + s);
            }
            for (int i = 0; i < s; i++) {
                values[i] = seq.get(i);
            }
            System.arraycopy(elements, 0, values, s, size);
            elements = values;
            size += s;
            return;
        }

        Object[] cv = collection.toArray(Object[]::new);
        if (cv.length == 0) {
            return;
        }

        Object[] values = elements;
        if (values.length <= size + cv.length) {
            values = growArray(size + cv.length);
        }

        System.arraycopy(elements, 0, values, cv.length, size);
        System.arraycopy(cv, 0, values, 0, cv.length);
        elements = values;
        size += cv.length;
    }

    @Override
    public final E remove(int index) {
        checkInBound(index);
        E v = (E) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index);
        --size;
        return v;
    }

    @Override
    public final void remove(int index, int count) {
        if (index < 0 || index > size - count) {
            throw new IndexOutOfBoundsException(String.format("index: %d, count: %d", index, count));
        }

        System.arraycopy(elements, index + count, elements, index, size - (index + count));
        size -= count;
    }

    @Override
    public final void clear() {
        Arrays.fill(elements, null);
        size = 0;
    }

    //
    // -- MSeq
    //

    @Override
    public final E get(int index) {
        checkInBound(index);
        return (E) elements[index];
    }

    @Override
    public final void set(int index, E newValue) {
        checkInBound(index);
        elements[index] = newValue;
    }


    //
    // -- MCollection
    //

    @Override
    public final String className() {
        return "ArrayBuffer";
    }

    @Override
    public int size() {
        return size;
    }

    @NotNull
    @Override
    public final Enumerator<E> iterator() {
        return (Enumerator<E>) Enumerator.ofArray(elements, 0, size);
    }

    //
    // -- Serializable
    //

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.write(size);
        if (size != 0) {
            Object[] values = elements.length == size ? elements : Arrays.copyOf(elements, size);
            out.writeObject(values);
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        this.size = in.readInt();
        if (size == 0) {
            elements = MArray.EMPTY_ARRAY;
        } else {
            elements = (Object[]) in.readObject();
        }
    }

    //
    // -- Object
    //

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArrayBuffer<?>)) {
            return false;
        }

        ArrayBuffer<?> other = (ArrayBuffer<?>) o;

        if (size != other.size) {
            return false;
        }
        return Arrays.equals(elements, other.elements);
    }

    @Override
    public final int hashCode() {
        return Arrays.hashCode(elements) + hashMagic;
    }
}

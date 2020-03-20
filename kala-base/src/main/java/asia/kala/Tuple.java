package asia.kala;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.IntFunction;

import org.intellij.lang.annotations.Flow;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * The base class of all tuples.
 *
 * @author Glavo
 * @see HList
 */
public abstract class Tuple implements Serializable {
    Tuple() {
    }

    /**
     * Returns the number of elements of this {@code Tuple}.
     *
     * @return the number of elements of this {@code Tuple}
     */
    @Contract(pure = true)
    public abstract int arity();

    /**
     * Returns the element at the specified position in this {@code Tuple}.
     *
     * @return the element at the specified position in this {@code Tuple}
     * @throws IndexOutOfBoundsException if the index is out of range ({@code index < 0 || index >=
     *                                   arity()})
     * @apiNote this method is not type safe, please use {@code Tuple.componentN(tuple)} to get the
     * Nth element of the tuple (when {@code N <= 18}).
     */
    @Contract(pure = true)
    @Flow(sourceIsContainer = true)
    public abstract <U> U elementAt(int index);

    /**
     * Return a new tuple by prepending the head to `this` tuple.
     *
     * @return a new tuple by prepending the head to `this` tuple
     */
    @Contract(pure = true)
    public abstract <H> HList<H, ? extends Tuple> cons(H head);

    /**
     * Returns an array containing all of the elements in this tuple.
     *
     * @return an array containing all of the elements in this tuple
     */
    @NotNull
    @Contract(value = "-> new", pure = true)
    public Object[] toJavaArray() {
        return toJavaArray(Object[]::new);
    }

    /**
     * Returns an array containing all of the elements in this tuple, using the provided {@code
     * generator} function to allocate the returned array.
     *
     * @return an array containing all of the elements in this tuple
     * @throws ArrayStoreException if any element of this tuple cannot be stored in the generated
     *                             array because the runtime type does not match
     */
    @NotNull
    @Contract(pure = true)
    public abstract <U> U[] toJavaArray(@NotNull IntFunction<U[]> generator);

    @NotNull
    public static Tuple0 of() {
        return Tuple0.INSTANCE;
    }

    /**
     * Creates a tuple of 1 element.
     *
     * @param <T1> type of the 1st element
     * @param t1   the 1st element
     * @return a tuple of 1 element
     */
    @NotNull
    @Contract("_ -> new")
    public static <T1> Tuple1<T1> of(T1 t1) {
        return new Tuple1<>(t1);
    }

    /**
     * Creates a tuple of 2 elements.
     *
     * @param <T1> type of the 1st element
     * @param <T2> type of the 2nd element
     * @param t1   the 1st element
     * @param t2   the 2nd element
     * @return a tuple of 2 elements
     */
    @NotNull
    @Contract("_, _ -> new")
    public static <T1, T2> Tuple2<T1, T2> of(T1 t1, T2 t2) {
        return new Tuple2<>(t1, t2);
    }

    /**
     * Creates a tuple of 3 elements.
     *
     * @param <T1> type of the 1st element
     * @param <T2> type of the 2nd element
     * @param <T3> type of the 3rd element
     * @param t1   the 1st element
     * @param t2   the 2nd element
     * @param t3   the 3rd element
     * @return a tuple of 3 elements
     */
    @NotNull
    @Contract("_, _, _ -> new")
    public static <T1, T2, T3> Tuple3<T1, T2, T3> of(T1 t1, T2 t2, T3 t3) {
        return new Tuple3<>(t1, t2, t3);
    }

    /**
     * Creates a tuple of 4 elements.
     *
     * @param <T1> type of the 1st element
     * @param <T2> type of the 2nd element
     * @param <T3> type of the 3rd element
     * @param <T4> type of the 4th element
     * @param t1   the 1st element
     * @param t2   the 2nd element
     * @param t3   the 3rd element
     * @param t4   the 4th element
     * @return a tuple of 4 elements
     */
    @NotNull
    @Contract("_, _, _, _ -> new")
    public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> of(T1 t1, T2 t2, T3 t3, T4 t4) {
        return new Tuple4<>(t1, t2, t3, t4);
    }

    /**
     * Creates a tuple of 5 elements.
     *
     * @param <T1> type of the 1st element
     * @param <T2> type of the 2nd element
     * @param <T3> type of the 3rd element
     * @param <T4> type of the 4th element
     * @param <T5> type of the 5th element
     * @param t1   the 1st element
     * @param t2   the 2nd element
     * @param t3   the 3rd element
     * @param t4   the 4th element
     * @param t5   the 5th element
     * @return a tuple of 5 elements
     */
    @NotNull
    @Contract("_, _, _, _, _ -> new")
    public static <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> of(
            T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
        return new Tuple5<>(t1, t2, t3, t4, t5);
    }

    /**
     * Creates a tuple of 6 elements.
     *
     * @param <T1> type of the 1st element
     * @param <T2> type of the 2nd element
     * @param <T3> type of the 3rd element
     * @param <T4> type of the 4th element
     * @param <T5> type of the 5th element
     * @param <T6> type of the 6th element
     * @param t1   the 1st element
     * @param t2   the 2nd element
     * @param t3   the 3rd element
     * @param t4   the 4th element
     * @param t5   the 5th element
     * @param t6   the 6th element
     * @return a tuple of 6 elements
     */
    @NotNull
    @Contract("_, _, _, _, _, _ -> new")
    public static <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6> of(
            T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
        return new Tuple6<>(t1, t2, t3, t4, t5, t6);
    }

    /**
     * Creates a tuple of 7 elements.
     *
     * @param <T1> type of the 1st element
     * @param <T2> type of the 2nd element
     * @param <T3> type of the 3rd element
     * @param <T4> type of the 4th element
     * @param <T5> type of the 5th element
     * @param <T6> type of the 6th element
     * @param <T7> type of the 7th element
     * @param t1   the 1st element
     * @param t2   the 2nd element
     * @param t3   the 3rd element
     * @param t4   the 4th element
     * @param t5   the 5th element
     * @param t6   the 6th element
     * @param t7   the 7th element
     * @return a tuple of 7 elements
     */
    @NotNull
    @Contract("_, _, _, _, _, _, _ -> new")
    public static <T1, T2, T3, T4, T5, T6, T7> Tuple7<T1, T2, T3, T4, T5, T6, T7> of(
            T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
        return new Tuple7<>(t1, t2, t3, t4, t5, t6, t7);
    }

    /**
     * Creates a tuple of 8 elements.
     *
     * @param <T1> type of the 1st element
     * @param <T2> type of the 2nd element
     * @param <T3> type of the 3rd element
     * @param <T4> type of the 4th element
     * @param <T5> type of the 5th element
     * @param <T6> type of the 6th element
     * @param <T7> type of the 7th element
     * @param <T8> type of the 8th element
     * @param t1   the 1st element
     * @param t2   the 2nd element
     * @param t3   the 3rd element
     * @param t4   the 4th element
     * @param t5   the 5th element
     * @param t6   the 6th element
     * @param t7   the 7th element
     * @param t8   the 8th element
     * @return a tuple of 8 elements
     */
    @NotNull
    @Contract("_, _, _, _, _, _, _, _ -> new")
    public static <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> of(
            T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {
        return new Tuple8<>(t1, t2, t3, t4, t5, t6, t7, t8);
    }

    /**
     * Creates a tuple of 9 elements.
     *
     * @param <T1> type of the 1st element
     * @param <T2> type of the 2nd element
     * @param <T3> type of the 3rd element
     * @param <T4> type of the 4th element
     * @param <T5> type of the 5th element
     * @param <T6> type of the 6th element
     * @param <T7> type of the 7th element
     * @param <T8> type of the 8th element
     * @param <T9> type of the 9th element
     * @param t1   the 1st element
     * @param t2   the 2nd element
     * @param t3   the 3rd element
     * @param t4   the 4th element
     * @param t5   the 5th element
     * @param t6   the 6th element
     * @param t7   the 7th element
     * @param t8   the 8th element
     * @param t9   the 9th element
     * @return a tuple of 9 elements
     */
    @NotNull
    @Contract("_, _, _, _, _, _, _, _, _ -> new")
    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9>
    Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> of(
            T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
        return new Tuple9<>(t1, t2, t3, t4, t5, t6, t7, t8, t9);
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Contract("_ -> new")
    public static <T extends Tuple> T of(Object... values) {
        Objects.requireNonNull(values);
        switch (values.length) {
            case 0:
                return (T) Tuple0.INSTANCE;
            case 1:
                return (T) new Tuple1<>(values[0]);
            case 2:
                return (T) new Tuple2<>(values[0], values[1]);
            case 3:
                return (T) new Tuple3<>(values[0], values[1], values[2]);
            case 4:
                return (T) new Tuple4<>(values[0], values[1], values[2], values[3]);
            case 5:
                return (T) new Tuple5<>(values[0], values[1], values[2], values[3], values[4]);
            case 6:
                return (T)
                        new Tuple6<>(
                                values[0], values[1], values[2], values[3], values[4], values[5]);
            case 7:
                return (T)
                        new Tuple7<>(
                                values[0], values[1], values[2], values[3], values[4], values[5],
                                values[6]);
            case 8:
                return (T)
                        new Tuple8<>(
                                values[0], values[1], values[2], values[3], values[4], values[5],
                                values[6], values[7]);
            case 9:
                return (T)
                        new Tuple9<>(
                                values[0], values[1], values[2], values[3], values[4], values[5],
                                values[6], values[7], values[8]);
            default:
                return (T) new TupleXXL(values.clone());
        }
    }

    /**
     * Return the 1st element of the {@code tuple}.
     *
     * @param <T>   type of the element
     * @param tuple a {@code Tuple}
     * @return the 1st element of the {@code tuple}
     */
    public static <T> T component1(@NotNull HList<T, ?> tuple) {
        Objects.requireNonNull(tuple);
        return tuple.elementAt(0);
    }

    /**
     * Return the 2nd element of the {@code tuple}.
     *
     * @param <T>   type of the element
     * @param tuple a {@code Tuple}
     * @return the 2nd element of the {@code tuple}
     */
    public static <T> T component2(@NotNull HList<?, ? extends HList<T, ?>> tuple) {
        Objects.requireNonNull(tuple);
        return tuple.elementAt(1);
    }

    /**
     * Return the 3rd element of the {@code tuple}.
     *
     * @param <T>   type of the element
     * @param tuple a {@code Tuple}
     * @return the 3rd element of the {@code tuple}
     */
    public static <T> T component3(
            @NotNull HList<?, ? extends HList<?, ? extends HList<T, ?>>> tuple) {
        Objects.requireNonNull(tuple);
        return tuple.elementAt(2);
    }

    /**
     * Return the 4th element of the {@code tuple}.
     *
     * @param <T>   type of the element
     * @param tuple a {@code Tuple}
     * @return the 4th element of the {@code tuple}
     */
    public static <T> T component4(
            @NotNull
                    HList<?, ? extends HList<?, ? extends HList<?, ? extends HList<T, ?>>>> tuple) {
        Objects.requireNonNull(tuple);
        return tuple.elementAt(3);
    }

    /**
     * Return the 5th element of the {@code tuple}.
     *
     * @param <T>   type of the element
     * @param tuple a {@code Tuple}
     * @return the 5th element of the {@code tuple}
     */
    public static <T> T component5(
            @NotNull
                    HList<
                            ?,
                            ? extends
                                    HList<
                                            ?,
                                            ? extends
                                                    HList<
                                                            ?,
                                                            ? extends
                                                                    HList<
                                                                            ?,
                                                                            ? extends
                                                                                    HList<
                                                                                            T,
                                                                                            ?>>>>>
                    tuple) {
        Objects.requireNonNull(tuple);
        return tuple.elementAt(4);
    }

    /**
     * Return the 6th element of the {@code tuple}.
     *
     * @param <T>   type of the element
     * @param tuple a {@code Tuple}
     * @return the 6th element of the {@code tuple}
     */
    public static <T> T component6(
            @NotNull
                    HList<
                            ?,
                            ? extends
                                    HList<
                                            ?,
                                            ? extends
                                                    HList<
                                                            ?,
                                                            ? extends
                                                                    HList<
                                                                            ?,
                                                                            ? extends
                                                                                    HList<
                                                                                            ?,
                                                                                            ? extends
                                                                                                    HList<
                                                                                                            T,
                                                                                                            ?>>>>>>
                    tuple) {
        Objects.requireNonNull(tuple);
        return tuple.elementAt(5);
    }

    /**
     * Return the 7th element of the {@code tuple}.
     *
     * @param <T>   type of the element
     * @param tuple a {@code Tuple}
     * @return the 7th element of the {@code tuple}
     */
    public static <T> T component7(
            @NotNull
                    HList<
                            ?,
                            ? extends
                                    HList<
                                            ?,
                                            ? extends
                                                    HList<
                                                            ?,
                                                            ? extends
                                                                    HList<
                                                                            ?,
                                                                            ? extends
                                                                                    HList<
                                                                                            ?,
                                                                                            ? extends
                                                                                                    HList<
                                                                                                            ?,
                                                                                                            ? extends
                                                                                                                    HList<
                                                                                                                            T,
                                                                                                                            ?>>>>>>>
                    tuple) {
        Objects.requireNonNull(tuple);
        return tuple.elementAt(6);
    }

    /**
     * Return the 8th element of the {@code tuple}.
     *
     * @param <T>   type of the element
     * @param tuple a {@code Tuple}
     * @return the 8th element of the {@code tuple}
     */
    public static <T> T component8(
            @NotNull
                    HList<
                            ?,
                            ? extends
                                    HList<
                                            ?,
                                            ? extends
                                                    HList<
                                                            ?,
                                                            ? extends
                                                                    HList<
                                                                            ?,
                                                                            ? extends
                                                                                    HList<
                                                                                            ?,
                                                                                            ? extends
                                                                                                    HList<
                                                                                                            ?,
                                                                                                            ? extends
                                                                                                                    HList<
                                                                                                                            ?,
                                                                                                                            ? extends
                                                                                                                                    HList<
                                                                                                                                            T,
                                                                                                                                            ?>>>>>>>>
                    tuple) {
        Objects.requireNonNull(tuple);
        return tuple.elementAt(7);
    }

    /**
     * Return the 9th element of the {@code tuple}.
     *
     * @param <T>   type of the element
     * @param tuple a {@code Tuple}
     * @return the 9th element of the {@code tuple}
     */
    public static <T> T component9(
            @NotNull
                    HList<
                            ?,
                            ? extends
                                    HList<
                                            ?,
                                            ? extends
                                                    HList<
                                                            ?,
                                                            ? extends
                                                                    HList<
                                                                            ?,
                                                                            ? extends
                                                                                    HList<
                                                                                            ?,
                                                                                            ? extends
                                                                                                    HList<
                                                                                                            ?,
                                                                                                            ? extends
                                                                                                                    HList<
                                                                                                                            ?,
                                                                                                                            ? extends
                                                                                                                                    HList<
                                                                                                                                            ?,
                                                                                                                                            ? extends
                                                                                                                                                    HList<
                                                                                                                                                            T,
                                                                                                                                                            ?>>>>>>>>>
                    tuple) {
        Objects.requireNonNull(tuple);
        return tuple.elementAt(8);
    }

    /**
     * Return the 10th element of the {@code tuple}.
     *
     * @param <T>   type of the element
     * @param tuple a {@code Tuple}
     * @return the 10th element of the {@code tuple}
     */
    public static <T> T component10(
            @NotNull
                    HList<
                            ?,
                            ? extends
                                    HList<
                                            ?,
                                            ? extends
                                                    HList<
                                                            ?,
                                                            ? extends
                                                                    HList<
                                                                            ?,
                                                                            ? extends
                                                                                    HList<
                                                                                            ?,
                                                                                            ? extends
                                                                                                    HList<
                                                                                                            ?,
                                                                                                            ? extends
                                                                                                                    HList<
                                                                                                                            ?,
                                                                                                                            ? extends
                                                                                                                                    HList<
                                                                                                                                            ?,
                                                                                                                                            ? extends
                                                                                                                                                    HList<
                                                                                                                                                            ?,
                                                                                                                                                            ? extends
                                                                                                                                                                    HList<
                                                                                                                                                                            T,
                                                                                                                                                                            ?>>>>>>>>>>
                    tuple) {
        Objects.requireNonNull(tuple);
        return tuple.elementAt(9);
    }

    /**
     * Return the 11th element of the {@code tuple}.
     *
     * @param <T>   type of the element
     * @param tuple a {@code Tuple}
     * @return the 11th element of the {@code tuple}
     */
    public static <T> T component11(
            @NotNull
                    HList<
                            ?,
                            ? extends
                                    HList<
                                            ?,
                                            ? extends
                                                    HList<
                                                            ?,
                                                            ? extends
                                                                    HList<
                                                                            ?,
                                                                            ? extends
                                                                                    HList<
                                                                                            ?,
                                                                                            ? extends
                                                                                                    HList<
                                                                                                            ?,
                                                                                                            ? extends
                                                                                                                    HList<
                                                                                                                            ?,
                                                                                                                            ? extends
                                                                                                                                    HList<
                                                                                                                                            ?,
                                                                                                                                            ? extends
                                                                                                                                                    HList<
                                                                                                                                                            ?,
                                                                                                                                                            ? extends
                                                                                                                                                                    HList<
                                                                                                                                                                            ?,
                                                                                                                                                                            ? extends
                                                                                                                                                                                    HList<
                                                                                                                                                                                            T,
                                                                                                                                                                                            ?>>>>>>>>>>>
                    tuple) {
        Objects.requireNonNull(tuple);
        return tuple.elementAt(10);
    }

    /**
     * Return the 12th element of the {@code tuple}.
     *
     * @param <T>   type of the element
     * @param tuple a {@code Tuple}
     * @return the 12th element of the {@code tuple}
     */
    public static <T> T component12(
            @NotNull
                    HList<
                            ?,
                            ? extends
                                    HList<
                                            ?,
                                            ? extends
                                                    HList<
                                                            ?,
                                                            ? extends
                                                                    HList<
                                                                            ?,
                                                                            ? extends
                                                                                    HList<
                                                                                            ?,
                                                                                            ? extends
                                                                                                    HList<
                                                                                                            ?,
                                                                                                            ? extends
                                                                                                                    HList<
                                                                                                                            ?,
                                                                                                                            ? extends
                                                                                                                                    HList<
                                                                                                                                            ?,
                                                                                                                                            ? extends
                                                                                                                                                    HList<
                                                                                                                                                            ?,
                                                                                                                                                            ? extends
                                                                                                                                                                    HList<
                                                                                                                                                                            ?,
                                                                                                                                                                            ? extends
                                                                                                                                                                                    HList<
                                                                                                                                                                                            ?,
                                                                                                                                                                                            ? extends
                                                                                                                                                                                                    HList<
                                                                                                                                                                                                            T,
                                                                                                                                                                                                            ?>>>>>>>>>>>>
                    tuple) {
        Objects.requireNonNull(tuple);
        return tuple.elementAt(11);
    }

    /**
     * Return the 13th element of the {@code tuple}.
     *
     * @param <T>   type of the element
     * @param tuple a {@code Tuple}
     * @return the 13th element of the {@code tuple}
     */
    public static <T> T component13(
            @NotNull
                    HList<
                            ?,
                            ? extends
                                    HList<
                                            ?,
                                            ? extends
                                                    HList<
                                                            ?,
                                                            ? extends
                                                                    HList<
                                                                            ?,
                                                                            ? extends
                                                                                    HList<
                                                                                            ?,
                                                                                            ? extends
                                                                                                    HList<
                                                                                                            ?,
                                                                                                            ? extends
                                                                                                                    HList<
                                                                                                                            ?,
                                                                                                                            ? extends
                                                                                                                                    HList<
                                                                                                                                            ?,
                                                                                                                                            ? extends
                                                                                                                                                    HList<
                                                                                                                                                            ?,
                                                                                                                                                            ? extends
                                                                                                                                                                    HList<
                                                                                                                                                                            ?,
                                                                                                                                                                            ? extends
                                                                                                                                                                                    HList<
                                                                                                                                                                                            ?,
                                                                                                                                                                                            ? extends
                                                                                                                                                                                                    HList<
                                                                                                                                                                                                            ?,
                                                                                                                                                                                                            ? extends
                                                                                                                                                                                                                    HList<
                                                                                                                                                                                                                            T,
                                                                                                                                                                                                                            ?>>>>>>>>>>>>>
                    tuple) {
        Objects.requireNonNull(tuple);
        return tuple.elementAt(12);
    }

    /**
     * Return the 14th element of the {@code tuple}.
     *
     * @param <T>   type of the element
     * @param tuple a {@code Tuple}
     * @return the 14th element of the {@code tuple}
     */
    public static <T> T component14(
            @NotNull
                    HList<
                            ?,
                            ? extends
                                    HList<
                                            ?,
                                            ? extends
                                                    HList<
                                                            ?,
                                                            ? extends
                                                                    HList<
                                                                            ?,
                                                                            ? extends
                                                                                    HList<
                                                                                            ?,
                                                                                            ? extends
                                                                                                    HList<
                                                                                                            ?,
                                                                                                            ? extends
                                                                                                                    HList<
                                                                                                                            ?,
                                                                                                                            ? extends
                                                                                                                                    HList<
                                                                                                                                            ?,
                                                                                                                                            ? extends
                                                                                                                                                    HList<
                                                                                                                                                            ?,
                                                                                                                                                            ? extends
                                                                                                                                                                    HList<
                                                                                                                                                                            ?,
                                                                                                                                                                            ? extends
                                                                                                                                                                                    HList<
                                                                                                                                                                                            ?,
                                                                                                                                                                                            ? extends
                                                                                                                                                                                                    HList<
                                                                                                                                                                                                            ?,
                                                                                                                                                                                                            ? extends
                                                                                                                                                                                                                    HList<
                                                                                                                                                                                                                            ?,
                                                                                                                                                                                                                            ? extends
                                                                                                                                                                                                                                    HList<
                                                                                                                                                                                                                                            T,
                                                                                                                                                                                                                                            ?>>>>>>>>>>>>>>
                    tuple) {
        Objects.requireNonNull(tuple);
        return tuple.elementAt(13);
    }

    /**
     * Return the 15th element of the {@code tuple}.
     *
     * @param <T>   type of the element
     * @param tuple a {@code Tuple}
     * @return the 15th element of the {@code tuple}
     */
    public static <T> T component15(
            @NotNull
                    HList<
                            ?,
                            ? extends
                                    HList<
                                            ?,
                                            ? extends
                                                    HList<
                                                            ?,
                                                            ? extends
                                                                    HList<
                                                                            ?,
                                                                            ? extends
                                                                                    HList<
                                                                                            ?,
                                                                                            ? extends
                                                                                                    HList<
                                                                                                            ?,
                                                                                                            ? extends
                                                                                                                    HList<
                                                                                                                            ?,
                                                                                                                            ? extends
                                                                                                                                    HList<
                                                                                                                                            ?,
                                                                                                                                            ? extends
                                                                                                                                                    HList<
                                                                                                                                                            ?,
                                                                                                                                                            ? extends
                                                                                                                                                                    HList<
                                                                                                                                                                            ?,
                                                                                                                                                                            ? extends
                                                                                                                                                                                    HList<
                                                                                                                                                                                            ?,
                                                                                                                                                                                            ? extends
                                                                                                                                                                                                    HList<
                                                                                                                                                                                                            ?,
                                                                                                                                                                                                            ? extends
                                                                                                                                                                                                                    HList<
                                                                                                                                                                                                                            ?,
                                                                                                                                                                                                                            ? extends
                                                                                                                                                                                                                                    HList<
                                                                                                                                                                                                                                            ?,
                                                                                                                                                                                                                                            ? extends
                                                                                                                                                                                                                                                    HList<
                                                                                                                                                                                                                                                            T,
                                                                                                                                                                                                                                                            ?>>>>>>>>>>>>>>>
                    tuple) {
        Objects.requireNonNull(tuple);
        return tuple.elementAt(14);
    }

    /**
     * Return the 16th element of the {@code tuple}.
     *
     * @param <T>   type of the element
     * @param tuple a {@code Tuple}
     * @return the 16th element of the {@code tuple}
     */
    public static <T> T component16(
            @NotNull
                    HList<
                            ?,
                            ? extends
                                    HList<
                                            ?,
                                            ? extends
                                                    HList<
                                                            ?,
                                                            ? extends
                                                                    HList<
                                                                            ?,
                                                                            ? extends
                                                                                    HList<
                                                                                            ?,
                                                                                            ? extends
                                                                                                    HList<
                                                                                                            ?,
                                                                                                            ? extends
                                                                                                                    HList<
                                                                                                                            ?,
                                                                                                                            ? extends
                                                                                                                                    HList<
                                                                                                                                            ?,
                                                                                                                                            ? extends
                                                                                                                                                    HList<
                                                                                                                                                            ?,
                                                                                                                                                            ? extends
                                                                                                                                                                    HList<
                                                                                                                                                                            ?,
                                                                                                                                                                            ? extends
                                                                                                                                                                                    HList<
                                                                                                                                                                                            ?,
                                                                                                                                                                                            ? extends
                                                                                                                                                                                                    HList<
                                                                                                                                                                                                            ?,
                                                                                                                                                                                                            ? extends
                                                                                                                                                                                                                    HList<
                                                                                                                                                                                                                            ?,
                                                                                                                                                                                                                            ? extends
                                                                                                                                                                                                                                    HList<
                                                                                                                                                                                                                                            ?,
                                                                                                                                                                                                                                            ? extends
                                                                                                                                                                                                                                                    HList<
                                                                                                                                                                                                                                                            ?,
                                                                                                                                                                                                                                                            ? extends
                                                                                                                                                                                                                                                                    HList<
                                                                                                                                                                                                                                                                            T,
                                                                                                                                                                                                                                                                            ?>>>>>>>>>>>>>>>>
                    tuple) {
        Objects.requireNonNull(tuple);
        return tuple.elementAt(15);
    }

    /**
     * Return the 17th element of the {@code tuple}.
     *
     * @param <T>   type of the element
     * @param tuple a {@code Tuple}
     * @return the 17th element of the {@code tuple}
     */
    public static <T> T component17(
            @NotNull
                    HList<
                            ?,
                            ? extends
                                    HList<
                                            ?,
                                            ? extends
                                                    HList<
                                                            ?,
                                                            ? extends
                                                                    HList<
                                                                            ?,
                                                                            ? extends
                                                                                    HList<
                                                                                            ?,
                                                                                            ? extends
                                                                                                    HList<
                                                                                                            ?,
                                                                                                            ? extends
                                                                                                                    HList<
                                                                                                                            ?,
                                                                                                                            ? extends
                                                                                                                                    HList<
                                                                                                                                            ?,
                                                                                                                                            ? extends
                                                                                                                                                    HList<
                                                                                                                                                            ?,
                                                                                                                                                            ? extends
                                                                                                                                                                    HList<
                                                                                                                                                                            ?,
                                                                                                                                                                            ? extends
                                                                                                                                                                                    HList<
                                                                                                                                                                                            ?,
                                                                                                                                                                                            ? extends
                                                                                                                                                                                                    HList<
                                                                                                                                                                                                            ?,
                                                                                                                                                                                                            ? extends
                                                                                                                                                                                                                    HList<
                                                                                                                                                                                                                            ?,
                                                                                                                                                                                                                            ? extends
                                                                                                                                                                                                                                    HList<
                                                                                                                                                                                                                                            ?,
                                                                                                                                                                                                                                            ? extends
                                                                                                                                                                                                                                                    HList<
                                                                                                                                                                                                                                                            ?,
                                                                                                                                                                                                                                                            ? extends
                                                                                                                                                                                                                                                                    HList<
                                                                                                                                                                                                                                                                            ?,
                                                                                                                                                                                                                                                                            ? extends
                                                                                                                                                                                                                                                                                    HList<
                                                                                                                                                                                                                                                                                            T,
                                                                                                                                                                                                                                                                                            ?>>>>>>>>>>>>>>>>>
                    tuple) {
        Objects.requireNonNull(tuple);
        return tuple.elementAt(16);
    }

    /**
     * Return the 18th element of the {@code tuple}.
     *
     * @param <T>   type of the element
     * @param tuple a {@code Tuple}
     * @return the 18th element of the {@code tuple}
     */
    public static <T> T component18(
            @NotNull
                    HList<
                            ?,
                            ? extends
                                    HList<
                                            ?,
                                            ? extends
                                                    HList<
                                                            ?,
                                                            ? extends
                                                                    HList<
                                                                            ?,
                                                                            ? extends
                                                                                    HList<
                                                                                            ?,
                                                                                            ? extends
                                                                                                    HList<
                                                                                                            ?,
                                                                                                            ? extends
                                                                                                                    HList<
                                                                                                                            ?,
                                                                                                                            ? extends
                                                                                                                                    HList<
                                                                                                                                            ?,
                                                                                                                                            ? extends
                                                                                                                                                    HList<
                                                                                                                                                            ?,
                                                                                                                                                            ? extends
                                                                                                                                                                    HList<
                                                                                                                                                                            ?,
                                                                                                                                                                            ? extends
                                                                                                                                                                                    HList<
                                                                                                                                                                                            ?,
                                                                                                                                                                                            ? extends
                                                                                                                                                                                                    HList<
                                                                                                                                                                                                            ?,
                                                                                                                                                                                                            ? extends
                                                                                                                                                                                                                    HList<
                                                                                                                                                                                                                            ?,
                                                                                                                                                                                                                            ? extends
                                                                                                                                                                                                                                    HList<
                                                                                                                                                                                                                                            ?,
                                                                                                                                                                                                                                            ? extends
                                                                                                                                                                                                                                                    HList<
                                                                                                                                                                                                                                                            ?,
                                                                                                                                                                                                                                                            ? extends
                                                                                                                                                                                                                                                                    HList<
                                                                                                                                                                                                                                                                            ?,
                                                                                                                                                                                                                                                                            ? extends
                                                                                                                                                                                                                                                                                    HList<
                                                                                                                                                                                                                                                                                            ?,
                                                                                                                                                                                                                                                                                            ? extends
                                                                                                                                                                                                                                                                                                    HList<
                                                                                                                                                                                                                                                                                                            T,
                                                                                                                                                                                                                                                                                                            ?>>>>>>>>>>>>>>>>>>
                    tuple) {
        Objects.requireNonNull(tuple);
        return tuple.elementAt(17);
    }
}

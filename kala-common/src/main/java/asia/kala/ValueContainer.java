package asia.kala;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * A container object which may or may not contain a value.
 *
 * @param <T> the type of value
 * @author Glavo
 */
public interface ValueContainer<T> extends Iterable<T>, Functor<T>, Foldable<T> {

    /**
     * Returns {@code true} if the container contain a value, otherwise return {@code false}.
     *
     * @return {@code true} if the container contain a value, otherwise {@code false}
     */
    boolean isDefined();

    /**
     * Returns {@code true} if the container is empty, otherwise return {@code false}.
     *
     * @return {@code true} if the container is empty, otherwise {@code false}
     */
    default boolean isEmpty() {
        return !isDefined();
    }

    /**
     * Returns the value of the container.
     *
     * @return the value of the container
     * @throws NoSuchElementException if the container is empty
     */
    T get();

    /**
     * Returns the value of the container if it is not empty, otherwise return the {@code defaultValue}.
     *
     * @param defaultValue the default value
     * @return the value of the container if the container {@link #isDefined()},
     * or the {@code defaultValue} if the container {@link #isEmpty()}
     */
    default T getOrElse(T defaultValue) {
        return isDefined() ? get() : defaultValue;
    }


    /**
     * Returns the value of the container if it is not empty, otherwise return the {@code null}.
     *
     * @return the value of the container if the container {@link #isDefined()},
     * or the {@code null} if the container {@link #isEmpty()}
     */
    @Nullable
    default T getOrNull() {
        return isDefined() ? get() : null;
    }

    /**
     * Returns the value of the container if it is not empty, otherwise throw the {@code exception}.
     *
     * @return the value of the container if the container {@link #isDefined()}
     * @throws E if no value is present
     */
    default <E extends Throwable> T getOrThrow(@NotNull E exception) throws E {
        Objects.requireNonNull(exception);
        if (isEmpty()) {
            throw exception;
        }
        return get();
    }

    /**
     * Returns the value of the container if it is not empty, otherwise throw {@code supplier.get()}.
     *
     * @return the value of the container if the container {@link #isDefined()}
     * @throws E if no value is present
     */
    default <E extends Throwable> T getOrThrow(@NotNull Supplier<? extends E> supplier) throws E {
        Objects.requireNonNull(supplier);
        if (isEmpty()) {
            throw supplier.get();
        }
        return get();
    }

    /**
     * Returns {@code Option.some(get())} if it is not empty, otherwise return the {@code Option.none()}.
     *
     * @return {@code Option.some(get())} if the container {@link #isDefined()},
     * or the {@code Option.none()} if the container {@link #isEmpty()}
     */
    default Option<T> getOption() {
        return isDefined() ? Option.some(get()) : Option.none();
    }

    /**
     * Returns {@code Option.some(get())} if it is not empty, otherwise return the {@code Option.none()}.
     *
     * @return {@code Option.some(get())} if the container {@link #isDefined()},
     * or the {@code Option.none()} if the container {@link #isEmpty()}
     */
    default Option<T> toOption() {
        return getOption();
    }

    //
    // -- Functor
    //

    @NotNull
    @Override
    <U> ValueContainer<U> map(@NotNull Function<? super T, ? extends U> mapper);

    //
    // -- Foldable
    //

    /**
     * {@inheritDoc}
     */
    @Override
    default <U> U foldLeft(U zero, @NotNull BiFunction<? super U, ? super T, ? extends U> op) {
        Objects.requireNonNull(op, "op");
        if (isEmpty()) {
            return zero;
        }
        return op.apply(zero, get());
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Contract(pure = true)
    default <U> U foldRight(U zero, @NotNull BiFunction<? super T, ? super U, ? extends U> op) {
        Objects.requireNonNull(op, "op");
        if (isEmpty()) {
            return zero;
        }
        return op.apply(get(), zero);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default T reduceLeft(@NotNull BiFunction<? super T, ? super T, ? extends T> op) {
        Objects.requireNonNull(op, "op");
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default T reduceRight(@NotNull BiFunction<? super T, ? super T, ? extends T> op) {
        Objects.requireNonNull(op, "op");
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    @Contract(pure = true)
    default Option<T> reduceLeftOption(@NotNull BiFunction<? super T, ? super T, ? extends T> op) {
        Objects.requireNonNull(op, "op");
        return getOption();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    @Contract(pure = true)
    default Option<T> reduceRightOption(@NotNull BiFunction<? super T, ? super T, ? extends T> op) {
        Objects.requireNonNull(op, "op");
        return getOption();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Contract(pure = true)
    default boolean forall(@NotNull Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate, "predicate");
        return isEmpty() || predicate.test(get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Contract(pure = true)
    default boolean exists(@NotNull Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate, "predicate");
        return isDefined() && predicate.test(get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default boolean contains(T v) {
        return isDefined() && Objects.equals(v, get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Contract(pure = true)
    default int count(@NotNull Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate, "predicate");
        return exists(predicate) ? 1 : 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    default Option<T> find(@NotNull Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate, "predicate");
        return isDefined() && predicate.test(get()) ? Option.some(get()) : Option.none();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default int size() {
        return isDefined() ? 1 : 0;
    }

    @NotNull
    default Stream<T> stream() {
        if (isEmpty()) {
            return Stream.empty();
        }
        return Stream.of(get());
    }

    //
    // -- Iterable
    //

    /**
     * {@inheritDoc}
     */
    @NotNull
    @Override
    default Iterator<T> iterator() {
        if (isDefined()) {
            return new ValueContainerIterator<>(get());
        }
        return new ValueContainerIterator<>(Option.NoneTag.INSTANCE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default Spliterator<T> spliterator() {
        if (isDefined()) {
            return new ValueContainerIterator<>(get());
        }
        return new ValueContainerIterator<>(Option.NoneTag.INSTANCE);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    default void forEach(@NotNull Consumer<? super T> action) {
        Objects.requireNonNull(action, "action");
        if (isDefined()) {
            action.accept(get());
        }
    }
}

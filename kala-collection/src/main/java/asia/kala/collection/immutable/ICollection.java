package asia.kala.collection.immutable;

import asia.kala.Tuple2;
import asia.kala.collection.CollectionFactory;
import asia.kala.collection.Transformable;
import asia.kala.collection.Traversable;
import asia.kala.collection.TraversableOnce;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;
import java.util.function.Predicate;

public interface ICollection<E> extends Traversable<E>, Transformable<E> {

    @Contract("_ -> param1")
    @SuppressWarnings("unchecked")
    static <E> ICollection<E> narrow(ICollection<? extends E> collection) {
        return (ICollection<E>) collection;
    }

    @Override
    default String className() {
        return "ICollection";
    }

    @NotNull
    @Override
    default <U> CollectionFactory<U, ?, ? extends ICollection<U>> iterableFactory() {
        return IArray.factory(); // TODO
    }

    @NotNull
    @Override
    default <U> ICollection<U> map(@NotNull Function<? super E, ? extends U> mapper) {
        return AbstractICollection.map(this, mapper, this.<U>iterableFactory());
    }

    @NotNull
    @Override
    default ICollection<E> filter(@NotNull Predicate<? super E> predicate) {
        return AbstractICollection.filter(this, predicate, iterableFactory());
    }

    @NotNull
    @Override
    default ICollection<E> filterNot(@NotNull Predicate<? super E> predicate) {
        return AbstractICollection.filterNot(this, predicate, iterableFactory());
    }

    @NotNull
    @Override
    default <U> ICollection<U> flatMap(@NotNull Function<? super E, ? extends TraversableOnce<? extends U>> mapper) {
        return AbstractICollection.flatMap(this, mapper, iterableFactory());
    }

    @NotNull
    @Override
    default Tuple2<? extends ICollection<E>, ? extends ICollection<E>> span(@NotNull Predicate<? super E> predicate) {
        return AbstractICollection.span(this, predicate, iterableFactory());
    }
}

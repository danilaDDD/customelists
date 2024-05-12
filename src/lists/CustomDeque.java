package lists;

public interface CustomDeque<E> extends Iterable<E> {
    void addFirst(E element);

    void addLast(E element);

    E getFirst();

    E getLast();

    E removeFirst();

    E removeLast();

    int size();
}

package lists;

public interface CustomList<E> extends Iterable<E> {
    void add(E element);

    E remove(int index);

    E remove(E element);

    E get(int index);

    void set(int index, E element);

    int size();
}

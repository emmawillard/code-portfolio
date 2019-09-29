/**Emma Willard
 * (willa115, 5040938)
 From Moodle for project 3*/
public interface List<T extends Comparable<T>> {

    boolean add(T element);
    boolean add(int index, T element);
    void clear();
    boolean contains(T element);
    T get(int index);
    int indexOf(T element);
    boolean isEmpty();
    int lastIndexOf(T element);
    T set(int index, T element);
    int size();
    void sort(boolean order);
    boolean remove(T element);
    T remove(int index);

}
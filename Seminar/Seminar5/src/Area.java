@FunctionalInterface
public interface Area<E> {
    double computeArea(E e);

    default String printClass(E e) {
        return e.getClass().toString();
    }
}

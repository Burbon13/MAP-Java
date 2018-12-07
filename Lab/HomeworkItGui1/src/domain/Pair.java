package domain;


import java.io.Serializable;

public class Pair<E,T> implements Serializable {
    private E first;
    private T second;

    Pair(E e, T t) {
        this.first = e;
        this.second = t;
    }

    public E getFirst() {
        return first;
    }

    public void setFirst(E first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) {
            return false;
        }
        Pair<?, ?> p = (Pair<?, ?>) o;
        return first.equals(((Pair<?, ?>) o).first) && second.equals(((Pair<?, ?>) o).second);
    }

    @Override
    public int hashCode() {
        return (first == null ? 0 : first.hashCode()) ^ (second == null ? 0 : second.hashCode());
    }
}

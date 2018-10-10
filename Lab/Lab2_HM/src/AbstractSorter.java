import java.util.Vector;

public abstract class AbstractSorter {
    protected Comparator comparator;

    AbstractSorter(Comparator comparator) {
        this.comparator = comparator;
    }

    public abstract void sort(Vector<Integer> vector);
}

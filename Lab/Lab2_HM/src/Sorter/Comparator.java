package Sorter;

public class Comparator {
    private boolean ascending;

    public Comparator(boolean ascending) {
        this.ascending = ascending;
    }

    //Returns true is the comparison is okay
    boolean compare(int n1, int n2) {
        return (n1 <= n2 && ascending) || (n1 >= n2 && !ascending);
    }

    boolean compareStrict(int n1, int n2) {
        return (n1 < n2 && ascending) || (n1 > n2 && !ascending);
    }
}

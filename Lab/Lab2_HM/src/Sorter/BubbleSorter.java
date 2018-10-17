package Sorter;

import Sorter.AbstractSorter;

import java.util.Collections;
import java.util.Vector;

public class BubbleSorter extends AbstractSorter {
    public BubbleSorter(Comparator comparator) {
        super(comparator);
    }

    @Override
    public void sort(Vector<Integer> vector) {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for(int index = 0; index < vector.size() - 1; index++)
                if(!comparator.compare(vector.elementAt(index),vector.elementAt(index+1))) {
                    Collections.swap(vector, index, index + 1);
                    isSorted = false;
                }
        }
    }
}

package Sorter;

import Sorter.AbstractSorter;

import java.util.Collections;
import java.util.Vector;

public class QuickSorter extends AbstractSorter {
    public QuickSorter(Comparator comparator) {
        super(comparator);
    }

    @Override
    public void sort(Vector<Integer> vector) {
        quickSort(vector,0,vector.size()-1);
    }

    private void quickSort(Vector<Integer> vector, int lowerIndex, int higherIndex) {
        int i = lowerIndex, j = higherIndex;
        int pivot = vector.elementAt(lowerIndex + (higherIndex - lowerIndex) / 2);
        while(i <= j) {
            while(comparator.compareStrict(vector.elementAt(i),pivot))
                i++;
            while (comparator.compareStrict(pivot,vector.elementAt(j)))
                j--;
            if(i <= j) {
                Collections.swap(vector,i,j);
                i++;
                j--;
            }
        }

        if(lowerIndex < j)
            quickSort(vector,lowerIndex,j);
        if(i < higherIndex)
            quickSort(vector,i,higherIndex);
    }
}

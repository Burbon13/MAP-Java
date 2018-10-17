package Task;

import Sorter.AbstractSorter;
import Sorter.BubbleSorter;
import Sorter.Comparator;
import Task.Task;

import java.util.Vector;

public class SortingTask extends Task {
    private Vector<Integer> vector;
    private AbstractSorter abstractSorter;

    public SortingTask() {
        super();
        this.vector = new Vector<>();
        abstractSorter = new BubbleSorter(new Comparator(true));
    }

    public SortingTask(Vector<Integer> vector, AbstractSorter abstractSorter) {
        super();
        this.vector = vector;
        this.abstractSorter = abstractSorter;
    }

    public SortingTask(String taskID, String description, Vector<Integer> vector, AbstractSorter abstractSorter) {
        super(taskID, description);
        this.vector = vector;
        this.abstractSorter = abstractSorter;
    }

    public void sort() {
        abstractSorter.sort(vector);
    }

    @Override
    public void execute() {
        System.out.println("Sorted vector: ");
        for(int nr: vector)
            System.out.println(nr);
    }
}

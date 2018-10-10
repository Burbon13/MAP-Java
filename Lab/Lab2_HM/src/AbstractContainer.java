/*TASK 5.3*/
import java.util.ArrayList;

public abstract class AbstractContainer implements Container {
    protected ArrayList<Task> c;

    public AbstractContainer() {
        c = new ArrayList<>();
    }

    @Override
    public void add(Task task) {
        c.add(task);
    }

    @Override
    public int size() {
        return c.size();
    }

    @Override
    public boolean empty() {
        return c.isEmpty();
    }
}

import java.util.ArrayList;

public class StackContainer implements Container {
    private ArrayList<Task> c; //Any problem if it is instantiated here?

    public StackContainer() {
        this.c = new ArrayList<>();
    }

    @Override
    public Task remove() {
        return c.remove(c.size()-1);
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

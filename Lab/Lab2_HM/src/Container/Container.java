package Container;

import Task.Task;

public interface Container {
    Task remove();
    void add(Task task);
    int size();
    boolean empty();
}

package Container;

import Task.Task;

public class StackContainer extends AbstractContainer {
    @Override
    public Task remove() {
        return c.remove(c.size()-1);
    }
}

package Runner;

import Factory.TaskContainerFactory;
import Runner.*;
import Container.*;
import Task.Task;
import Strategy.Strategy;

public class StrategyTaskRunner implements TaskRunner {
    private Container c;

    public StrategyTaskRunner(Strategy strategy) {
        this.c = TaskContainerFactory.getInstance().createContainer(strategy);
    }

    @Override
    public void executeOneTask() {
        c.remove().execute();
    }

    @Override
    public void executeAll() {
        while(!c.empty())
            c.remove().execute();
    }

    @Override
    public void addTask(Task task) {
        c.add(task);
    }

    @Override
    public boolean hasTask() {
        return !c.empty();
    }
}

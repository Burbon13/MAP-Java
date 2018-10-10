public class TaskContainerFactory implements Factory {
    //Make singleton pattern
    private static TaskContainerFactory instance = null;

    private TaskContainerFactory() {}

    public static TaskContainerFactory getInstance() {
        if(instance == null)
            instance = new TaskContainerFactory();
        return instance;
    }

    @Override
    public Container createContainer(Strategy strategy) {
        return strategy.equals(Strategy.LIFO) ? new StackContainer() : new QueueContainer();
    }
}

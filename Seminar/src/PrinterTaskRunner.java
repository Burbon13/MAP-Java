public class PrinterTaskRunner extends AbstractTaskRunner {
    PrinterTaskRunner(TaskRunner taskRunner) {
        super(taskRunner);
    }

    @Override
    public void executeOneTask() {
        super.executeOneTask();
        System.out.println("...");//In Delay class here you put Thread.sleep();
    }
}

public class Seminar1 {
    public static void main(String[] args) {
        TaskRunner taskRunner = new StrategyTaskRunner(args[0].equals("LIFO") ? Strategy.LIFO : Strategy.FIFO);
        //taskRunner.addTask(new MessageTask(,,,,,,));
        //taskRunner.addTask();
        //...
        taskRunner.executeOneTask();
        taskRunner.executeAll();
        ///------------------------------

        PrinterTaskRunner printerTaskRunner = new PrinterTaskRunner(taskRunner);
        printerTaskRunner.executeAll(); //how are the tasks executed?
    }
}

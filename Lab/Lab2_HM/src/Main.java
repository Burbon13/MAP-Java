import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        //Exercise 4
        Task[] tasks = new Task[5];
        tasks[0] = new MessageTask("1","Seminar","Hello world","Computer","Students","2018-10-02 13:00");
        tasks[1] = new MessageTask("2","Laborator","Beautiful world","Love","Ex's","2012-05-02 15:00");
        tasks[2] = new MessageTask("3","Curs","Cruel world","Teacher","Parents","2058-08-02 13:35");
        tasks[3] = new MessageTask("4","Examen","Great world","Colleague","Teachers","1018-03-02 20:00");
        tasks[4] = new MessageTask("5","Colocviu","Bye world","Deputy","Mayor","2618-10-02 13:00");

        for(Task t : tasks)
            t.execute();

        System.out.println("----- Exercise 10 -----");

        //Exercise 10
        TaskRunner strategyTaskRunner = new StrategyTaskRunner(args[0].equals("LIFO") ? Strategy.LIFO : Strategy.FIFO);
        for(int i = 0; i < 5; i++)
            strategyTaskRunner.addTask(tasks[i]);
        strategyTaskRunner.executeAll();

        //Exercise 13
        System.out.println("----- Exercise 13 ------");
        TaskRunner printerTaskRunner = new PrinterTaskRunner(new StrategyTaskRunner(args[0].equals("LIFO") ? Strategy.LIFO : Strategy.FIFO));
        for(int i = 0; i < 5; i++)
            printerTaskRunner.addTask(tasks[i]);
        printerTaskRunner.executeAll();

        //Exercise 14
        System.out.println("---- Exercise 14 -----");
        TaskRunner printerDelayTaskRunner = new PrinterTaskRunner(new DelayTaskRunner(new StrategyTaskRunner(args[0].equals("LIFO") ? Strategy.LIFO : Strategy.FIFO)));
        for(int i = 0; i < 5; i++)
            printerDelayTaskRunner.addTask(tasks[i]);
        printerDelayTaskRunner.executeAll();


        //Test SortingTask
        System.out.println("---- Test SortingTask ----");
        Vector<Integer> vector = new Vector<>();
        vector.add(3);
        vector.add(10);
        vector.add(-4);
        vector.add(20);
        vector.add(30);
        vector.add(3);
        SortingTask sortingTask = new SortingTask(vector,new BubbleSorter(new Comparator(true)));
        SortingTask sortingTas2 = new SortingTask(vector,new QuickSorter(new Comparator(true)));

        System.out.println("Bubble sort");
        sortingTask.sort();
        sortingTask.execute();

        System.out.println("Quick sort");
        sortingTas2.sort();
        sortingTas2.execute();

        //TODO:TASK 10

    }
}

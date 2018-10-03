public class Seminar1 {
    public static void main(String [] args) {
        //create Task from parameters
        Task specialTask = new MessageTask(args[0],args[1],args[2],args[3],args[4],args[5]);
        specialTask.execute();

        System.out.println("Hello to your first Java program!");

        Task[] tasks = new Task[5];
        tasks[0] = new MessageTask("1","Seminar","Hello world","Computer","Students","2018-10-02 13:00");
        tasks[1] = new MessageTask("2","Laborator","Beautiful world","Love","Ex's","2012-05-02 15:00");
        tasks[2] = new MessageTask("3","Curs","Cruel world","Teacher","Parents","2058-08-02 13:35");
        tasks[3] = new MessageTask("4","Examen","Great world","Colleague","Teachers","1018-03-02 20:00");
        tasks[4] = new MessageTask("5","Colocviu","Bye world","Deputy","Mayor","2618-10-02 13:00");

//        for(Task i: tasks)
//            i.execute();

    }

}

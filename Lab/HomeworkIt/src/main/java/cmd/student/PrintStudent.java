package cmd.student;

import cmd.AbstractCommand;
import service.Service;

public class PrintStudent extends AbstractCommand {
    private int studentID;

    public PrintStudent(Service service, int studentID) {
        super(service);
        this.studentID = studentID;
    }

    @Override
    public void execute() {
        try {
            System.out.println(service.getStudent(studentID));
        } catch (SecurityException ex) {
            System.out.println(ex.getMessage());
        }
    }

//    @Override
//    public void undo() {
//        System.out.println("Cannot undo print command!");
//    }
}

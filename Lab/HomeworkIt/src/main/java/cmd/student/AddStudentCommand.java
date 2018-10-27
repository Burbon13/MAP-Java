package cmd.student;

import cmd.AbstractCommand;
import service.Service;
import service.exception.ServiceException;

public class AddStudentCommand extends AbstractCommand {
    private int studentID, group;
    private String name, email, labTeacher;

    public AddStudentCommand(Service service, int studentID, String name, int group, String email, String labTeacher) {
        super(service);
        this.studentID = studentID;
        this.group = group;
        this.name = name;
        this.email = email;
        this.labTeacher = labTeacher;
    }

    @Override
    public void execute() {
        try {
            service.addStudent(studentID, name, group, email, labTeacher);
            System.out.println("Student added!");
        } catch (ServiceException ex) {
            System.out.println(ex.getMessage());
        }
    }

//    @Override
//    public void undo() {
//        try {
//            service.deleteStudent(studentID);
//        } catch (ServiceException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
}

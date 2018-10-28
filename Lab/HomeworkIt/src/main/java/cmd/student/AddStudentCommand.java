package cmd.student;

import cmd.AbstractCommand;
import cmd.CommandException;
import service.Service;
import service.exception.ServiceException;
import validator.exception.ValidationException;

public class AddStudentCommand extends AbstractCommand {
    private int studentID, group;
    private String name, email, labTeacher;

    public AddStudentCommand(Service service, String[] sep_params) {
        super(service);
        if(sep_params.length != 6)
            throw new CommandException("Invalid parameters!");

        try {
            this.studentID = Integer.parseInt(sep_params[1]);
            this.group = Integer.parseInt(sep_params[3]);
        } catch (NumberFormatException ex) {
            throw new CommandException("Invalid parameters! " + ex.getMessage());
        }
        this.name = sep_params[2];
        this.email = sep_params[4];
        this.labTeacher = sep_params[5];
    }

    @Override
    public void execute() {
        try {
            service.addStudent(studentID, name, group, email, labTeacher);
            System.out.println("Student added!");
        } catch (ServiceException | ValidationException ex) {
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

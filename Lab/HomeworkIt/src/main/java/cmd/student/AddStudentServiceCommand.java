package cmd.student;

import cmd.AbstractServiceCommand;
import cmd.CommandException;
import service.Service;
import service.exception.ServiceException;
import validator.exception.ValidationException;

public class AddStudentServiceCommand extends AbstractServiceCommand {
    private int studentID, group;
    private String name, email, labTeacher;

    public AddStudentServiceCommand(Service service, String[] sep_params) {
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

    /**
     * Adds student into memory
     */
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

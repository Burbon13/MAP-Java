package cmd.student;

import cmd.AbstractServiceCommand;
import cmd.CommandException;
import service.Service;

public class PrintStudentCommand extends AbstractServiceCommand {
    private int studentID;

    public PrintStudentCommand(Service service, String[] sep_params) {
        super(service);
        if(sep_params.length != 2)
            throw new CommandException("Invalid parameters!");
        try {
            this.studentID = Integer.parseInt(sep_params[1]);
        } catch (NumberFormatException ex) {
            throw new CommandException("Invalid parameters! " + ex.getMessage());
        }
    }

    /**
     * Prints the student from memory
     */
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

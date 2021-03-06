package cmd.student;

import cmd.AbstractServiceCommand;
import cmd.CommandException;
import service.Service;
import service.exception.ServiceException;

public class DeleteStudentServiceCommand extends AbstractServiceCommand {
    private int studentID;
//    private Student studentDeleted;

    public DeleteStudentServiceCommand(Service service, String[] sep_params) {
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
     * Deletes student from memory
     */
    @Override
    public void execute() {
        try {
            service.deleteStudent(studentID);
            System.out.println("Student deleted!");
        } catch (ServiceException ex) {
            System.out.println(ex.getMessage());
        }
    }

//    @Override
//    public void undo() {
//        try {
//            service.addStudent(studentDeleted.getID(), studentDeleted.getName(), studentDeleted.getGroup(),
//                    studentDeleted.getEmail(), studentDeleted.getLabTeacher());
//        } catch (ServiceException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
}

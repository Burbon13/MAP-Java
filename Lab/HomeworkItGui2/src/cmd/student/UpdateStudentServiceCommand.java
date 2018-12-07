package cmd.student;

import cmd.AbstractServiceCommand;
import cmd.CommandException;
import service.Service;
import service.exception.ServiceException;
import validator.exception.ValidationException;

public class UpdateStudentServiceCommand extends AbstractServiceCommand {
    private int studentID, newGroup;
    private String newName, newLabTeacher, newEmail;
//    private Student oldStudent;

    public UpdateStudentServiceCommand(Service service, String[] sep_params) {
        super(service);
        if(sep_params.length != 6)
            throw new CommandException("Invalid parameters!");

        try {
            this.studentID = Integer.parseInt(sep_params[1]);
            this.newGroup = Integer.parseInt(sep_params[3]);
        } catch (NumberFormatException ex) {
            throw new CommandException("Invalid parameters! " + ex.getMessage());
        }
        this.newName = sep_params[2];
        this.newEmail = sep_params[4];
        this.newLabTeacher = sep_params[5];
    }

    /**
     * Updates the student from memory
     */
    @Override
    public void execute() {
        try {
//            oldStudent = service.getStudent(studentID);
            service.updateStudent(studentID, newName, newGroup, newEmail, newLabTeacher);
            System.out.println("Student updated!");
        }catch (ServiceException | ValidationException ex) {
            System.out.println(ex.getMessage());
        }
    }

//    @Override
//    public void undo() {
//        try {
//            service.updateStudent(studentID, oldStudent.getName(), oldStudent.getGroup(), oldStudent.getEmail(),
//                    oldStudent.getLabTeacher());
//        } catch (ServiceException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
}

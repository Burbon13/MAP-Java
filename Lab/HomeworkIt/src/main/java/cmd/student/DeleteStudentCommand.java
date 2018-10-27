package cmd.student;

import cmd.AbstractCommand;
import domain.Student;
import service.Service;
import service.exception.ServiceException;

public class DeleteStudentCommand extends AbstractCommand {
    private int studentID;
//    private Student studentDeleted;

    public DeleteStudentCommand(Service service, int studentID) {
        super(service);
        this.studentID = studentID;
    }

    @Override
    public void execute() {
        try {
//            studentDeleted = service.getStudent(studentID);
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

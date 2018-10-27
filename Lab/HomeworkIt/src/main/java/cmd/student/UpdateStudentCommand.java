package cmd.student;

import cmd.AbstractCommand;
import domain.Student;
import service.Service;
import service.exception.ServiceException;

public class UpdateStudentCommand extends AbstractCommand {
    private int studentID, newGroup;
    private String newName, newLabTeacher, newEmail;
//    private Student oldStudent;

    public UpdateStudentCommand(Service service, int studentID, int newGroup, String newName, String newLabTeacher, String newEmail) {
        super(service);
        this.studentID = studentID;
        this.newGroup = newGroup;
        this.newName = newName;
        this.newLabTeacher = newLabTeacher;
        this.newEmail = newEmail;
    }

    @Override
    public void execute() {
        try {
//            oldStudent = service.getStudent(studentID);
            service.updateStudent(studentID, newName, newGroup, newEmail, newLabTeacher);
            System.out.println("Student updated!");
        }catch (ServiceException ex) {
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

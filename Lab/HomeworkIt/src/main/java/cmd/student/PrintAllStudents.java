package cmd.student;

import cmd.AbstractCommand;
import domain.Student;
import service.Service;

public class PrintAllStudents extends AbstractCommand {
    public PrintAllStudents(Service service, String[] sep_params) {
        super(service);
    }

    @Override
    public void execute() {
        if(service.getAllStudents().size() == 0) {
            System.out.println("No students!");
            return;
        }
        for(Student student: service.getAllStudents())
            System.out.println(student);
    }
}

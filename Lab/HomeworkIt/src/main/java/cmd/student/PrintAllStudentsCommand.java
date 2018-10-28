package cmd.student;

import cmd.AbstractServiceCommand;
import domain.Student;
import service.Service;

public class PrintAllStudentsCommand extends AbstractServiceCommand {
    public PrintAllStudentsCommand(Service service, String[] sep_params) {
        super(service);
    }

    /**
     * Prints all students from memory
     */
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

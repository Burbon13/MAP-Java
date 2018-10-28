import cmd.factory.CommandFactory;
import cmd.factory.CommandFactoryException;
import cmd.student.AddStudentCommand;
import domain.Student;
import repository.HomeworkRepository;
import repository.StudentRepository;
import service.Service;
import validator.HomeworkValidator;
import validator.StudentValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class Application {
    private static BufferedReader reader;
    private static Service service;

    static {
        HomeworkRepository homeworkRepository = new HomeworkRepository(new HomeworkValidator());
        StudentRepository studentRepository  = new StudentRepository(new StudentValidator());
        LocalDate semesterStart = LocalDate.of(2018, 10, 1);
        service = new Service(homeworkRepository, studentRepository, semesterStart);
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) {
        while(true) {
            try {
                String command = reader.readLine();
                CommandFactory.getCommand(command, service).execute();
            } catch (IOException | CommandFactoryException ex) {
                System.out.println(ex.getMessage());
            }
       }
    }
}

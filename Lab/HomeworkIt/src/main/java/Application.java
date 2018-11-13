import cmd.factory.CommandFactory;
import cmd.factory.CommandFactoryException;
import repository.exception.FileRepositoryException;
import repository.in_file.HomeworkFileRepository;
import repository.in_file.StudentFileRepository;
import repository.in_memory.HomeworkRepository;
import repository.in_memory.MarkRepository;
import repository.in_memory.StudentRepository;
import service.Service;
import validator.HomeworkValidator;
import validator.MarkValidator;
import validator.StudentValidator;

import java.io.*;
import java.time.LocalDate;

public class Application {
    private static BufferedReader reader;
    private static Service service;

    static {
        try {
            HomeworkFileRepository homeworkRepository = new HomeworkFileRepository(new HomeworkValidator(), "homework.txt");
            StudentFileRepository studentRepository = new StudentFileRepository(new StudentValidator(), "students.txt");
            MarkRepository markRepository = new MarkRepository(new MarkValidator());
            LocalDate semesterStart = LocalDate.of(2018, 10, 1);
//            service = new Service(homeworkRepository, studentRepository, semesterStart);
            service = new Service(homeworkRepository, studentRepository, semesterStart, markRepository);
            reader = new BufferedReader(new InputStreamReader(System.in));
        }catch (FileRepositoryException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
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

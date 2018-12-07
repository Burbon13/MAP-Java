import cmd.factory.CommandFactory;
import cmd.factory.CommandFactoryException;
import javafx.application.Application;
import javafx.stage.Stage;
import repository.exception.FileRepositoryException;
import repository.in_file.HomeworkFileRepository;
import repository.in_file.MarkFileRepository;
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

public class Main extends Application {
    private  BufferedReader reader;
    private  Service service;

    static {

    }

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            HomeworkFileRepository homeworkRepository = new HomeworkFileRepository(new HomeworkValidator(), "homework.txt");
            StudentFileRepository studentRepository = new StudentFileRepository(new StudentValidator(), "students.txt");
//            MarkRepository markRepository = new MarkRepository(new MarkValidator());
            MarkFileRepository markFileRepository = new MarkFileRepository(new MarkValidator(), "catalog.txt");
            LocalDate semesterStart = LocalDate.of(2018, 10, 1);
//            service = new Service(homeworkRepository, studentRepository, semesterStart);
            service = new Service(homeworkRepository, studentRepository, semesterStart, markFileRepository);
            reader = new BufferedReader(new InputStreamReader(System.in));
        }catch (FileRepositoryException ex) {
            System.out.println(ex.getMessage());
        }

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


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import repository.exception.FileRepositoryException;
import repository.in_file.HomeworkFileRepository;
import repository.in_file.MarkFileRepository;
import repository.in_file.StudentFileRepository;
import service.Service;
import validator.HomeworkValidator;
import validator.MarkValidator;
import validator.StudentValidator;
import view.StudentController;
import view.StudentView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            HomeworkFileRepository homeworkRepository = new HomeworkFileRepository(new HomeworkValidator(), "homework.txt");
            StudentFileRepository studentRepository = new StudentFileRepository(new StudentValidator(), "students.txt");
//            MarkRepository markRepository = new MarkRepository(new MarkValidator());
            MarkFileRepository markFileRepository = new MarkFileRepository(new MarkValidator(), "catalog.txt");
            LocalDate semesterStart = LocalDate.of(2018, 10, 1);
//            service = new Service(homeworkRepository, studentRepository, semesterStart);
            Service service = new Service(homeworkRepository, studentRepository, semesterStart, markFileRepository);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            StudentController studentController = new StudentController(service);
            StudentView viewRoot = new StudentView(studentController);

            primaryStage.setTitle("Get dem students");
            primaryStage.setScene(new Scene(viewRoot.getView(), 600, 400));
            primaryStage.show();
        }catch (FileRepositoryException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

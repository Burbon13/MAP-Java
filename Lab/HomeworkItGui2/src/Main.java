import gui.ControllerGUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import repository.in_file.HomeworkFileRepository;
import repository.in_file.MarkFileRepository;
import repository.in_file.StudentFileRepository;
import service.Service;
import validator.HomeworkValidator;
import validator.MarkValidator;
import validator.StudentValidator;

import java.time.LocalDate;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "gui/main.fxml"
                )
        );

        HomeworkFileRepository homeworkRepository = new HomeworkFileRepository(new HomeworkValidator(), "homework.txt");
        StudentFileRepository studentRepository = new StudentFileRepository(new StudentValidator(), "students.txt");
//            MarkRepository markRepository = new MarkRepository(new MarkValidator());
        MarkFileRepository markFileRepository = new MarkFileRepository(new MarkValidator(), "catalog.txt");
        LocalDate semesterStart = LocalDate.of(2018, 10, 1);
//            service = new Service(homeworkRepository, studentRepository, semesterStart);
        Service service = new Service(homeworkRepository, studentRepository, semesterStart, markFileRepository);

        Parent root = loader.load();

        loader.<ControllerGUI>getController().initService(service);
//
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

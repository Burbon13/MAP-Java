import domain.Homework;
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

//        homeworkRepository.save(new Homework(1, "Prima tema", 1, 3));
//        homeworkRepository.save(new Homework(2, "Prima tema", 2, 3));
//        homeworkRepository.save(new Homework(3, "Prima tema", 3, 4));
//        homeworkRepository.save(new Homework(4, "Prima tema", 4, 5));
//        homeworkRepository.save(new Homework(5, "Prima tema", 5, 6));
//        homeworkRepository.save(new Homework(6, "Prima tema", 6, 8));
//        homeworkRepository.save(new Homework(7, "Prima tema", 7, 8));
//        homeworkRepository.save(new Homework(8, "Prima tema", 8, 9));
//        homeworkRepository.save(new Homework(9, "Prima tema", 9, 10));
//        homeworkRepository.save(new Homework(10, "Prima tema", 10, 12));
//        homeworkRepository.save(new Homework(11, "Prima tema", 11, 12));
//        homeworkRepository.save(new Homework(12, "Prima tema", 12, 14));
//        homeworkRepository.save(new Homework(13, "Prima tema", 13, 14));

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

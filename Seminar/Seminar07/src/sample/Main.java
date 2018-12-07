package sample;

import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import repository.MessageTaskFileRepository;
import repository.MessageTaskRepository;
import repository.XMLRepository;
import services.Service;
import validation.TaskValidation;
import view.Controller;
import view.View;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        MessageTaskFileRepository xmlRepository = new MessageTaskFileRepository(new TaskValidation(), "tasks.xml");
        MessageTaskRepository messageTaskRepository = new MessageTaskRepository(new TaskValidation());
        Service service = new Service(messageTaskRepository);

        Controller controller = new Controller(service);
        View viewRoot = new View(controller);


        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(viewRoot.getView(), 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

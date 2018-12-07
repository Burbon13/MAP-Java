package view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import sample.Controller;

public class StudentView {
    private TextField idStudent;
    private TextField nameStudent;
    private TextField groupStudent;
    private TextField emailStudent;
    private TextField labTeacherStudent;

    private Controller controller;

    public StudentView(Controller controller) {
        this.controller = controller;
    }

    public BorderPane getView() {
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(createCenter());
        return borderPane;
    }

    private GridPane createCenter() {
        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Id"),0,0);
        gridPane.add(idStudent = new TextField(), 1, 0);

        return gridPane;
    }
}

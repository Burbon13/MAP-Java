package view;

import domain.Student;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;


public class StudentView {
    private TableView<Student> tableView;
    private TextField idStudent;
    private TextField nameStudent;
    private TextField groupStudent;
    private TextField emailStudent;
    private TextField labTeacherStudent;
    private StudentController studentController;

    public StudentView(StudentController studentController) {
        tableView = new TableView<>();
        this.studentController = studentController;
    }

    public BorderPane getView() {
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(createTable());
        borderPane.setRight(createStudent());
        return borderPane;
    }

    private void initTableView() {
        tableView.setItems(studentController.getList());
        TableColumn<Student, Integer> idColumn = new TableColumn<>("Id");
        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Student, Integer> groupColumn = new TableColumn<>("Group");
        TableColumn<Student, Integer> emailColumn = new TableColumn<>("Email");
        TableColumn<Student, Integer> teacherColumn = new TableColumn<>("Teacher");
        tableView.getColumns().addAll(idColumn, nameColumn, groupColumn, emailColumn, teacherColumn);
        idColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        groupColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("group"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("email"));
        teacherColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("labTeacher"));
        tableView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> showDetails(newValue)));
    }

    private void showDetails(Student student) {
        if(student != null) {
            idStudent.setText(student.getID().toString());
            nameStudent.setText(student.getName());
            groupStudent.setText(String.valueOf(student.getGroup()));
            emailStudent.setText(student.getEmail());
            labTeacherStudent.setText(student.getLabTeacher());
        }
    }

    private StackPane createTable() {
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(tableView);
        initTableView();
        return stackPane;
    }

    private GridPane createStudent() {
        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Id"), 0, 0);
        gridPane.add(idStudent = new TextField(), 1, 0);
        gridPane.add(new Label("Name"), 0, 1);
        gridPane.add(nameStudent = new TextField(), 1, 1);
        gridPane.add(new Label("Group"), 0, 2);
        gridPane.add(groupStudent = new TextField(), 1, 2);
        gridPane.add(new Label("Email"), 0, 3);
        gridPane.add(emailStudent = new TextField(), 1, 3);
        gridPane.add(new Label("Teacher"), 0, 4);
        gridPane.add(labTeacherStudent = new TextField(), 1, 4);

        HBox buttonsBox = new HBox();
        Button add = new Button("Add");
        add.setOnAction(event ->
                this.addHandler()
        );
        buttonsBox.getChildren().add(add);

        Button update = new Button("Update");
        update.setOnAction(event -> {
            this.updateHandler();
        });
        buttonsBox.getChildren().add(update);

        Button delete = new Button("Delete");
        delete.setOnAction(event -> {
            this.deleteHandler();
        });
        buttonsBox.getChildren().add(delete);
        gridPane.add(buttonsBox, 0, 5, 2, 1);
        return gridPane;
    }

    private void addHandler(){ }
    private void deleteHandler(){}
    private void updateHandler(){}
}

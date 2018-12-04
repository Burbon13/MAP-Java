package gui;

import domain.Mark;
import domain.Student;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.Service;
import service.exception.ServiceException;
import view.*;
import view.Event;


import java.awt.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ControllerGUI implements Observer<AppEvent> {
    private Service service;

    private ObservableList<Student> observableListStudents;
    private ObservableList<Mark> observableListMarks;

    //Students TAB
    @FXML
    private TableView tvStudentsS;
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn groupColumn;
    @FXML
    private TableColumn emailColumn;
    @FXML
    private TableColumn labTeacherColumn;
    @FXML
    private TextField tfIdS;
    @FXML
    private TextField tfNameS;
    @FXML
    private TextField tfGroupS;
    @FXML
    private TextField tfEmailS;
    @FXML
    private TextField tfTeacherS;
    @FXML
    private Button buAddS;
    @FXML
    private Button buUpdateS;
    @FXML
    private Button buRemoveS;
    @FXML
    private Button buClearS;




    public void initialize() {
        setStudentTab();
    }

    public void initService(Service service) {
        this.service = service;
        this.service.addObserver(this);
        this.observableListStudents = FXCollections.observableList(StreamSupport.stream(service.getAllStudents().spliterator(), false).collect(Collectors.toList()));
        this.observableListMarks = FXCollections.observableList(StreamSupport.stream(service.getAllMarks().spliterator(), false).collect(Collectors.toList()));
        printStudents();
    }

    public ObservableList<Student> getObservableListStudents() {
        return observableListStudents;
    }

    public ObservableList<Mark> getObservableListMarks() {
        return observableListMarks;
    }

    private void setStudentTab() {
    }

    @FXML
    void addStudentListener() {
        try {
            service.addStudent(Integer.parseInt(tfIdS.getText()), tfNameS.getText(), Integer.parseInt(tfGroupS.getText()),
                    tfEmailS.getText(), tfTeacherS.getText());
        } catch (ServiceException | NumberFormatException e) {
            setAlarm(e.getMessage());
        }
    }

    @FXML
    void updateStudentListener() {
        try {
            service.updateStudent(Integer.parseInt(tfIdS.getText()), tfNameS.getText(), Integer.parseInt(tfGroupS.getText()),
                    tfEmailS.getText(), tfTeacherS.getText());
        } catch (ServiceException | NumberFormatException e) {
            setAlarm(e.getMessage());
        }
    }

    @FXML
    void deleteStudentListener() {
        try {
            service.deleteStudent(Integer.parseInt(tfIdS.getText()));
            clearAll();
        }catch (ServiceException | NumberFormatException e) {
            setAlarm(e.getMessage());
        }
    }

    private void printStudents() {
        tvStudentsS.setItems(observableListStudents);
        idColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        groupColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("group"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        labTeacherColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("labTeacher"));
        tvStudentsS.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> showDetailsStudent((Student)newValue)));
    }

    private void showDetailsStudent(Student student) {
        tfIdS.setText(String.valueOf(student.getID()));
        tfIdS.setEditable(false);
        tfNameS.setText(student.getName());
        tfGroupS.setText(String.valueOf(student.getGroup()));
        tfEmailS.setText(student.getEmail());
        tfTeacherS.setText(student.getLabTeacher());
    }

    @FXML
    public void clearAll() {
        tfIdS.clear();
        tfIdS.setEditable(true);
        tfNameS.clear();
        tfGroupS.clear();
        tfEmailS.clear();
        tfTeacherS.clear();
    }

    @Override
    public void update(AppEvent appEvent) {
        if(appEvent.getEventClass() == EventClass.STUDENTS) {
            if(appEvent.getType() == ChangeEventType.ADD)
                observableListStudents.add((Student)appEvent.getNewData());
            else if(appEvent.getType() == ChangeEventType.UPDATE) {
                int pos = observableListStudents.indexOf((Student)appEvent.getOldData());
                observableListStudents.remove((Student)appEvent.getOldData());
                observableListStudents.add(pos, (Student)appEvent.getNewData());
            } else if(appEvent.getType() == ChangeEventType.DELETE) {
                observableListStudents.remove((Student)appEvent.getNewData());
            }
            tvStudentsS.refresh();
        } else if(appEvent.getEventClass() == EventClass.MARKS) {

        }
    }

    private void setAlarm(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Student error");
        alert.setContentText(msg);
        alert.show();
    }
}

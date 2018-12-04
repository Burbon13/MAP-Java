package gui;

import domain.Homework;
import domain.Mark;
import domain.Student;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.Service;
import service.exception.ServiceException;
import view.*;
import view.Event;


import java.awt.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ControllerGUI implements Observer<AppEvent> {
    private Service service;

    private ObservableList<Student> observableListStudents;
    private ObservableList<Mark> observableListMarks;
    private ObservableList<Homework> observableListHomework;

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

    //Marks tab
    FilteredList<Student> filteredStudents;

    @FXML
    private TableView tvMarks;
    @FXML
    private ComboBox cbMark;
    @FXML
    private TextField tfNameMark;
    @FXML
    private TextField tfMarkValue;
    @FXML
    private TextArea taFeedback;
    @FXML
    private Label currentDate;
    @FXML
    private CheckBox isMotivated;
    @FXML
    private TableView tvStudentsMarks;
    @FXML
    private TableColumn studentMarkIdColumn;
    @FXML
    private TableColumn studentMarkNameColumn;
    @FXML
    private TableColumn studentMarkGroupColumn;

    Student selectedStudentForMark;


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

    public void initialize() {

    }

    public void initService(Service service) {
        this.service = service;
        this.service.addObserver(this);
        this.observableListStudents = FXCollections.observableList(StreamSupport.stream(service.getAllStudents().spliterator(), false).collect(Collectors.toList()));
        this.observableListMarks = FXCollections.observableList(StreamSupport.stream(service.getAllMarks().spliterator(), false).collect(Collectors.toList()));
        this.observableListHomework = FXCollections.observableList(StreamSupport.stream(service.getAllHomework().spliterator(), false).collect(Collectors.toList()));
        printStudents();
        initMarksTab();
    }

    //Student stuff
    private void printStudents() {
        tvStudentsS.setItems(observableListStudents);
        idColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        groupColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("group"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        labTeacherColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("labTeacher"));
        tvStudentsS.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> showDetailsStudent((Student)newValue)));
    }

    public ObservableList<Student> getObservableListStudents() {
        return observableListStudents;
    }

    public ObservableList<Mark> getObservableListMarks() {
        return observableListMarks;
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


    //Mark stuff
    private void initMarksTab() {
        initComboBoxHomework();
        setColumnsStudentTable();
        initNameTextField();
        cbMark.valueProperty().addListener(((observable, oldValue, newValue) -> {
            setTextAreaDateBased((Homework)newValue);
        }));
    }

    private void setTextAreaDateBased(Homework homework) {
        int diff = (int) (ChronoUnit.DAYS.between(service.getStartingDate(), LocalDate.now())/7 + 1) - homework.getDeadline();
        if((diff == 1 || diff == 2) && !isMotivated.isSelected()) {
            taFeedback.setText("NOTA A FOST DIMINUATA CU " + String.valueOf(diff*2.5) + " PUNCTE DATORITA INTARZIERILOR");
        } else if(diff > 2 && !isMotivated.isSelected()) {
            taFeedback.setText("NOTA 1, PREA TARZIU");
        } else
            taFeedback.clear();
    }

    private void setColumnsStudentTable() {
        studentMarkIdColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("ID"));
        studentMarkNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        studentMarkGroupColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("group"));
        tvStudentsMarks.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> loadStudentForMark((Student)newValue)));
    }

    private void loadStudentForMark(Student student) {
        if(student != null) {
            selectedStudentForMark = student;
            tfNameMark.setEditable(false);
            tfNameMark.setText(student.getName());

        }
    }

    @FXML
    private void clearStudentMark() {
        selectedStudentForMark = null;
        tfNameMark.setEditable(true);
        tfNameMark.clear();
        taFeedback.clear();
        autoHomeworkSelect();
    }

    private void initNameTextField() {
        tfNameMark.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.equals("")) {
                filteredStudents = new FilteredList<Student>(observableListStudents, student -> false);
                tvStudentsMarks.setItems(filteredStudents);
            } else {
                filteredStudents = new FilteredList<Student>(observableListStudents, student -> {
                   return student.getName().contains(newValue);
                });
                tvStudentsMarks.setItems(filteredStudents);
            }
        });
    }

    private void initComboBoxHomework() {
        cbMark.setItems(observableListHomework);
        autoHomeworkSelect();
    }

    private void autoHomeworkSelect() {
        LocalDate now = LocalDate.now();
        observableListHomework.forEach(homework -> {
            if(((int) ChronoUnit.DAYS.between(service.getStartingDate(), now)/7 + 1) - homework.getDeadline() == 0)
                cbMark.getSelectionModel().select(homework);
        });
    }

    private void setAlarm(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Student error");
        alert.setContentText(msg);
        alert.show();
    }
}

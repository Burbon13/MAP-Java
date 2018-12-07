package gui;

import domain.Homework;
import domain.Mark;
import domain.Pair;
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
import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
    double penalizare = 0;

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
    @FXML
    private TableColumn studentNameMarkColumn;
    @FXML
    private TableColumn homeworkNrColumn;
    @FXML
    private TableColumn valueColumn;
    @FXML
    private TableColumn feedbackColumn;

    Student selectedStudentForMark;

    // Filter
    FilteredList<Mark> filteredMarks;

    @FXML
    private ComboBox cbFilterHomework;
    @FXML
    private TextField tfGroupFilter;
    @FXML
    private TextField tfNameFilter;
    @FXML
    private DatePicker startingDate;
    @FXML
    private DatePicker endingDate;
    @FXML
    private TableView tvFilter;
    @FXML
    private TableColumn columnNameFilter;
    @FXML
    private TableColumn columnMarkFilter;
    @FXML
    private TableColumn columnHomeworkFilter;
    @FXML
    private TableColumn columnGroupFilter;
    @FXML
    private TableColumn columnDateFilter;

    @Override
    public void update(AppEvent appEvent) {
        if(appEvent.getEventClass() == EventClass.STUDENTS) {
            if(appEvent.getType() == ChangeEventType.ADD)
                observableListStudents.add((Student)appEvent.getNewData());
            else if(appEvent.getType() == ChangeEventType.UPDATE) {
                int pos = observableListStudents.indexOf((Student)appEvent.getOldData());
                observableListStudents.remove((Student)appEvent.getOldData());
                observableListStudents.add(pos, (Student)appEvent.getNewData());
                service.updateMarksForStudents((Student)appEvent.getNewData());
                doMagic();
            } else if(appEvent.getType() == ChangeEventType.DELETE) {
                observableListStudents.remove((Student)appEvent.getNewData());
            }
            magicRefresh();
        } else if(appEvent.getEventClass() == EventClass.MARKS) {
            if (appEvent.getType() == ChangeEventType.ADD)
                observableListMarks.add((Mark)appEvent.getNewData());
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
        initFilterTab();
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

    private void doMagic() {
        this.observableListMarks = FXCollections.observableList(StreamSupport.stream(service.getAllMarks().spliterator(), false).collect(Collectors.toList()));
        tvMarks.setItems(observableListMarks);
        filteredMarks = new FilteredList<>(observableListMarks, s-> true);
        tvFilter.setItems(filteredMarks);
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
        isMotivated.selectedProperty().addListener(list -> setTextAreaDateBased((Homework)cbMark.getValue()));
        setTablewViewMarks();
    }

    private void setTablewViewMarks() {
        tvMarks.setItems(observableListMarks);
        studentNameMarkColumn.setCellValueFactory(new PropertyValueFactory<Mark, String>("studentName"));
        homeworkNrColumn.setCellValueFactory(new PropertyValueFactory<Mark, Integer>("homeworkNumber"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<Mark, String>("value"));
        feedbackColumn.setCellValueFactory(new PropertyValueFactory<Mark, String>("feedback"));
    }

    private void setTextAreaDateBased(Homework homework) {
        int diff = (int) (ChronoUnit.DAYS.between(service.getStartingDate(), LocalDate.now())/7 + 1) - homework.getDeadline();
        if((diff == 1 || diff == 2) && !isMotivated.isSelected()) {
            taFeedback.setText("NOTA A FOST DIMINUATA CU " + String.valueOf(diff*2.5) + " PUNCTE DATORITA INTARZIERILOR");
            penalizare = diff*2.5;
        } else if(diff > 2 && !isMotivated.isSelected()) {
            taFeedback.setText("NOTA 1, PREA TARZIU");
            penalizare = -1;
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
        tfMarkValue.clear();
        penalizare = 0;
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

    @FXML
    private void addMarkEvent() {
        int idStud = 0, idHom = 0;
        String feedback;
        boolean pass;
        double value = 1f;

        String error = "";
        if (cbMark.getValue() == null)
            error += "No homework selected\n";
        else
            idHom = ((Homework) cbMark.getValue()).getID();
        if (selectedStudentForMark == null)
            error += "No student selected\n";
        else
            idStud = selectedStudentForMark.getID();
        try {
            value = Double.parseDouble(tfMarkValue.getText());
            if (value < 1 || value > 10)
                error += "Value must be bewteen 1 and 10\n";
        } catch (NumberFormatException e) {
            error += e.getMessage() + "\n";
        }
        if (taFeedback.getText().equals(""))
            error += "Feedback is inexistent\n";
        feedback = taFeedback.getText();
        pass = isMotivated.isSelected();

        if (!error.equals("")) {
            setAlarm(error);
            return;
        }

        String content = "Name: " + selectedStudentForMark.getName() + "\n" +
                "Homework: " + idHom + "\n";


        if (pass) {
            content += "Mark: " + Math.floor(100 * value) / 100 + "\n";
            content += "Motivated\n";
        }
        else if(penalizare == -1){
            content += "Mark: " + 1 + "\n";
            content += "Too late\n";
        } else if(penalizare > 0) {
            double aux = value - penalizare;
            if(aux < 1)
                aux = 1;
            content += "Mark: " + Math.floor(100 * aux) / 100 + "\n";
            content += "Penalty: " + penalizare + "\n";
        } else
            content += "Mark: " + value + "\n";

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(content);
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {

            //service.addHomework(((Homework)cbMark.getValue()).getID(), taFeedback.getText(), );
            try {
                value = Math.floor(value * 100) / 100;
                service.addMark(idStud, idHom, value, feedback, pass);
//                service.addMark(selectedStudentForMark.getID(), ((Homework)cbMark.getValue()).getID(), Double.parseDouble(tfMarkValue.getText()),
//                        taFeedback.getText(), isMotivated.isSelected());
//                clearStudentMark();
            } catch (ServiceException e) {
                setAlarm(e.getMessage());
            }
        }
    }

    //Filter stuff

    private void initFilterTab() {
        filteredMarks = new FilteredList<>(observableListMarks, s-> true);
        tvFilter.setItems(filteredMarks);
        columnNameFilter.setCellValueFactory(new PropertyValueFactory<Mark,String>("studentName"));
        columnMarkFilter.setCellValueFactory(new PropertyValueFactory<Mark,Double>("value"));
        columnHomeworkFilter.setCellValueFactory(new PropertyValueFactory<Mark,Integer>("homeworkNumber"));
        columnGroupFilter.setCellValueFactory(new PropertyValueFactory<Mark, Integer>("group"));
        columnDateFilter.setCellValueFactory(new PropertyValueFactory<Mark, String>("niceDate"));
        cbFilterHomework.setItems(observableListHomework);
        addListeners();
    }

    private void magicRefresh() {
        tvStudentsS.refresh();
        tvFilter.refresh();
        tvMarks.refresh();
    }

    private void addListeners() {
        cbFilterHomework.valueProperty().addListener(((observable, oldValue, newValue) -> filterAllMarks()));
        tfNameFilter.textProperty().addListener(((observable, oldValue, newValue) -> filterAllMarks()));
        tfGroupFilter.textProperty().addListener(((observable, oldValue, newValue) -> filterAllMarks()));
        startingDate.valueProperty().addListener((observable, oldValue, newValue) -> filterAllMarks());
        endingDate.valueProperty().addListener((observable, oldValue, newValue) -> filterAllMarks());
    }

    @FXML
    private void clearFilter() {
        //tvFilter.setItems(observableListMarks);
        filteredMarks.setPredicate(s -> true);

        tfNameFilter.clear();
        startingDate.getEditor().clear();
        endingDate.getEditor().clear();
        tfGroupFilter.clear();
        cbFilterHomework.getSelectionModel().clearSelection();
        //cbFilterHomework.setPromptText("Select homework");
    }


    private void filterAllMarks() {
        Integer homeworkId = null;
        String name = null;
        String group = null;
        MyCoolPair<LocalDate, LocalDate> period = null;

        if(cbFilterHomework.getValue() != null)
            homeworkId = ((Homework)cbFilterHomework.getValue()).getID();

        if(!tfNameFilter.getText().equals("")) {
            System.out.println(":" + tfNameFilter.getText()+":");
            name = tfNameFilter.getText();
        }


        if(!tfGroupFilter.getText().equals(""))
            group = tfGroupFilter.getText();

        if(startingDate.getValue() != null && endingDate.getValue() != null) {
            LocalDate starting = startingDate.getValue();
            LocalDate ending = endingDate.getValue();
            period = new MyCoolPair(starting,ending);
        }

        Optional<Integer> oHomeworkId = Optional.ofNullable(homeworkId);
        Optional<String> oName = Optional.ofNullable(name);
        Optional<String> oGroup = Optional.ofNullable(group);
        Optional<MyCoolPair<LocalDate,LocalDate>> oPeriod = Optional.ofNullable(period);

        filteredMarks.setPredicate(mark -> (!oHomeworkId.isPresent() || mark.getHomework().getID().equals(oHomeworkId.get()))
                && (!oName.isPresent() || mark.getStudent().getName().contains(oName.get()))
                && (!oGroup.isPresent() || String.valueOf(mark.getStudent().getGroup()).contains(oGroup.get()))
                && (!oPeriod.isPresent() || (oPeriod.get().getFirst().isBefore(mark.getDate().toLocalDate()) &&
                                            oPeriod.get().getSecond().isAfter(mark.getDate().toLocalDate()))));




    }


    private void setAlarm(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Student error");
        alert.setContentText(msg);
        alert.show();
    }

    class MyCoolPair<E,T> implements Serializable {
        private E first;
        private T second;

        MyCoolPair(E e, T t) {
            this.first = e;
            this.second = t;
        }

        public E getFirst() {
            return first;
        }

        public T getSecond() {
            return second;
        }
    }
}

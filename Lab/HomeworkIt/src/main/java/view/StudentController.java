package view;

import domain.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import service.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StudentController implements Observer<StudentEvent> {
    private Service service;
    private ObservableList<Student> observableList;

    public StudentController(Service service) {
        this.service = service;
        this.service.addObserver(this);
        //Does it work?
        observableList = FXCollections.observableList(new ArrayList<>(service.getAllStudents()));
    }

    @Override
    public void update(StudentEvent studentEvent) {
        if(studentEvent.getType() == ChangeEventType.ADD)
            observableList.add(studentEvent.getNewData());
        else if(studentEvent.getType() == ChangeEventType.UPDATE) {
            observableList.remove(studentEvent.getOldData());
            observableList.add(studentEvent.getNewData());
        }
        else if(studentEvent.getType() == ChangeEventType.DELETE)
            observableList.remove(studentEvent.getNewData());
    }

    public ObservableList getList() {
        return observableList;
    }

    public void addStudent(int studentID, String name, int group, String email, String labTeacher) {
        service.addStudent(studentID, name, group, email, labTeacher);
    }

    public void updateStudent(int studentID, String name, int group, String email, String labTeacher) {
        service.updateStudent(studentID, name, group, email, labTeacher);
    }

    public void deleteStudent(int studentID) {
        service.deleteStudent(studentID);
    }
}

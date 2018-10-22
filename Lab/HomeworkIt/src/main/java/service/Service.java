package service;

import domain.Homework;
import domain.Student;
import repository.HomeworkRepository;
import repository.StudentRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class Service {
    private HomeworkRepository homeworkRepository;
    private StudentRepository studentRepository;
    private LocalDate startingDate;

    public Service(HomeworkRepository homeworkRepository, StudentRepository studentRepository, LocalDate startingDate) {
        this.homeworkRepository = homeworkRepository;
        this.studentRepository = studentRepository;
        this.startingDate = startingDate;
    }

    public boolean addStudent(int studentID, String name, int group, String email, String labTeacher) {
        return studentRepository.save(new Student(studentID, name, group, email, labTeacher))  == null;
    }

    public boolean updateStudent(int studentID, String name, int group, String email, String labTeacher) {
        return studentRepository.update(new Student(studentID, name, group, email, labTeacher)) == null;
    }

    public boolean deleteStudent(int studentID) {
        return studentRepository.delete(studentID) != null;
    }

    public Student getStudent(int studentID ) {
        return studentRepository.findOne(studentID);
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public boolean addHomework(int number, String description, int given, int deadline) {
        return homeworkRepository.save(new Homework(number, description, given, deadline)) == null;
    }

    public boolean extendProblemDeadline(int number, int newDeadline) {
        //TODO: Service exception
        Homework homework = homeworkRepository.findOne(number);
        if(homework == null)
            return false;

        LocalDate now = LocalDate.now();
        if((Period.between(startingDate,now).getDays()/7 + 1) <= homework.getDeadline() ) {
            homeworkRepository.update(new Homework(number, homework.getDescription(), homework.getGiven(), newDeadline));
            return true;
        }
        return false;
    }
}

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

    public void addStudent(int studentID, String name, int group, String email, String labTeacher) {
        if(studentRepository.save(new Student(studentID, name, group, email, labTeacher))  != null)
            throw new ServiceException("Student already exists!");
    }

    public void updateStudent(int studentID, String name, int group, String email, String labTeacher) {
        if(studentRepository.update(new Student(studentID, name, group, email, labTeacher)) != null)
            throw new ServiceException("Student doesn't exist!");
    }

    public void deleteStudent(int studentID) {
        if(studentRepository.delete(studentID) == null)
            throw new ServiceException("Student doesn't exist!");
    }

    public Student getStudent(int studentID ) {
        return studentRepository.findOne(studentID);
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addHomework(int number, String description, int given, int deadline) {
        if(homeworkRepository.save(new Homework(number, description, given, deadline)) != null)
            throw new ServiceException("Homework already exists!");
    }

    public void extendProblemDeadline(int number, int newDeadline) {
        //TODO: Service exception
        Homework homework = homeworkRepository.findOne(number);
        if(homework == null)
            throw new ServiceException("Homework cannot be null!");
        if(homework.getDeadline() >= newDeadline)
            throw new ServiceException("New deadline must be bigger than the actual deadline!");

        LocalDate now = LocalDate.now();
        if((Period.between(startingDate,now).getDays()/7 + 1) <= homework.getDeadline() ) {
            homeworkRepository.update(new Homework(number, homework.getDescription(), homework.getGiven(), newDeadline));
            return;
        }
        throw new ServiceException("Too late, you cannot extend the deadline anymore!");
    }
}

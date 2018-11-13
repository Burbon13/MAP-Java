package service;

import domain.Homework;
import domain.Mark;
import domain.Pair;
import domain.Student;
//import javafx.util.Pair;
import repository.AbstractRepository;
import repository.CrudRepository;
import repository.in_memory.HomeworkRepository;
import repository.in_memory.StudentRepository;
import service.exception.ServiceException;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;


/**
 * Service class which implements CRUD operations on students, add operation for homework
 * and 'update deadline' functionality for the saved homework
 */
public class Service {
    private CrudRepository<Integer, Homework> homeworkRepository;
    private CrudRepository<Integer, Student> studentRepository;
    private CrudRepository<Pair<Integer,Integer>, Mark> markRepository;
    private LocalDate startingDate;

    /**
     * Constructor
     * @param homeworkRepository repository responsible for managing the homework
     * @param studentRepository repository responsible for managing the students
     * @param startingDate the starting date of the semester
     */
    public Service(CrudRepository<Integer, Homework> homeworkRepository, CrudRepository<Integer, Student> studentRepository, LocalDate startingDate) {
        this.homeworkRepository = homeworkRepository;
        this.studentRepository = studentRepository;
        this.startingDate = startingDate;
    }

    public Service(CrudRepository<Integer, Homework> homeworkRepository, CrudRepository<Integer, Student> studentRepository,
                       LocalDate startingDate, CrudRepository<Pair<Integer,Integer>, Mark> markRepository) {
        this.homeworkRepository = homeworkRepository;
        this.studentRepository = studentRepository;
        this.startingDate = startingDate;
        this.markRepository = markRepository;
    }

    /**
     * Adds the student
     * @param studentID the student's unique id
     * @param name the student's name
     * @param group the student's faculty group
     * @param email the student's email
     * @param labTeacher the student's laboratory teacher name
     * @throws ServiceException if the student already exists
     */
    public void addStudent(int studentID, String name, int group, String email, String labTeacher) {
        if(studentRepository.save(new Student(studentID, name, group, email, labTeacher))  != null)
            throw new ServiceException("Student already exists!");
    }

    /**
     * Updates a student
     * @param studentID the student's unique id
     * @param name the student's name
     * @param group the student's faculty group
     * @param email the student's email
     * @param labTeacher the student's laboratory teacher name
     * @throws ServiceException if the student doesn't exist
     */
    public void updateStudent(int studentID, String name, int group, String email, String labTeacher) {
        if(studentRepository.update(new Student(studentID, name, group, email, labTeacher)) != null)
            throw new ServiceException("Student doesn't exist!");
    }

    /**
     * Deletes a student
     * @param studentID the student's unique id
     * @throws ServiceException if the student doesn't exist
     */
    public void deleteStudent(int studentID) {
        if(studentRepository.delete(studentID) == null)
            throw new ServiceException("Student doesn't exist!");
    }

    /**
     * Gets the student with the given studentID
     * @param studentID the student's unique id
     * @return the student if exists, null else
     */
    public Student getStudent(int studentID ) {
        return studentRepository.findOne(studentID);
    }

    /**
     * Gives all the saved students
     * @return all the students
     */
    public Collection<Student> getAllStudents() {
        return  (Collection<Student>)studentRepository.findAll();
    }

    /**
     * Adds homework
     * @param number the homework's unique id
     * @param description the homework's short description
     * @param given the homework's starting week
     * @param deadline the homework's deadline week
     */
    public void addHomework(int number, String description, int given, int deadline) {
        if(homeworkRepository.save(new Homework(number, description, given, deadline)) != null)
            throw new ServiceException("Homework already exists!");
    }

    /**
     * Deletes homework from repository
     * @param number the homework's id
     * @throws ServiceException if the homework doesn't exist in the repo
     */
    public void deleteHomework(int number) {
        if(homeworkRepository.delete(number) == null)
            throw new ServiceException(String.format("Homework with id %d doesn't exist!", number));
    }

    /**
     * Gets all the homework
     * @return the homework
     */
    public Collection<Homework> getAllHomework() {
        return (Collection<Homework>)homeworkRepository.findAll();
    }

    /**
     * Extends the deadline of a given problem
     * @param number the problem's unique id
     * @param newDeadline the new deadline to be set
     * @throws ServiceException if the operation cannot be done
     */
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

    public void addMark(int studentId, int homeworkId, int value, String description) {
        Student student = studentRepository.findOne(studentId);
        if(student == null)
            throw new ServiceException("The student with the given id doesn't exist!");
        Homework homework = homeworkRepository.findOne(homeworkId);
        if(homeworkRepository.findOne(homeworkId) == null)
            throw new ServiceException("The homework with the given id doesn't exist");

        if(markRepository.save(new Mark(student, homework, value, description)) != null)
            throw new ServiceException("Mark already exists!");
    }

    public Collection<Mark> getAllMarks() {
        return (Collection<Mark>)markRepository.findAll();
    }
}

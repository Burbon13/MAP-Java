package service;

import domain.Homework;
import domain.Mark;
import domain.Pair;
import domain.Student;
import repository.CrudRepository;
import service.exception.ServiceException;
import view.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;


/**
 * Service class which implements CRUD operations on students, add operation for homework
 * and 'update deadline' functionality for the saved homework
 */
public class Service implements Observable<AppEvent> {
    private CrudRepository<Integer, Homework> homeworkRepository;
    private CrudRepository<Integer, Student> studentRepository;
    private CrudRepository<Pair<Integer,Integer>, Mark> markRepository;
    private LocalDate startingDate;
    //Added for implementing observable
    private ArrayList<Observer<AppEvent>> observers = new ArrayList<>();

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

    @Override
    public void addObserver(Observer<AppEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<AppEvent> e) {
        observers.remove(e);
    }

    @Override
    public void notifyObserver(AppEvent e) {
        observers.forEach(obs -> obs.update(e));
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
        notifyObserver(new AppEvent(null, getStudent(studentID), EventClass.STUDENTS, ChangeEventType.ADD));
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
        Student stud = getStudent(studentID);
        if(studentRepository.update(new Student(studentID, name, group, email, labTeacher)) != null)
            throw new ServiceException("Student doesn't exist!");
        notifyObserver(new AppEvent(stud, getStudent(studentID), EventClass.STUDENTS, ChangeEventType.UPDATE));
    }

    /**
     * Deletes a student
     * @param studentID the student's unique id
     * @throws ServiceException if the student doesn't exist
     */
    public void deleteStudent(int studentID) {
        Student stud = studentRepository.delete(studentID);
        if(stud == null)
            throw new ServiceException("Student doesn't exist!");
        notifyObserver(new AppEvent(null, stud, EventClass.STUDENTS, ChangeEventType.DELETE));
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

    public void addMark(int studentId, int homeworkId, double value, String description, boolean spare) {
        Student student = studentRepository.findOne(studentId);
        if(student == null)
            throw new ServiceException("The student with the given id doesn't exist!");
        Homework homework = homeworkRepository.findOne(homeworkId);
        if(homeworkRepository.findOne(homeworkId) == null)
            throw new ServiceException("The homework with the given id doesn't exist");

        //TODO: Calculate the minus points
        value = calculateMinusPoints(homework, value, spare);

        if(markRepository.save(new Mark(student, homework, value, description)) != null)
            throw new ServiceException("Mark already exists!");

        notifyObserver(new AppEvent(null, new Mark(student, homework, value, description), EventClass.MARKS, ChangeEventType.ADD));

        addToStudentFile(homework.getID(), value,
                (int)ChronoUnit.DAYS.between(startingDate, LocalDate.now())/7 + 1, homework.getDeadline(),
                description, student.getName());
    }

    private double calculateMinusPoints(Homework homework, double value, boolean spare) {
        if(spare)
            return value;

        LocalDate now = LocalDate.now();

        int diff = ((int)ChronoUnit.DAYS.between(startingDate, now)/7 + 1) - homework.getDeadline();

        if(diff > 0 && diff <= 2) {
            System.out.println("Intarziat cu " + diff + " saptamani!");
            value -= 2.5f * (double)diff;
            value = value > 1 ? value : 1;
        } else if(diff > 2)
            value = 1;

        return value;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void addToStudentFile(int homeworkId, double value, int weekGiven, int deadline, String description, String sName) {
        File yourFile = new File("./data/" + sName  + ".txt");
        try {
            yourFile.createNewFile(); // if file already exists will do nothing
            FileOutputStream oFile = new FileOutputStream(yourFile, true);
            DataOutputStream stream = new DataOutputStream(oFile);
            BufferedOutputStream buff = new BufferedOutputStream(stream);

            buff.write(("Tema: " + homeworkId + "\n").getBytes(StandardCharsets.UTF_8));
            buff.write(("Nota: " + value + "\n").getBytes(StandardCharsets.UTF_8));
            buff.write(("Predata in saptamana: " + weekGiven + "\n").getBytes(StandardCharsets.UTF_8));
            buff.write(("Deadline: " + deadline + "\n").getBytes(StandardCharsets.UTF_8));
            buff.write(("Feedback: " + description + "\n").getBytes(StandardCharsets.UTF_8));
            buff.write(("\n").getBytes(StandardCharsets.UTF_8));


            buff.flush();
            buff.close();
            stream.close();
            oFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Collection<Mark> getAllMarks() {
        return (Collection<Mark>)markRepository.findAll();
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void updateMarksForStudents(Student student) {
        ArrayList<Mark> toUpd = new ArrayList<>();


        int a = getAllMarks().size();
        getAllMarks().forEach(mark -> {
            System.out.println(mark.getStudent().getID());
            System.out.println(student.getID());
            System.out.println("-------");
            if(mark.getStudent().getID().equals(student.getID())) {
                toUpd.add(mark);
            }
        });

        toUpd.forEach(mark -> {
            System.out.println("Modfica");
            markRepository.update(new Mark(student, mark.getHomework(), mark.getValue(), mark.getFeedback()));
        });
    }
}

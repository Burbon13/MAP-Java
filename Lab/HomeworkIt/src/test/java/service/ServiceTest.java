package service;

import domain.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.HomeworkRepository;
import repository.StudentRepository;
import validator.HomeworkValidator;
import validator.StudentValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    private int day, month, year;
    private Service service;

    @BeforeEach
    void setUp() {
        service = new Service(new HomeworkRepository(new HomeworkValidator()), new StudentRepository(new StudentValidator()), LocalDate.now().minusWeeks(4));
        service.addStudent(123,"Razvan", 226, "rrir2390@yahoo.com", "Dorina");
        service.addStudent(125,"Razvan", 226, "fasf24390@yahoo.com", "Dorina");
        service.addStudent(128,"Razvan", 228, "sitt2331@yahoo.com", "Mihaela");
        service.addStudent(147,"Razvan", 228, "rrgf4390@yahoo.com", "Teodora");
        service.addStudent(188,"Razvan", 230, "rgfr2590@yahoo.com", "Dorina");

        service.addHomework(1,"One problem", 1,2);
        service.addHomework(2,"One problem", 1,4);
        service.addHomework(5,"One problem", 3,4);
        service.addHomework(3,"One problem", 2,5);
        service.addHomework(8,"One problem", 4,5);
        service.addHomework(4,"One problem", 6,10);
    }

    @Test
    void addHomework() {
        try {
            service.addHomework(1,"dada",3,6);
        } catch(ServiceException ex) {
            assertEquals(ex.getMessage(), "Homework already exists!");
        }
    }

    @Test
    void addStudent() {
        assertNull(service.getStudent(1251));
        try {
            service.addStudent(123, "Razvan", 226, "rrir2390@yahoo.com", "Dorina");
            fail();
        } catch (ServiceException ex) {
            assertEquals("Student already exists!", ex.getMessage());
        }
        service.addStudent(1251, "Razvan", 226, "rrir2390@yahoo.com", "Dorina");
        assertEquals(service.getAllStudents().size(),6);
        assertEquals(service.getStudent(1251), new Student(1251,"",0,"",""));
    }

    @Test
    void updateStudent() {
        try {
            service.updateStudent(1,"fas",432,"fsa","fasf");
        } catch (ServiceException ex) {
            assertEquals(ex.getMessage(), "Student doesn't exist!");
        }
        service.updateStudent(123,"fsfsd",1,"fsf","fsfs");
    }

    @Test
    void deleteStudent() {
        try {
            service.deleteStudent(0);
        } catch (ServiceException ex) {
            assertEquals(ex.getMessage(), "Student doesn't exist!");
        }
        service.deleteStudent(123);
    }

    @Test
    void getStudent() {
        assertNull(service.getStudent(1251));
        assertEquals(service.getStudent(123), new Student(123,"",0,"",""));
    }

    @Test
    void getAllStudents() {
        assertEquals(service.getAllStudents().size(),5);
    }

    @Test
    void extendProblemDeadline() {
        try {
            service.extendProblemDeadline(1,1);
        } catch (ServiceException ex) {
            assertEquals(ex.getMessage(), "New deadline must be bigger than the actual deadline!");
        }

        try {
            service.extendProblemDeadline(233,4);
        } catch(ServiceException ex) {
            assertEquals(ex.getMessage(), "Homework cannot be null!");
        }

        try {
            service.extendProblemDeadline(2,13);
        } catch(ServiceException ex) {
            assertEquals(ex.getMessage(), "Too late, you cannot extend the deadline anymore!");
        }


        service.extendProblemDeadline(3,6);

        try {
            service.extendProblemDeadline(5,4);
        } catch(ServiceException ex) {
            assertEquals(ex.getMessage(), "New deadline must be bigger than the actual deadline!");
        }

        service.extendProblemDeadline(8,10);
    }
}
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
    void addStudent() {
        assertNull(service.getStudent(1251));
        assertFalse(service.addStudent(123, "Razvan", 226, "rrir2390@yahoo.com", "Dorina"));
        assertTrue(service.addStudent(1251, "Razvan", 226, "rrir2390@yahoo.com", "Dorina"));
        assertEquals(service.getAllStudents().size(),6);
        assertEquals(service.getStudent(1251), new Student(1251,"",0,"",""));
    }

    @Test
    void updateStudent() {
        assertFalse(service.updateStudent(1,"fas",432,"fsa","fasf"));
        assertTrue(service.updateStudent(123,"fsfsd",1,"fsf","fsfs"));
    }

    @Test
    void deleteStudent() {
        assertFalse(service.deleteStudent(0));
        assertTrue(service.deleteStudent(123));
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
        assertFalse(service.extendProblemDeadline(1,1));
        assertFalse(service.extendProblemDeadline(233,4));
        assertFalse(service.extendProblemDeadline(2,5));
        assertTrue(service.extendProblemDeadline(3,6));
        assertFalse(service.extendProblemDeadline(5,4));
        assertTrue(service.extendProblemDeadline(8,10));
    }
}
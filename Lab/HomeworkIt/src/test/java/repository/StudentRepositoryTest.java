package repository;

import domain.Student;
import validator.StudentValidator;
import validator.exception.ValidationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryTest {
    private StudentRepository studentRepository;
    private Student st1 = new Student(123,"Razvan",226, "razvan_roatis@yahoo.com", "Mihaela");
    private Student st2 = new Student(200,"Daniel",226, "daniel@yahoo.com", "Daniela");
    private Student st3 = new Student(332,"Cristian",225, "cristi@yahoo.com", "Mihaela");
    private Student st4 = new Student(444,"Andreea",224, "dea@yahoo.com", "Daniela");
    private Student st5 = new Student(11111,"Andreea",224, "dea@yahoo.com", "Daniela");

    @BeforeEach
    void setUp() {
        studentRepository = new StudentRepository(new StudentValidator());
        try {
            studentRepository.save(st1);
            studentRepository.save(st2);
            studentRepository.save(st3);
            studentRepository.save(st4);
        } catch (ValidationException ve) {
            System.out.println(ve.toString());
        }

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findOne() {
        try {
            studentRepository.findOne(null);
            fail();
        } catch (IllegalArgumentException ile) {
            assertTrue(true);
        }

        assertNull(studentRepository.findOne(1));
        assertEquals(studentRepository.findOne(200), st2);

    }

    @Test
    void findAll() {
        Collection<Student> students = studentRepository.findAll();
        assertEquals(students.size(),4);

        assertTrue(students.contains(st1));
        assertTrue(students.contains(st2));
        assertTrue(students.contains(st3));
        assertTrue(students.contains(st4));
    }

    @Test
    void save() {
        try {
            studentRepository.save(null);
            fail();
        } catch (IllegalArgumentException ile) {
            assertTrue(true);
        } catch (ValidationException ve) {
            fail();
        }

        try {
            assertNull(studentRepository.save(new Student(555,"Misu",12345,"ha@yg.com","Misu")));
            assertTrue(true);
        } catch (IllegalArgumentException | ValidationException ile) {
            fail();
        }

        try {
            assertEquals(studentRepository.save(new Student(123,"Misu",12345,"ha@yg.com",
                    "Misu")),st1);
            assertTrue(true);
        } catch (IllegalArgumentException | ValidationException ile) {
            fail();
        }

        try {
            studentRepository.save(new Student(0,"",0,"",""));
            fail();
        }catch (IllegalArgumentException ile) {
            fail();
        } catch (ValidationException ve) {
            assertTrue(true);
        }
    }

    @Test
    void delete() {
        try {
            studentRepository.delete(null);
            fail();
        } catch (IllegalArgumentException ile) {
            assertTrue(true);
        }

        try {
            assertEquals(studentRepository.delete(200),st2);
        } catch (IllegalArgumentException ile) {
            fail();
        }
    }

    @Test
    void update() {
        try {
            studentRepository.update(null);
            fail();
        } catch (IllegalArgumentException iar) {
            assertTrue(true);
        } catch (ValidationException ve) {
            fail();
        }

        try {
            assertNull(studentRepository.update(new Student(123, "Gaina", 665,
                    "gainusha@yahoo.com", "Ciobanu")));
        } catch (ValidationException e) {
            fail();
        }

        try {
            assertEquals(studentRepository.update(st5),st5);
        } catch (ValidationException e) {
            fail();
        }
    }

}
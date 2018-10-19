package Domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    private Student student1, student2;

    @BeforeEach
    void setUp() {
        student1 = new Student(124,"Razvan",226,"razvan_roatis@yahoo.com","Mihaela");
        student2 = new Student(555,"Mihai",726,"mihai@yahoo.com","Ionel");
    }

    @Test
    void getID() {
        assertEquals(124, (int) student1.getID());
        assertEquals(555, (int) student2.getID());
    }

    @Test
    void setID() {
        assertEquals(124, (int) student1.getID());
        student1.setID(123);
        assertEquals(123, (int) student1.getID());
    }

    @Test
    void getName() {
        assertEquals(student1.getName(),"Razvan");
        assertEquals(student2.getName(),"Mihai");
    }

    @Test
    void getGroup() {
        assertEquals(student1.getGroup(),226);
        assertEquals(student2.getGroup(),726);
    }

    @Test
    void getEmail() {
        assertEquals(student1.getEmail(),"razvan_roatis@yahoo.com");
        assertEquals(student2.getEmail(),"mihai@yahoo.com");
    }

    @Test
    void getLabTeacher() {
        assertEquals(student1.getLabTeacher(),"Mihaela");
        assertEquals(student2.getLabTeacher(),"Ionel");
    }
}
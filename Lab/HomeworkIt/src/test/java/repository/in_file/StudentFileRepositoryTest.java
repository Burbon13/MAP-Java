package repository.in_file;

import domain.Homework;
import domain.Student;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.exception.FileRepositoryException;
import validator.HomeworkValidator;
import validator.StudentValidator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("SpellCheckingInspection")
class StudentFileRepositoryTest {
    StudentFileRepository repo;

    @BeforeEach
    void setUp() {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("./data/temp_st_text.txt"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeInt(4);
            objectOutputStream.writeObject(new Student(1, "Razvan", 226, "razvan@yahoo.com", "Dorina"));
            objectOutputStream.writeObject(new Student(2, "Cati", 224, "cati@yahoo.com", "Dorina"));
            objectOutputStream.writeObject(new Student(3, "Ciobanu", 227, "daniel@yahoo.com", "Mirela"));
            objectOutputStream.writeObject(new Student(4, "Vlad", 226, "vlad@yahoo.com", "Dorina"));

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            fail();
        }

        repo = new StudentFileRepository(new StudentValidator(), "temp_st_text.txt");
    }

    @Test
    void findOne() {
        assertEquals(repo.findOne(2).getName(), "Cati");
        assertNull(repo.findOne(3243));
    }

    @Test
    void findAll() {
        assertEquals(repo.findAll().size(), 4);
    }

    @Test
    void save() {
        assertNull(repo.save(new Student(10, "Mihaha", 111, "a@yahoo.com", "Suciu")));
        StudentFileRepository aux = new StudentFileRepository(new StudentValidator(), "temp_st_text.txt");
        assertEquals(aux.findAll().size(), 5);
        assertEquals(aux.findOne(10).getName(), "Mihaha");
    }

    @Test
    void delete() {
        assertNotNull(repo.delete(1));
        StudentFileRepository aux = new StudentFileRepository(new StudentValidator(), "temp_st_text.txt");
        assertEquals(aux.findAll().size(), 3);
        assertNull(aux.findOne(1));
    }

    @Test
    void update() {
        assertNull(repo.update(new Student(1,"ga",334,"faf", "fa")));
        StudentFileRepository aux = new StudentFileRepository(new StudentValidator(), "temp_st_text.txt");
        assertEquals(aux.findAll().size(), 4);
        assertEquals(aux.findOne(1).getGroup(), 334);
    }

    @Test
    void repoExp() {
        try {
            StudentFileRepository sfr = new StudentFileRepository(new StudentValidator(), "ha");
        } catch (FileRepositoryException ex) {
            assertTrue(true);
        }
    }
}
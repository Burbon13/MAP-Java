package repository.in_file;

import domain.Homework;
import org.junit.jupiter.api.*;
import validator.HomeworkValidator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class HomeworkFileRepositoryTest {
    HomeworkFileRepository repo;

    @BeforeEach
    void setUp() {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("./data/temp_hm_text.txt"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeInt(4);
            objectOutputStream.writeObject(new Homework(1, "First one", 10,14));
            objectOutputStream.writeObject(new Homework(4, "Harder", 4, 5));
            objectOutputStream.writeObject(new Homework(10, "The easiest", 1, 2));
            objectOutputStream.writeObject(new Homework(20, "Last one", 13,14));

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            fail();
        }

        repo = new HomeworkFileRepository(new HomeworkValidator(), "temp_hm_text.txt");
    }

    @Test
    void findOne() {
        assertEquals(repo.findOne(4), new Homework(4, "A harder one", 7, 9));
        assertEquals(5, repo.findOne(4).getDeadline());
        assertEquals("The easiest", repo.findOne(10).getDescription());
        assertNull(repo.findOne(30));
    }

    @Test
    void findAll() {
        assertEquals(repo.findAll().size(), 4);
    }

    @Test
    void save() {
        assertNotNull(repo.save(new Homework(4, "haha", 10, 13)));
        assertEquals(repo.findAll().size(), 4);
        assertNull(repo.save(new Homework(100, "Wei", 4, 5)));
        assertEquals(repo.findAll().size(), 5);
    }

    @Test
    void delete() {
        assertNotNull(repo.delete(4));
        assertEquals(repo.findAll().size(), 3);
    }

    @Test
    void update() {
        assertEquals(repo.findOne(1).getDescription(), "First one");
        repo.update(new Homework(1, "haha", 6,7));
        assertEquals(repo.findOne(1).getDescription(), "haha");
    }
}
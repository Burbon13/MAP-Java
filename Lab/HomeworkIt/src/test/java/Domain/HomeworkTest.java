package Domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HomeworkTest {
    private Homework hm1, hm2, hm3;

    @BeforeEach
    void setUp() {
        hm1 = new Homework(1,"Eazy", 11, 13);
        hm2 = new Homework(2,"Medium", 1, 14);
        hm3 = new Homework(3,"Hard tasks", 5, 6);
    }

    @Test
    void equals() {
        assertNotEquals(hm1,hm2);
        assertNotEquals(hm1,null);
        assertEquals(hm1,hm1);
        assertEquals(hm1, new Homework(1,"Razy",0,1));
    }

    @Test
    void getID() {
        assertEquals((int)hm1.getID(),1);
        assertEquals((int)hm2.getID(),2);
    }

    @Test
    void setID() {
        assertEquals((int)hm1.getID(),1);
        hm1.setID(10);
        assertEquals((int)hm1.getID(),10);
    }

    @Test
    void getDescription() {
        assertEquals(hm3.getDescription(),"Hard tasks");
    }

    @Test
    void getGiven() {
        assertEquals(hm2.getGiven(),1);
    }

    @Test
    void getDeadline() {
        assertEquals(hm1.getDeadline(),13);
    }
}
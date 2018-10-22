package validator;

import domain.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentValidatorTest {
    @Test
    void validate() {
        StudentValidator validator = new StudentValidator();

        Student student1 = new Student(123,"Razvan",336,"razvan@yahoo.com","Daniela");
        try {
            validator.validate(student1);
        } catch (ValidationException ve) {
            fail();
        }

        Student student2 = new Student(123,"",336,null,"Daniela");
        try {
            validator.validate(student2);
            fail();
        } catch (ValidationException ve) {
            assertEquals("email cannot be null\nname cannot be empty\n", ve.toString());
        }

        Student student3 = new Student(0,"",336,null,"");
        try {
            validator.validate(student3);
            fail();
        } catch (ValidationException ve) {
            assertEquals("id cannot be 0\n" +
                    "email cannot be null\n" +
                    "name cannot be empty\n" +
                    "labTeacher cannot be empty\n", ve.toString());
        }

        try {
            validator.validate(null);
            fail();
        } catch (ValidationException ve) {
            assertEquals("student cannot be null", ve.toString());
        }

        Student student4 = new Student(0,null,0,"",null);
        try {
            validator.validate(student4);
            fail();
        } catch (ValidationException ve) {
            assertEquals("id cannot be 0\n" +
                    "email cannot be empty\n" +
                    "group cannot be 0\n" +
                    "name cannot be null\n" +
                    "labTeacher cannot be null\n", ve.toString());
        }
    }
}
package Validator;

import Domain.Homework;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HomeworkValidatorTest {

    @Test
    void validate() {
        HomeworkValidator homeworkValidator = new HomeworkValidator();
        Homework homework = new Homework(1,"desc",4,10);
        try {
            homeworkValidator.validate(homework);
        }  catch(ValidationException ve) {
            fail();
        }

        homework = new Homework(0,"",1,4);
        try {
            homeworkValidator.validate(homework);
            fail();
        }  catch(ValidationException ve) {
            assertEquals(ve.toString(),"id cannot be null\n" +
                    "description cannot be empty\n");
        }

        homework = new Homework(2,"fsd",20,0);
        try {
            homeworkValidator.validate(homework);
            fail();
        }  catch(ValidationException ve) {
            assertEquals(ve.toString(),"given must be between 1 and 14\n" +
                    "deadline must be between 1 and 14\n" +
                    "given must be strictly smaller than deadline\n");
        }
    }
}
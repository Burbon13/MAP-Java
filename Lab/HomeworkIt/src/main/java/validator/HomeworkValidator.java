package validator;

import domain.Homework;
import validator.exception.ValidationException;

public class HomeworkValidator implements Validator<Homework> {

    /**
     * Validator method that checks if a homework has any problems
     * @param homework what needs to be validated
     * @throws ValidationException if the homework is not correct
     */
    @Override
    public void validate(Homework homework) throws ValidationException {
        if(homework == null)
            throw new ValidationException("homework cannot be null");

        StringBuilder stringBuilder = new StringBuilder();
        if(homework.getID() == 0)
            stringBuilder.append("id cannot be null\n");
        if(homework.getGiven() < 1 || homework.getGiven() > 14)
            stringBuilder.append("given must be between 1 and 14\n");
        if(homework.getDeadline() < 1 || homework.getDeadline() > 14)
            stringBuilder.append("deadline must be between 1 and 14\n");
        if(homework.getGiven() >= homework.getDeadline())
            stringBuilder.append("given must be strictly smaller than deadline\n");
        if(homework.getDescription() == null)
            stringBuilder.append("description cannot be null\n");
        else if(homework.getDescription().equals(""))
            stringBuilder.append("description cannot be empty\n");
        String problems = stringBuilder.toString();
        if(problems.length() > 0)
            throw new ValidationException(problems);
    }
}

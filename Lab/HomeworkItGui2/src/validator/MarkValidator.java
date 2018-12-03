package validator;

import domain.Mark;
import validator.exception.ValidationException;

public class MarkValidator implements Validator<Mark> {
    @Override
    public void validate(Mark entity) throws ValidationException {

        StringBuilder stringBuilder = new StringBuilder();
        if(entity.getValue() < 1 && entity.getValue() > 10)
            stringBuilder.append("Value must be between 1 and 10!");
        if(entity.getStudent() == null)
            stringBuilder.append("Student cannot be null!");
        if(entity.getHomework() == null)
            stringBuilder.append("Homework cannot be null!");
        String problems = stringBuilder.toString();
        if(problems.length() > 0)
            throw new ValidationException(problems);
    }
}

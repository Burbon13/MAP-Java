package validator;

import domain.Student;

public class StudentValidator implements Validator<Student> {
    @Override
    public void validate(Student student) throws ValidationException {
        if(student == null)
            throw new ValidationException("student cannot be null");

        StringBuilder stringBuilder = new StringBuilder();
        if(student.getID() == 0)
            stringBuilder.append("id cannot be 0\n");
        if(student.getEmail() == null)
            stringBuilder.append("email cannot be null\n");
        else if(student.getEmail().equals(""))
            stringBuilder.append("email cannot be empty\n");
        if(student.getGroup() == 0)
            stringBuilder.append("group cannot be 0\n");
        if(student.getName() == null)
            stringBuilder.append("name cannot be null\n");
        else if(student.getName().equals(""))
            stringBuilder.append("name cannot be empty\n");
        if(student.getLabTeacher() == null)
            stringBuilder.append("labTeacher cannot be null\n");
        else if(student.getLabTeacher().equals(""))
            stringBuilder.append("labTeacher cannot be empty\n");
        String problems = stringBuilder.toString();
        if(problems.length() > 0)
            throw new ValidationException(problems);
    }
}

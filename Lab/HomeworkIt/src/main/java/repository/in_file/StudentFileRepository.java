package repository.in_file;

import domain.Student;
import repository.AbstractFileRepository;
import validator.Validator;

public class StudentFileRepository extends AbstractFileRepository<Integer, Student> {
    public StudentFileRepository(Validator<Student> validator, String fileName) {
        super(validator, fileName);
    }
}

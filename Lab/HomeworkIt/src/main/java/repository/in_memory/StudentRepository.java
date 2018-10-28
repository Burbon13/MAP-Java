package repository.in_memory;

import domain.Student;
import repository.AbstractRepository;
import validator.Validator;

public class StudentRepository extends AbstractRepository<Integer, Student> {
    public StudentRepository(Validator<Student> validator) {
        super(validator);
    }
}

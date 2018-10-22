package repository;

import domain.Student;
import validator.Validator;

public class StudentRepository extends  AbstractRepository<Integer, Student> {
    public StudentRepository(Validator<Student> validator) {
        super(validator);
    }
}

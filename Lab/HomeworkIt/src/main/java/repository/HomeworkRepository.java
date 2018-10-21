package repository;

import domain.Homework;
import validator.Validator;

public class HomeworkRepository extends AbstractRepository<Integer, Homework> {
    public HomeworkRepository(Validator<Homework> validator) {
        super(validator);
    }
}

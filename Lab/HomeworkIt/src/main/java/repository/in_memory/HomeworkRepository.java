package repository.in_memory;

import domain.Homework;
import repository.AbstractRepository;
import validator.Validator;

public class HomeworkRepository extends AbstractRepository<Integer, Homework> {
    public HomeworkRepository(Validator<Homework> validator) {
        super(validator);
    }
}

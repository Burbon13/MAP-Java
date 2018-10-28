package repository.in_file;

import domain.Homework;
import repository.AbstractFileRepository;
import validator.Validator;


public class HomeworkFileRepository extends AbstractFileRepository<Integer, Homework> {
    public HomeworkFileRepository(Validator<Homework> validator, String fileName) {
        super(validator, fileName);
    }
}

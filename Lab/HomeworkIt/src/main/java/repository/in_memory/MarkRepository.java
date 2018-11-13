package repository.in_memory;

import domain.Mark;
//import javafx.util.Pair;
import domain.Pair;
import repository.AbstractRepository;
import validator.Validator;

public class MarkRepository extends AbstractRepository<Pair<Integer, Integer>, Mark> {
    public MarkRepository(Validator<Mark> validator) {
        super(validator);
    }
}

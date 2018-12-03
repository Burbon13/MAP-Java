package repository.in_file;

import domain.Mark;
//import javafx.util.Pair;
import domain.Pair;
import repository.AbstractFileRepository;
import validator.Validator;

public class MarkFileRepository extends AbstractFileRepository<Pair<Integer, Integer>, Mark> {
    /**
     * Constructor
     *
     * @param validator must implement Validator interface
     */
    public MarkFileRepository(Validator<Mark> validator, String fileName) {
        super(validator, fileName);
    }
}

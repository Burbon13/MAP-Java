package II.repo;

import II.domain.MessageTask;
import II.valid.IValidator;

public class MTRepository extends AbstractCRUDRepository<String,MessageTask> {

    public MTRepository(IValidator<MessageTask> v){
        super(v);
    }
}


package Repo;

import Task.MessageTask;
import Validator.IValidator;
public class MTRepository extends AbstractCRUDRepository<String,MessageTask> {

    public MTRepository(IValidator<MessageTask> v){
        super(v);
    }
}

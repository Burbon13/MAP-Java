package repository;

import domain.MessageTask;
import domain.Task;
import validation.IValidation;

public class MessageTaskRepository extends AbstractCRUDRepository<String, MessageTask> {
    public MessageTaskRepository(IValidation<MessageTask> validator) {
        super(validator);
    }
}

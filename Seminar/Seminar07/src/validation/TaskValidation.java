package validation;

import domain.*;

public class TaskValidation implements IValidation<MessageTask> {

    @Override
    public void validate(MessageTask elem) throws ValidationException {
        if(elem.getId() == null){
            throw  new ValidationException("Task id is null");
        }
        if(elem.getId().equals("")){
            throw new ValidationException("Task id is missing");
        }
        //idem pentru celelalte atribute ale clasei Task
    }
}

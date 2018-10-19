
public class MTValidator implements IValidator<MessageTask> {

    public void validate(MessageTask mt) throws ValidationException{
        if(mt.getId()== null || mt.getId().equals("")){
            throw new ValidationException("Id invalid\n");
        }//else if...
    }
}

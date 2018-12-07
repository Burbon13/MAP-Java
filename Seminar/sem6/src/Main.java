import Repo.SerRepo;
import Repo.XMLRepo;
import Task.MessageTask;
import Validator.IValidator;
import Validator.MTValidator;
import Validator.ValidationException;


public class Main{
    public static void xml()
    {
        IValidator<MessageTask> validator = new MTValidator();
        XMLRepo repo = new XMLRepo(validator,"./data/tasks.xml");
////
//        try {
//            repo.save(new MessageTask("1", "des1", "mesaj1", "me", "you", "10-10-2018 20:20:00"));
//            repo.save(new MessageTask("2", "des2", "mesaj2", "mee", "yo", "10-10-2018 20:20:00"));
//            repo.save(new MessageTask("3", "des3", "mesaj3", "meeee", "y", "10-10-2018 20:20:00"));
//            repo.save(new MessageTask("4", "des4", "mesaj4", "meeeee", "you", "10-10-2018 20:20:00"));
//        } catch (ValidationException e) {
//            e.printStackTrace();
//        }

        repo.findAll().forEach(System.out::println);
    }
    public static void ser()
    {
        IValidator<MessageTask> validator = new MTValidator();
        SerRepo repo = new SerRepo(validator,"./data/tasks.ser");

//        try {
//            repo.save(new MessageTask("1", "des1", "mesaj1", "me", "you", "2018-10-10 20:20:00"));
//            repo.save(new MessageTask("2", "des2", "mesaj2", "mee", "yo", "2018-10-10 20:20:00"));
//            repo.save(new MessageTask("3", "des3", "mesaj3", "meeee", "y", "2018-10-10 20:20:00"));
//            repo.save(new MessageTask("4", "des4", "mesaj4", "meeeee", "you", "2018-10-10 20:20:00"));
//        } catch (ValidationException e) {
//            e.printStackTrace();
//        }

        repo.findAll().forEach(System.out::println);
    }
    public static void main(String[] args) {
        xml();
        //ser();
    }
}
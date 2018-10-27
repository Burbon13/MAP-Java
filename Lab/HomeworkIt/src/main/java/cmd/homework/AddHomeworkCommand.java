package cmd.homework;

import cmd.AbstractCommand;
import service.Service;
import service.exception.ServiceException;

public class AddHomeworkCommand extends AbstractCommand {
    private int hmNumber, given, deadline;
    private String description;

    public AddHomeworkCommand(Service service, int hmNumber, int given, int deadline, String description) {
        super(service);
        this.hmNumber = hmNumber;
        this.given = given;
        this.deadline = deadline;
        this.description = description;
    }
    
    @Override
    public void execute() {
        try {
            service.addHomework(hmNumber, description, given, deadline);
            System.out.println("Homework added!");
        } catch (ServiceException ex) {
            System.out.println(ex.getMessage());
        }
    }

//    @Override
//    public void undo() {
//        try {
//            service.deleteHomework(hmNumber);
//        } catch (ServiceException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
}

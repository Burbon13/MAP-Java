package cmd.homework;

import cmd.AbstractServiceCommand;
import cmd.CommandException;
import service.Service;
import service.exception.ServiceException;
import validator.exception.ValidationException;

public class AddHomeworkServiceCommand extends AbstractServiceCommand {
    private int hmNumber, given, deadline;
    private String description;

    public AddHomeworkServiceCommand(Service service, String[] sep_params) {
        super(service);
        if(sep_params.length != 5)
            throw new CommandException("Invalid parameters!");

        this.description = sep_params[2];
        try {
            this.hmNumber = Integer.parseInt(sep_params[1]);
            this.given = Integer.parseInt(sep_params[3]);
            this.deadline = Integer.parseInt(sep_params[4]);
        } catch (NumberFormatException ex) {
            throw new CommandException("Invalid parameters! " + ex.getMessage());
        }
    }

    /**
     * Adds homework into memory
     */
    @Override
    public void execute() {
        try {
            service.addHomework(hmNumber, description, given, deadline);
            System.out.println("Homework added!");
        } catch (ServiceException | ValidationException ex) {
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

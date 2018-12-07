package cmd.homework;

import cmd.AbstractServiceCommand;
import cmd.CommandException;
import service.Service;
import service.exception.ServiceException;

public class DeleteHomeworkCommand extends AbstractServiceCommand {
    private int number;

    public DeleteHomeworkCommand(Service service, String[] sep_params) {
        super(service);
        if(sep_params.length != 2)
            throw new CommandException("Invalid parameters!");
        try {
            this.number = Integer.parseInt(sep_params[1]);
        } catch (NumberFormatException ex) {
            throw new CommandException("Invalid parameters! " + ex.getMessage());
        }
    }

    @Override
    public void execute() {
        try {
            service.deleteHomework(number);
            System.out.println("Homework deleted!");
        } catch (ServiceException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

package cmd.homework;

import cmd.AbstractCommand;
import cmd.CommandException;
import service.Service;
import service.exception.ServiceException;

public class ExtendDeadlineCommand extends AbstractCommand {
    private int number /*, oldDeadline*/, newDeadline;

    public ExtendDeadlineCommand(Service service, String[] sep_params) {
        super(service);
        if(sep_params.length != 3)
            throw new CommandException("Invalid parameters!");

         try {
             this.number = Integer.parseInt(sep_params[1]);
             this.newDeadline = Integer.parseInt(sep_params[2]);
         } catch (NumberFormatException ex) {
             throw new CommandException("Invalid parameters! " + ex.getMessage());
         }
    }

    /**
     * Extends deadline of homework from memory
     */
    @Override
    public void execute() {
        try {
            service.extendProblemDeadline(number, newDeadline);
            System.out.println("Deadline extended!");
        } catch (ServiceException ex) {
            System.out.println(ex.getMessage());
        }
    }

//    @Override
//    public void undo() {
//
//    }
}

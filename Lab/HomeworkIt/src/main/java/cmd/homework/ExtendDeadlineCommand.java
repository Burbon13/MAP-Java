package cmd.homework;

import cmd.AbstractCommand;
import service.Service;
import service.exception.ServiceException;

public class ExtendDeadlineCommand extends AbstractCommand {
    private int number /*, oldDeadline*/, newDeadline;

    public ExtendDeadlineCommand(Service service, int number, int newDeadline) {
        super(service);
        this.number = number;
        this.newDeadline = newDeadline;
    }

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

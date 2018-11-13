package cmd.mark;

import cmd.AbstractServiceCommand;
import cmd.CommandException;
import service.Service;
import service.exception.ServiceException;
import validator.exception.ValidationException;

public class AddMarkCommand extends AbstractServiceCommand {
    private int studentId, homeworkId, value;
    private boolean spare;
    private String description;

    public AddMarkCommand(Service service, String[] sep_params) {
        super(service);
        if(sep_params.length != 6)
            throw new CommandException("Invalid parameters!");

        description = sep_params[5];
        try {
            this.studentId = Integer.parseInt(sep_params[1]);
            this.homeworkId = Integer.parseInt(sep_params[2]);
            this.value = Integer.parseInt(sep_params[3]);
            this.spare = Boolean.parseBoolean(sep_params[4]);
        } catch (NumberFormatException e) {
            throw new CommandException("Invalid parameters! " + e.getMessage());
        }
    }

    @Override
    public void execute() {
        try {
            service.addMark(studentId, homeworkId, value, description, spare);
            System.out.println("Mark added!");
        } catch (ServiceException | ValidationException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

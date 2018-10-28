package cmd.homework;

import cmd.AbstractServiceCommand;
import domain.Homework;
import service.Service;

public class PrintAllHomeworkCommand extends AbstractServiceCommand {
    public PrintAllHomeworkCommand(Service service, String[] sep_params) {
        super(service);
    }

    @Override
    public void execute() {
        if(service.getAllHomework().size() == 0) {
            System.out.println("No homework!");
            return;
        }
        for(Homework homework: service.getAllHomework())
            System.out.println(homework);
    }
}

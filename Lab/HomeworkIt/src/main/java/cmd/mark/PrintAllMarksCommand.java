package cmd.mark;

import cmd.AbstractServiceCommand;
import domain.Homework;
import domain.Mark;
import service.Service;

public class PrintAllMarksCommand extends AbstractServiceCommand {
    public PrintAllMarksCommand(Service service, String[] sep_params) {
        super(service);
    }

    @Override
    public void execute() {
        if(service.getAllMarks().size() == 0) {
            System.out.println("No marks!");
            return;
        }
        for(Mark mark: service.getAllMarks())
            System.out.println(mark);
    }
}

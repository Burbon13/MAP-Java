package cmd.factory;

import cmd.Command;
import cmd.CommandException;
import cmd.homework.AddHomeworkServiceCommand;
import cmd.homework.DeleteHomeworkCommand;
import cmd.homework.ExtendDeadlineServiceCommand;
import cmd.homework.PrintAllHomeworkCommand;
import cmd.mark.AddMarkCommand;
import cmd.mark.PrintAllMarksCommand;
import cmd.student.*;
import service.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Map<String, Class<? extends Command>> commandMap; //Adds classes that extends Command

    static {
        commandMap = new HashMap<>();
        commandMap.put("add_stud", AddStudentServiceCommand.class);
        commandMap.put("del_stud", DeleteStudentServiceCommand.class);
        commandMap.put("update_stud", UpdateStudentServiceCommand.class);
        commandMap.put("get_stud", PrintStudentCommand.class);
        commandMap.put("add_prob", AddHomeworkServiceCommand.class);
        commandMap.put("extend_deadline", ExtendDeadlineServiceCommand.class);
        commandMap.put("print_students", PrintAllStudentsCommand.class);
        commandMap.put("print_homework", PrintAllHomeworkCommand.class);
        commandMap.put("del_homework", DeleteHomeworkCommand.class);
        commandMap.put("add_mark", AddMarkCommand.class);
        commandMap.put("show_marks", PrintAllMarksCommand.class);
    }

    private CommandFactory() {}

    /**
     * Returns the corresponding command class for the given string command
     * @param command the string containing the user's command
     * @param service instance for the business layer
     * @return instance of class which implements Command interface
     * @throws CommandFactoryException if the parameters are not valid
     */
    public static Command getCommand(String command, Service service) {
        //TODO: Document yourself a little bit more about this :))
        String[] cmd = Arrays.stream(command.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)")).filter(s -> s.length() > 0).toArray(String[]::new);
        for(int i = 0; i < cmd.length; i++)
            cmd[i] = cmd[i].replaceAll("\"", "");

        if(cmd.length > 0 && commandMap.containsKey(cmd[0])) {
            try {
                return commandMap.get(cmd[0]).getConstructor(Service.class, String[].class).newInstance(service, cmd);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | CommandException e) {
                throw new CommandFactoryException("Illegal parameters! " + e.getMessage());
            }
        }
        throw new CommandFactoryException("The command was not found!");
    }
}

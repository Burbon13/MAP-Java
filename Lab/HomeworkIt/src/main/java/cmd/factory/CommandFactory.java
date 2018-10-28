package cmd.factory;

import cmd.Command;
import cmd.CommandException;
import cmd.homework.AddHomeworkCommand;
import cmd.homework.ExtendDeadlineCommand;
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
        commandMap.put("add_stud", AddStudentCommand.class);
        commandMap.put("del_stud", DeleteStudentCommand.class);
        commandMap.put("update_stud", UpdateStudentCommand.class);
        commandMap.put("get_stud", PrintStudent.class);
        commandMap.put("add_prob", AddHomeworkCommand.class);
        commandMap.put("extend_deadline", ExtendDeadlineCommand.class);
        commandMap.put("print_students", PrintAllStudents.class);
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

        if(commandMap.containsKey(cmd[0])) {
            try {
                return commandMap.get(cmd[0]).getConstructor(Service.class, String[].class).newInstance(service, cmd);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | CommandException e) {
                throw new CommandFactoryException("Illegal parameters! " + e.getMessage());
            }
        }
        throw new CommandFactoryException("The command was not found!");
    }
}

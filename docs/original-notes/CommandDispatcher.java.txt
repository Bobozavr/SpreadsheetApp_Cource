package Spreadsheet;

import java.util.HashMap;
import java.util.Map;

public class CommandDispatcher {
    private Map<String, Command> commands = new HashMap<>();        //string - key(edit,open..) command - obj command(type)

    public void register(String name, Command command) {
        commands.put(name.toLowerCase(), command);
    }

    public void dispatch(String input, spreadsheet spreadsheet) {       //take line from input and obj table
        if (input == null || input.trim().isEmpty()) return;

        String[] parts = input.trim().split("\\s+");        //split command for cases
        String commandName = parts[0].toLowerCase();            //first part commandName
        String[] args = new String[parts.length - 1];           //others put to args
        System.arraycopy(parts, 1, args, 0, args.length);

        Command command = commands.get(commandName);        //search command in pull
        if (command != null) {
            command.execute(args, spreadsheet);
        } else {
            System.out.println("Unknown command: " + commandName);
        }
    }
}

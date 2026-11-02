package spreadsheet.console;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import spreadsheet.command.Command;
import spreadsheet.model.Spreadsheet;

public class CommandDispatcher {
    private final Map<String, Command> commands = new HashMap<>(); //Map("open",new open()) <>-take type from left side
    // open -> "open"
    public void register(String name, Command command) {    //name-open comand-link on created object open
        commands.put(name.toLowerCase(Locale.ROOT), command);
    }
            //dispatch ("open exm.csv", table)
    public void dispatch(String input, Spreadsheet spreadsheet) {
        if (input == null || input.isBlank()) return;
        String[] parts = input.trim().split("\\s+", 2); //split by space by 2
        //parts[0] open parts[1] exm.csv
        Command command = commands.get(parts[0].toLowerCase(Locale.ROOT));
        if (command == null) {
            System.out.println("Unknown command: " + parts[0] + ". Type help.");
            return;
        }
        // Keep the argument text intact; each command owns its syntax.
        //if after comand exist text keep to args else null massive
        //adit 3 4 30 -> 3 4 30
        String[] args = parts.length == 2 ? new String[]{parts[1]} : new String[0];
        command.execute(args, spreadsheet);
    }
}
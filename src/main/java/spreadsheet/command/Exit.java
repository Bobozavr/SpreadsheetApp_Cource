package spreadsheet.command;

import spreadsheet.model.Spreadsheet;
import spreadsheet.model.Cell;

public class Exit implements Command {
    @Override
    public void execute(String[] args, Spreadsheet spreadsheet) {
        System.out.println("Exiting program...");
        System.exit(0);
    }
}

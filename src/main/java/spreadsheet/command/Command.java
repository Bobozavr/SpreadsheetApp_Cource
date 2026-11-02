package spreadsheet.command;

import spreadsheet.model.Spreadsheet;
import spreadsheet.model.Cell;

public interface Command {
    void execute(String[] args, Spreadsheet spreadsheet);
}

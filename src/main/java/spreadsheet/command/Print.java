package spreadsheet.command;

import spreadsheet.model.Spreadsheet;
import spreadsheet.model.Cell;

public class Print implements Command {

    @Override
    public void execute(String[] args, Spreadsheet spreadsheet) {
        spreadsheet.print();
    }
}

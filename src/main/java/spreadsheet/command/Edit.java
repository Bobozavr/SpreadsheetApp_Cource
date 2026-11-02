package spreadsheet.command;

import java.util.List;
import spreadsheet.model.Cell;
import spreadsheet.model.Spreadsheet;

public class Edit implements Command {
    @Override
    public void execute(String[] args, Spreadsheet spreadsheet) {
        String[] parts = args.length == 0 ? new String[0] : args[0].split("\\s+", 3); //split for 3
        if (parts.length < 3) {
            System.out.println("Usage: edit <row> <column> <value>");
            return;
        }
        int row;
        int column;
        try {
            row = Integer.parseInt(parts[0]) - 1;
            column = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Row and column must be integers.");
            return;
        }
        if (row < 0 || row >= spreadsheet.getTable().size()) {
            System.out.println("Row out of bounds.");
            return;
        }
        List<Cell> cells = spreadsheet.getTable().get(row); //cell= link to edit
        if (column < 0 || column >= cells.size()) {
            System.out.println("Column out of bounds.");
            return;
        }
        try {
            cells.set(column, new Cell(parts[2]));
            System.out.println("Updated R" + (row + 1) + "C" + (column + 1) + ".");
        } catch (NumberFormatException e) {
            System.out.println("Numeric value is outside the supported range.");
        }
    }
}
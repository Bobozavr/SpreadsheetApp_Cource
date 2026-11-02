package spreadsheet.view;

import java.util.ArrayList;
import java.util.List;
import spreadsheet.model.Cell;
import spreadsheet.model.Spreadsheet;

/** Formats display values without changing the stored cell contents. */
public final class TablePrinter {
    private TablePrinter() {}

    public static void print(Spreadsheet spreadsheet) {
        List<List<Cell>> table = spreadsheet.getTable();  //all table-> singl row->object cell
        if (table.isEmpty()) {
            System.out.println("No table loaded. Use open <file>.");
            return;
        }
        int columns = table.stream().mapToInt(List::size).max().orElse(0); //num of col
        int[] widths = new int[columns + 1];    //for additional col of name R1
        widths[0] = Math.max(3, Integer.toString(table.size()).length() + 1);
        for (int col = 1; col <= columns; col++) widths[col] = ("C" + col).length();
        List<String[]> rows = new ArrayList<>();
        for (int row = 0; row < table.size(); row++) {
            String[] values = new String[columns + 1];
            values[0] = "R" + (row + 1);
            for (int col = 0; col < columns; col++) {
                values[col + 1] = col < table.get(row).size()
                        ? table.get(row).get(col).displayValue(spreadsheet) : "";
                widths[col + 1] = Math.max(widths[col + 1], values[col + 1].length());
            }
            rows.add(values);
        }
        String[] header = new String[columns + 1];
        header[0] = "";
        for (int col = 1; col <= columns; col++) header[col] = "C" + col;
        border(widths);
        row(header, widths);
        border(widths);
        for (String[] values : rows) row(values, widths);
        border(widths);
        System.out.println(table.size() + " rows x " + columns + " columns\n");
    }

    private static void border(int[] widths) {
        StringBuilder line = new StringBuilder("+");
        for (int width : widths) line.append("-".repeat(width + 2)).append('+');
        System.out.println(line);
    }

    private static void row(String[] values, int[] widths) {
        StringBuilder line = new StringBuilder("|");
        for (int i = 0; i < values.length; i++) {
            line.append(' ').append(values[i]).append(" ".repeat(widths[i] - values[i].length() + 1)).append('|');
        }
        System.out.println(line);
    }
}
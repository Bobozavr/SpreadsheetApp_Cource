package Spreadsheet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class spreadsheet {
    private List<List<Cell>> table = new ArrayList<>();   //double list each cell = List<Cell>, all table list of cell

    // meth for dispaly ( "2,6,\"Shura\"")
    public void loadFromLines(List<String> lines) {
        table.clear(); // we need clean if something already inside table
        for (String line : lines) {
            String[] parts = line.split(","); // split until comma
            List<Cell> row = new ArrayList<>(); // create cell for new info
            for (String part : parts) {
                row.add(new Cell(part)); // put to the cell
            }
            table.add(row); // put to table
        }
    }

    public void saveToFile(String filename) throws IOException {        // warning when we are saving
        List<String> lines = new ArrayList<>();             // create list of cell , each will be one cell CSV

        for (List<Cell> row : table) {          // table — this is List<List<Cell>>
            StringBuilder csv = new StringBuilder();        // use StringBuilder to collect cell CSV use cell line
            for (int i = 0; i < row.size(); i++) {
                csv.append(row.get(i).getRawContent()); // save original text which wrote users not like  displayValue
                if (i < row.size() - 1) csv.append(",");
            }
            lines.add(csv.toString());          //add line to list
        }

        Files.write(Paths.get(filename), lines);            //use for write to file
    }



    public void print() {
        final int rangeofcolumn = 15;
        for (List<Cell> row : table) {               // loop on all cells in table
            for (int i = 0; i < row.size(); i++) {
                String cellValue = row.get(i).displayValue(); //return cell with index i  after making display depends of type
                System.out.print(String.format("%-" + rangeofcolumn + "s", cellValue));// using format for cell //"-" format left side

                if (i < row.size() - 1) System.out.print(" | "); //  after each cell use | but not at the end => i < row.size() - 1
            }
            System.out.println();
        }
    }

    //  return alll table
    public List<List<Cell>> getTable() {
        return table;
    }
}

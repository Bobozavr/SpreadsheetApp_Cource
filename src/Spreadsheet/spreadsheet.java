package Spreadsheet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class spreadsheet {
    private List<List<Cell>> table = new ArrayList<>();   //one row which has some count of cell = List<Cell>, all table List<List<Cell>>

    // meth for dispaly ( "2,6,\"Shura\"")
    public void loadFromLines(List<String> lines) {
        table.clear(); // we need clean if something already inside table
        for (String line : lines) {
            String[] parts = line.split(","); // split until comma each row
            List<Cell> row = new ArrayList<>(); // create list for save cell this row
            for (String part : parts) {
                row.add(new Cell(part)); // put to the cell to Cell contractor
            }
            table.add(row); // put to table
        }
    }

    public void saveToFile(String filename) throws IOException {        // warning when we are saving
        List<String> lines = new ArrayList<>();             // create list of line , each will be one lineCSV

        for (List<Cell> row : table) {          // table — this is List<List<Cell>>  row - List<Cell>
            StringBuilder csv = new StringBuilder();        // use StringBuilder to collect cell CSV use cell line
            for (int i = 0; i < row.size(); i++) {
                csv.append(row.get(i).getRawContent()); // save original in getRawContent
                if (i < row.size() - 1) csv.append(","); //put comma after this
            }
            lines.add(csv.toString());          //add line to list
        }

        Files.write(Paths.get(filename), lines);            //Paths.get(filename) - path  Files.write-writes all lines to a file.
    }



    public void print() {
        for (List<Cell> row : table) {  //by each line
            for (int i = 0; i < row.size(); i++) {      //by each cell in line
                String cellValue = row.get(i).displayValue(this); // write some thing but if formula use displayValue(
                System.out.print(String.format("%-13s", cellValue)); // 13
                if (i < row.size() - 1) System.out.print("|");
            }
            System.out.println();
        }
    }

    //  return alll table
    public List<List<Cell>> getTable() {
        return table;
    }

    public Cell getCell(int row, int col) {     //return cell by cordinates
        if (row >= 0 && row < table.size()) { //checking out of bounce firrs row after cell
            List<Cell> targetRow = table.get(row);
            if (col >= 0 && col < targetRow.size()) {
                return targetRow.get(col);
            }
        }
        //  if there is no  cell  we will return empty
        return new Cell("");
    }

}

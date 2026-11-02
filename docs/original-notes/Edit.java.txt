package Spreadsheet;

import java.util.List;

public class Edit implements Command {

    @Override
    public void execute(String[] args, spreadsheet spreadsheet) {
        if (args.length < 3) {      //if less than 3 argo
            System.out.println("Usage: edit <row> <col> <value>");
            return;
        }

        try {
            int row = Integer.parseInt(args[0]) - 1;        //pars string to int  //bcs index from 0
            int col = Integer.parseInt(args[1]) - 1;
            String newValue = args[2];                          //new value to put

            if (row < 0 || row >= spreadsheet.getTable().size()) {          //the number rows of the table are not in the range of table
                System.out.println("Row out of bounds.");
                return;
            }

            List<Cell> targetRow = spreadsheet.getTable().get(row);         //we take line by cordi return list of cells by index to save targetRow to not get mistake "IndexOutOfBoundsException"
            if (col < 0 || col >= targetRow.size()) {
                System.out.println("Column out of bounds.");
                return;
            }

            targetRow.set(col, new Cell(newValue));     //update cell, creating new obj CELL with new value
            System.out.println("Cell updated.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid row/column number. Please use integers.");
        }
    }
}

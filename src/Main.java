import Spreadsheet.spreadsheet;
import java.io.IOException; // for try catch
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import Spreadsheet.CellType;
import Spreadsheet.Cell;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to SpreadsheetApp!");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        spreadsheet spreadsheet = new spreadsheet();

        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();  //waiting and cleaning spaces
            String[] parts = input.split("\\s+", 2); //split for 2 cases our input: info and arguments
            String command = parts[0].toLowerCase(); // use first case and small letters
            String argument = parts.length > 1 ? parts[1] : ""; //checking second case is it empty or no

            switch (command) {
                case "help":
                    System.out.println("Available commands:");
                    System.out.println("open <file>");
                    System.out.println("close");
                    System.out.println("save");
                    System.out.println("save as <file>");
                    System.out.println("help");
                    System.out.println("exit");
                    break;
                case "open":
                    if (argument.isEmpty()) {   // did we write file name correct ?
                        System.out.println("Please provide a file name.");
                        break;
                    }
                    try {
                        List<String> lines = Files.readAllLines(Paths.get(argument)); //try catch info from file, create road to file
                        spreadsheet.loadFromLines(lines); // Use methods split cell on comma create cell ,
                        System.out.println("File loaded successfully:");
                        spreadsheet.print();
                    } catch (IOException Er) {   //if error
                        System.out.println("Error reading file: " + Er.getMessage());
                    }
                    break;
                case "print":
                    spreadsheet.print(); //print our table
                    break;
                case "edit":
                    String[] Egitargs = argument.split("\\s+", 3);   //split for 3 cases our input
                    if (Egitargs.length < 3) {
                        System.out.println("Usage: edit <row> <col> <value>");
                        break;
                    }
                    try {
                        int row = Integer.parseInt(Egitargs[0]) - 1;        // index start from 0 bcs we  subtract
                        int col = Integer.parseInt(Egitargs[1]) - 1;
                        String newValue = Egitargs[2];      // save new value
                        if (row < 0 || row >= spreadsheet.getTable().size()) {          //the number rows of the table are not in the range of table
                            System.out.println("Row out of bounds.");
                            break;
                        }
                        List<Cell> targetRow = spreadsheet.getTable().get(row);        // same for row like for cell
                        if (col < 0 || col >= targetRow.size()) {
                            System.out.println("Column out of bounds.");
                            break;
                        }

                        targetRow.set(col, new Cell(newValue));         //save to TargetRow update cell with new value
                        System.out.println("Cell updated.");
                    } catch (NumberFormatException e) {             //if error
                        System.out.println("Invalid row/column number.  Please ensure the row and column are integers");

                    }
                    break;
                case "save":
                    if (argument.isEmpty()) {               //checking
                        System.out.println("Please provide a file name.");
                        break;
                    }
                    try {
                        spreadsheet.saveToFile(argument);
                        System.out.println("File saved successfully.");
                    } catch (IOException Er) {
                        System.out.println("Error saving file: " + Er.getMessage());
                    }
                    break;
                case "saveas":              // when we want save to new file we use argument for new file
                    if (argument.isEmpty()) {
                        System.out.println("Please provide a new file name.");
                        break;
                    }
                    try {
                        spreadsheet.saveToFile(argument);
                        System.out.println("File saved as: " + argument);
                    } catch (IOException e) {
                        System.out.println("Error saving file: " + e.getMessage());
                    }
                    break;
                case "exit":
                    System.out.println("Exiting the program...");
                    running = false;
                    break;
                default:
                    System.out.println("Unknown command. Type 'help' for available commands.");
            }
        }

        scanner.close();
    }
    private static void printHelp() {
        System.out.println("Available commands:");
        System.out.println("open <filename> - Load a spreadsheet from a file");
        System.out.println("exit            - Exit the application");
        System.out.println("help            - Show this help message");
    }

}

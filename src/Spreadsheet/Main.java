package Spreadsheet;

import java.io.IOException; // for try catch
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        spreadsheet spreadsheet = new spreadsheet();            // obj table for command
        CommandDispatcher dispatcher = new CommandDispatcher();     //for detection comm

        System.out.println("Welcome to SpreadsheetApp!");
        dispatcher.register("help", new Help());
        dispatcher.register("edit", new Edit());
        dispatcher.register("open", new Open());
        dispatcher.register("close", new Close());
        dispatcher.register("print", new Print());
        dispatcher.register("save", new Save());
        dispatcher.register("saveas", new SaveAs());
        dispatcher.register("exit", new Exit());


        Scanner scanner = new Scanner(System.in);       // for read users input
        System.out.println("Write command (help - to see all commands):");


        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                continue;
            }

            dispatcher.dispatch(input, spreadsheet);            //call comm
        }

    }

}
















//        Scanner scanner = new Scanner(System.in);
//        boolean running = true;
//        spreadsheet spreadsheet = new spreadsheet();
//
//        while (running) {
//            System.out.print("> ");
//            String input = scanner.nextLine().trim();  //waiting and cleaning spaces
//            String[] parts = input.split("\\s+", 2); //split for 2 cases our input: info and arguments
//            String command = parts[0].toLowerCase(); // use first case and small letters
//            String argument = parts.length > 1 ? parts[1] : ""; //checking second case is it empty or its command
//
//            switch (command) {
//                case "help":
//                    System.out.println("Available commands:");
//                    System.out.println("open <file>");
//                    System.out.println("help");
//                    System.out.println("close");
//                    System.out.println("edit");
//                    System.out.println("print");
//                    System.out.println("save");
//                    System.out.println("save as <file>");
//                    System.out.println("exit");
//                    break;
//                case "open":
//                    if (argument.isEmpty()) {   // did we write file name correct ?
//                        System.out.println("Please provide a file name.");
//                        break;
//                    }
//                    try {
//                        List<String> lines = Files.readAllLines(Paths.get(argument)); //try catch info from file, create road to file
//                        spreadsheet.loadFromLines(lines); // Use methods split cell on comma create cell ,
//                        System.out.println("File loaded successfully:");
//                        spreadsheet.print();
//                    } catch (IOException Er) {   //if error
//                        System.out.println("Error reading file: " + Er.getMessage());
//                    }
//                    break;
//                case "close":
//                    System.out.print("Are you sure you want to close the current table without saving? (yes/no): ");
//                    String confirm = scanner.nextLine().trim().toLowerCase();
//                    if (confirm.equals("yes")) {
//                        spreadsheet = new spreadsheet();  //clean hole table to create new
//                        System.out.println("Table closed.");
//                    } else {
//                        System.out.println("Close cancelled.");
//                    }
//                    break;
//                case "print":
//                    spreadsheet.print(); //print our table
//                    break;
//                case "edit":
//                    String[] Egitargs = argument.split("\\s+", 3);   //split for 3 cases our input
//                    if (Egitargs.length < 3) {
//                        System.out.println("Usage: edit <row> <col> <value>");
//                        break;
//                    }
//                    try {
//                        int row = Integer.parseInt(Egitargs[0]) - 1;        // index start from 0 bcs we  subtract
//                        int col = Integer.parseInt(Egitargs[1]) - 1;
//                        String newValue = Egitargs[2];      // save new value
//                        if (row < 0 || row >= spreadsheet.getTable().size()) {          //the number rows of the table are not in the range of table
//                            System.out.println("Row out of bounds.");
//                            break;
//                        }
//                        List<Cell> NewRow = spreadsheet.getTable().get(row);        // same for row like for cell
//                        if (col < 0 || col >= NewRow.size()) {
//                            System.out.println("Column out of bounds.");
//                            break;
//                        }
//
//                        NewRow.set(col, new Cell(newValue));         //save to NewRow update cell with new value
//                        System.out.println("Cell updated.");
//                    } catch (NumberFormatException e) {             //if error
//                        System.out.println("Invalid row/column number.  Please ensure the row and column are integers");
//
//                    }
//                    break;
//                case "save":
//                    if (argument.isEmpty()) {               //checking
//                        System.out.println("Please provide a file name.");
//                        break;
//                    }
//                    try {
//                        spreadsheet.saveToFile(argument);
//                        System.out.println("File saved successfully.");
//                    } catch (IOException e) {
//                        System.out.println("Error saving file: " + e.getMessage());
//                    }
//                    break;
//                case "saveas":              // when we want save to new file we use argument for new file
//                    if (argument.isEmpty()) {
//                        System.out.println("Please provide a new file name.");
//                        break;
//                    }
//                    try {
//                        spreadsheet.saveToFile(argument);
//                        System.out.println("File saved as: " + argument);
//                    } catch (IOException e) {
//                        System.out.println("Error saving file: " + e.getMessage());
//                    }
//                    break;
//                case "exit":
//                    System.out.println("Exiting the program...");
//                    running = false;
//                    break;
//                default:
//                    System.out.println("Unknown command. Type 'help' for available commands.");
//            }
//        }
//
//        scanner.close();


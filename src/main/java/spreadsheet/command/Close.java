package spreadsheet.command;

import java.util.Scanner;
import spreadsheet.model.Spreadsheet;

public class Close implements Command {
    private final Scanner scanner;     //object pool save in memory

    public Close(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void execute(String[] args, Spreadsheet spreadsheet) {  //argument  table for clear
        System.out.print("Close without saving? (yes/no): ");
        if (scanner.hasNextLine() && scanner.nextLine().trim().equalsIgnoreCase("yes")) {
            spreadsheet.clear();
            System.out.println("Table closed.");
        } else {
            System.out.println("Close cancelled.");
        }
    }
}
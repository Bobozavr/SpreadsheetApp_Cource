package Spreadsheet;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;

public class Open implements Command {

    @Override
    public void execute(String[] args, spreadsheet spreadsheet) {
        if (args.length < 1 || args[0].isEmpty()) {
            System.out.println("Please provide a file name.");
            return;
        }

        String filename = args[0];      //take name from arg

        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));       // Paths.get(filename)- path to file // reaad all by lines
            spreadsheet.loadFromLines(lines);       //from file to table
            System.out.println("File loaded successfully:");
            spreadsheet.print();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

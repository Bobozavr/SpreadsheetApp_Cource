package Spreadsheet;

import java.io.IOException;

public class Save implements Command {
    @Override
    public void execute(String[] args, spreadsheet spreadsheet) {
        if (args.length < 1) {
            System.out.println("Usage: save <filename>");
            return;
        }

        String filename = args[0];
        try {
            spreadsheet.saveToFile(filename);
            System.out.println("File saved successfully as: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}

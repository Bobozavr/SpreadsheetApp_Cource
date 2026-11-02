package spreadsheet.command;

import spreadsheet.model.Spreadsheet;
import spreadsheet.model.Cell;

import java.io.IOException;

public class Save implements Command {
    @Override
    public void execute(String[] args, Spreadsheet spreadsheet) {
        if (args.length < 1) {
            System.out.println("Usage: save <filename>");
            return;
        }

        String filename = FileArgument.parse(args[0]);
        try {
            spreadsheet.saveToFile(filename);
            System.out.println("File saved successfully as: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}

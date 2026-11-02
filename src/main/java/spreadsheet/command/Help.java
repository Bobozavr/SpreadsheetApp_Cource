package spreadsheet.command;

import spreadsheet.model.Spreadsheet;

public class Help implements Command {
    @Override
    public void execute(String[] args, Spreadsheet spreadsheet) {
        System.out.println("\n  COMMANDS");
        System.out.printf("  %-30s %s%n", "open <file>", "Load and display a CSV table");
        System.out.printf("  %-30s %s%n", "print", "Display rows and columns");
        System.out.printf("  %-30s %s%n", "edit <row> <column> <value>", "Replace an existing cell (numbers start at 1)");
        System.out.printf("  %-30s %s%n", "save <file>", "Save the table to a file");
        System.out.printf("  %-30s %s%n", "saveas <file>", "Save the table under another name");
        System.out.printf("  %-30s %s%n", "close", "Clear the table after confirmation");
        System.out.printf("  %-30s %s%n", "help", "Show this help");
        System.out.printf("  %-30s %s%n", "exit", "Exit without automatic saving");
        System.out.println("\n  Examples: edit 1 2 42 | edit 1 3 \"Hello world\" | edit 1 4 =R1C1 + R1C2\n");
    }
}
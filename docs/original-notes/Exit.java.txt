package Spreadsheet;

public class Exit implements Command {
    @Override
    public void execute(String[] args, spreadsheet spreadsheet) {
        System.out.println("Exiting program...");
        System.exit(0);
    }
}

package Spreadsheet;

public class Help implements Command {
    @Override
    public void execute(String[] args, spreadsheet spreadsheet) {
        System.out.println("Available commands:");
        System.out.println(" open <filename> ");
        System.out.println(" close ");
        System.out.println(" print ");
        System.out.println(" edit <row> <column> <value> ");
        System.out.println(" save  ");
        System.out.println(" saveas <filename> ");
        System.out.println(" exit  ");
        System.out.println(" help  ");
    }
}

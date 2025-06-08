package Spreadsheet;

public class Print implements Command {

    @Override
    public void execute(String[] args, spreadsheet spreadsheet) {
        spreadsheet.print();
    }
}

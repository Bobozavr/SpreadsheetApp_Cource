package Spreadsheet;

public class Close implements Command {
    @Override
    public void execute(String[] args, spreadsheet spreadsheet) {
        System.out.print("Are you sure you want to close the current table without saving? (yes/no): ");
        java.util.Scanner scanner = new java.util.Scanner(System.in); //Obj for read from keyboard
        String confirm = scanner.nextLine().trim().toLowerCase(); //read from keyboard etc
        if ("yes".equals(confirm)) {
            spreadsheet.clear(); // cleaning
            System.out.println("Table closed.");
        } else {
            System.out.println("Close cancelled.");
        }
    }
}

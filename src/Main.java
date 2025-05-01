import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to SpreadsheetApp!");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.print("> ");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "help":
                    System.out.println("Available commands:");
                    System.out.println("open <file>");
                    System.out.println("close");
                    System.out.println("save");
                    System.out.println("save as <file>");
                    System.out.println("help");
                    System.out.println("exit");
                    break;
                case "exit":
                    System.out.println("Exiting the program...");
                    running = false;
                    break;
                default:
                    System.out.println("Unknown command. Type 'help' for available commands.");
            }
        }

        scanner.close();
    }
}

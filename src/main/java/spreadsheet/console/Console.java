package spreadsheet.console;

import java.util.Scanner;   //ready class for read
import spreadsheet.command.*;
import spreadsheet.model.Spreadsheet;

/** Reads input; command selection belongs to the dispatcher. */
public class Console {
    public void run() {
        Spreadsheet spreadsheet = new Spreadsheet();    //create table in memory
        try (Scanner scanner = new Scanner(System.in)) {    //sys in - get from keyboard
            CommandDispatcher dispatcher = new CommandDispatcher();
            dispatcher.register("help", new Help());
            dispatcher.register("open", new Open());
            dispatcher.register("close", new Close(scanner));
            dispatcher.register("print", new Print());
            dispatcher.register("edit", new Edit());
            dispatcher.register("save", new Save());
            dispatcher.register("saveas", new SaveAs());
            dispatcher.register("exit", new Exit());

            System.out.println("\n  SPREADSHEET APP");
            System.out.println("  Tables, cells and simple formulas");
            System.out.println("  Type help for commands. Start with: open examples/test.csv\n");
            while (true) {
                System.out.print("sheet> ");
                if (!scanner.hasNextLine()) {
                    System.out.println();
                    break;
                }
                dispatcher.dispatch(scanner.nextLine(), spreadsheet);
            }
        }
    }
}
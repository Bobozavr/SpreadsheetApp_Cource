package Spreadsheet;

import Spreadsheet.spreadsheet;

public interface Command {
    void execute(String[] args, spreadsheet spreadsheet);
}

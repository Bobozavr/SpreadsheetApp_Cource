package Spreadsheet;

public class Cell {
    private CellType type;
    private String rawContent;
    private Object value;           //for pars value

    public Cell(String rawContent) {                    //
        this.rawContent = rawContent.trim();
        this.type = determineType(this.rawContent);
        this.value = parsingValue();
    }

    private CellType determineType(String rawContent) {
        if (rawContent.isEmpty()) return CellType.EMPTY;
        if (rawContent.startsWith("=")) return CellType.FORMULA;
        if (rawContent.matches("[-+]?\\d+")) return CellType.INTEGER;
        if (rawContent.matches("[-+]?\\d+\\.\\d+")) return CellType.DOUBLE;
        if (rawContent.matches("\".*\"")) return CellType.STRING;
        return CellType.ERROR;
    }

    private Object parsingValue() {
        switch (type) {
            case INTEGER:
                return Integer.parseInt(rawContent);
            case DOUBLE:
                return Double.parseDouble(rawContent);
            case STRING:
                return rawContent.substring(1, rawContent.length() - 1);
            case FORMULA:
                return "FORMULA";                   // to  displayValue()
            default:
                return null;
        }
    }

    public String displayValue(spreadsheet forming) {               //call to table
        if (type == CellType.EMPTY) return "";
        if (type == CellType.ERROR) return "ERROR";
        if (type == CellType.FORMULA) {
            Object result = evaluateFormula(rawContent, forming);           //using data from table to calculate ,for example if is it null or error
            return result != null ? result.toString() : "ERROR";
        }
        return value != null ? value.toString() : "";
    }

    public CellType getType() {
        return type;
    }

    public String getRawContent() {                 //from output without pars
        return rawContent;
    }

    public Object getValue() {              //with pars
        return value;
    }


    private Object evaluateFormula(String formula, spreadsheet forming) {
        try {
            formula = formula.substring(1); // deleting first symbol '='
            String[] cases = formula.split(" "); // split for 3 cases
            if (cases.length != 3) return "ERROR";

            double val1 = getCellValueFromcase(cases[0], forming);      //gets first case by link in cell  and get val
            String operator = cases[1];         //our operators
            double val2 = getCellValueFromcase(cases[2], forming);

            switch (operator) {
                case "+":
                    return val1 + val2;
                case "-":
                    return val1 - val2;
                case "*":
                    return val1 * val2;
                case "/":
                    return (val2 == 0) ? 0 : val1 / val2;
                default:
                    return "ERROR";
            }
        } catch (Exception e) {
            return "ERROR";
        }
    }

    private double getCellValueFromcase(String reference, spreadsheet forming) throws Exception {
        if (!reference.matches("R\\d+C\\d+")) {                 //reference - string that have a coordinates of the cell, start R?C?
            throw new Exception("Invalid cell reference: " + reference);
        }

        int row = Integer.parseInt(reference.substring(1, reference.indexOf('C'))) - 1;         //take  num between R and C(cut index from 1 to C = between)    // and -1 bcs index start from 0
        int col = Integer.parseInt(reference.substring(reference.indexOf('C') + 1)) - 1;    //take num after C  // and -1 bcs index start from 0

        Cell cell = forming.getCell(row, col);          //this row use  methods  which gets value from cells which we veed for calculate =R1C1+R2C2
        if (cell == null || cell.getType() == CellType.EMPTY) {
            return 0;
        }

        Object value = cell.getValue();         //Int,Double,String or null
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }

        if (value instanceof String) {
            try {
                return Double.parseDouble((String) value);  //if string we convert to 0 to catch 0 in res for formul
            } catch (NumberFormatException e) {
                return 0;
            }
        }

        return 0;
    }
}

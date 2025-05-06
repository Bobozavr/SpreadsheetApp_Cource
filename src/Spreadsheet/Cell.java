package Spreadsheet;

public class Cell {
    private CellType type;
    private String rawContent;
    private Object value;

    public Cell(String rawContent) {
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
            default:
                return null;
        }
    }

    public String displayValue() {
        if (type == CellType.EMPTY) return "";
        if (type == CellType.ERROR) return "ERROR";
        if (value != null) {
            return value.toString();
        } else {
            return "";
        }
    }

    public CellType getType() {
        return type;
    }

    public String getRawContent() {
        return rawContent;
    }

    public Object getValue() {
        return value;
    }
}

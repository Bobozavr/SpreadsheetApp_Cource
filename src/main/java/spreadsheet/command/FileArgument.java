package spreadsheet.command;

/** Removes optional outer quotes, preserving spaces and Windows backslashes. */
final class FileArgument {
    private FileArgument() {}

    static String parse(String argument) {
        String path = argument.trim();
        if (path.length() >= 2 && path.startsWith("\"") && path.endsWith("\"")) {
            return path.substring(1, path.length() - 1);
        }
        return path;
    }
}
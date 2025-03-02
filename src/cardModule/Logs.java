package cardModule;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Logs {
    private static ArrayList<String> logHistory = new ArrayList<>();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static void logUpdate(String text) {
        String timestamp = dateFormat.format(new Date());
        logHistory.add(String.format("[%s] %s", timestamp, text));
    }

    public static ArrayList<String> getLogs() {
        return logHistory;
    }

    public static void clearLogs() {
        logHistory.clear();
    }
} 
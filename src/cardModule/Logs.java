package cardModule;

import java.util.ArrayList;

public class Logs {
    private static LogStrategy logStrategy = new InMemoryLogStrategy();

    public static void setLogStrategy(LogStrategy strategy) {
        logStrategy = strategy;
    }

    public static void logUpdate(String text) {
        logStrategy.log(text);
    }

    public static ArrayList<String> getLogs() {
        return logStrategy.getLogs();
    }

    public static void clearLogs() {
        logStrategy.clearLogs();
    }
}
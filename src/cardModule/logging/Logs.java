package cardModule.logging;

import java.util.ArrayList;

public class Logs {
    private static Logs instance;
    private LogStrategy logStrategy;

    private Logs() {
        this.logStrategy = new InMemoryLogStrategy();
    }

    public static Logs getInstance() {
        if (instance == null) {
            instance = new Logs();
        }
        return instance;
    }

    public static void logUpdate(String text) {
        getInstance().logStrategy.log(text);
    }

    public static ArrayList<String> getLogs() {
        return getInstance().logStrategy.getLogs();
    }

    public static void clearLogs() {
        getInstance().logStrategy.clearLogs();
    }
} 
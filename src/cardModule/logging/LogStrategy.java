package cardModule.logging;

import java.util.ArrayList;

public interface LogStrategy {
    void log(String text);
    ArrayList<String> getLogs();
    void clearLogs();
} 
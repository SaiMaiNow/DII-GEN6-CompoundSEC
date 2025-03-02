package cardModule;

import java.util.ArrayList;

public interface LogStrategy {
    void log(String message);
    ArrayList<String> getLogs();
    void clearLogs();
} 
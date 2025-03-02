package cardModule;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class InMemoryLogStrategy implements LogStrategy {
    private ArrayList<String> logHistory;
    private SimpleDateFormat dateFormat;

    public InMemoryLogStrategy() {
        this.logHistory = new ArrayList<>();
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }

    @Override
    public void log(String message) {
        String timestamp = dateFormat.format(new Date());
        logHistory.add(String.format("[%s] %s", timestamp, message));
    }

    @Override
    public ArrayList<String> getLogs() {
        return logHistory;
    }

    @Override
    public void clearLogs() {
        logHistory.clear();
    }
} 
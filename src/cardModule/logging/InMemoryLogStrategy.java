package cardModule.logging;

import java.util.ArrayList;

public class InMemoryLogStrategy implements LogStrategy {
    private ArrayList<String> logs = new ArrayList<>();

    @Override
    public void log(String text) {
        logs.add(text);
    }

    @Override
    public ArrayList<String> getLogs() {
        return new ArrayList<>(logs);
    }

    @Override
    public void clearLogs() {
        logs.clear();
    }
} 
import cardModule.management.CardManagement;
import guiModule.GUIManager;
import guiModule.GUIClient;
public class Main {
    public static void main(String[] args) {
        CardManagement cardManagement = CardManagement.getInstance();
        new GUIManager(cardManagement, new GUIClient(cardManagement));
    }
}
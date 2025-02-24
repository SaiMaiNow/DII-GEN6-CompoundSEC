import guiModule.GUIManager;
import guiModule.GUIClient;
import cardModule.CardManagement;
public class Main {
    public static void main(String[] args) {
        CardManagement cardManagement = new CardManagement();
        new GUIManager(cardManagement, new GUIClient(cardManagement));
    }
}
import guiModule.GUIManager;
import guiModule.GUICilent;
import cardModule.CardManagement;
public class Main {
    public static void main(String[] args) {
        CardManagement cardManagement = new CardManagement();
        new GUIManager(cardManagement, new GUICilent(cardManagement));
    }
}
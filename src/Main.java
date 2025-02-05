import cardModel.CardAccess;
import cardModel.CardManagement;

public class Main {
    public static void main(String[] args) {
        CardManagement cardManagement = new CardManagement();

        CardAccess cardAccess = new CardAccess();
        cardManagement.addCard(cardAccess);
    }
}
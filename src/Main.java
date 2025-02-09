import cardModel.CardManagement;
import cardModel.CardAccess;

public class Main {
    public static void main(String[] args) {
        CardManagement cardManagement = new CardManagement();
        CardAccess card1 = new CardAccess("LOW");
        System.out.println(card1.generateCardId());

        cardManagement.addCard(card1);
        System.out.println(cardManagement.getCard(card1.getCardNumber()));
    }
}
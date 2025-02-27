package cardModule;

import java.util.ArrayList;
import java.util.Date;
public class CardManagement implements CardManagementInterface {
    private ArrayList<CardAccess> cardList;

    public CardManagement() {
        this.cardList = new ArrayList<>();
    }

    public boolean addCard(CardAccess card) {
        this.cardList.add(card);
        return true;
    }

    public boolean modifyCard(String cardId, ArrayList<String> newLevel, Date newExpiryDate) { 
        CardAccess card = getCard(cardId);
        if (card == null) {
            System.out.println("Card not found");
            return false;
        }

        if (newLevel == null) {
            newLevel = new ArrayList<>();
        }

        card.setCardLevel(newLevel);
        card.setExpiryDate(newExpiryDate);
        return true;
    }

    public boolean revokeCard(String cardId) {
        CardAccess card = getCard(cardId);
        if (card == null) {
            System.out.println("Card not found");
            return false;
        }

        this.cardList.remove(card);
        return true;
    }

    public CardAccess getCard(String cardId) {
        if (cardId == null || cardId.isEmpty()) {
            return null;
        }
        
        return this.cardList.stream().filter(card -> card.getCardNumber().equals(cardId)).findFirst().orElse(null);
    }

    public ArrayList<CardAccess> getCards() {
        return this.cardList;
    }
}

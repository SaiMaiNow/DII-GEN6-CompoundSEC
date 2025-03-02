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
        Logs.logUpdate("Card added: " + card.getCardNumber());
        return true;
    }

    public boolean modifyCard(String cardId, ArrayList<String> newLevel, Date newExpiryDate) { 
        CardAccess card = getCard(cardId);
        if (card == null) {
            Logs.logUpdate("Failed to modify card: Card not found - " + cardId);
            return false;
        }

        if (newLevel == null) {
            newLevel = new ArrayList<>();
        }

        card.setCardLevel(newLevel);
        card.setExpiryDate(newExpiryDate);
        Logs.logUpdate("Card modified: " + cardId + " - New levels: " + newLevel);
        return true;
    }

    public boolean revokeCard(String cardId) {
        CardAccess card = getCard(cardId);
        if (card == null) {
            Logs.logUpdate("Failed to revoke card: Card not found - " + cardId);
            return false;
        }

        this.cardList.remove(card);
        Logs.logUpdate("Card revoked: " + cardId);
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

package cardModule.management;

import java.util.ArrayList;
import java.util.Date;
import cardModule.card.CardAccess;
import cardModule.card.CardManagementInterface;
import cardModule.logging.Logs;

public class CardManagement implements CardManagementInterface {
    private static CardManagement instance;
    private ArrayList<CardAccess> cardList;

    private CardManagement() {
        this.cardList = new ArrayList<>();
    }

    public static CardManagement getInstance() {
        if (instance == null) {
            instance = new CardManagement();
        }
        return instance;
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

        ArrayList<String> oldLevel = card.getCardPermission();
        Date oldExpiryDate = card.getExpiryDate();

        card.setCardLevel(newLevel);
        card.setExpiryDate(newExpiryDate);

        if (!newLevel.equals(oldLevel)) {
            Logs.logUpdate("Card modified: " + cardId + " - New levels: " + newLevel);
        }

        if (!newExpiryDate.equals(oldExpiryDate)) {
            Logs.logUpdate("Card modified: " + cardId + " - New expiry date: " + newExpiryDate);
        }

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

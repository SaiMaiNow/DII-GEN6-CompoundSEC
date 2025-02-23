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
        
        String decryptedCardId = decryptData(cardId);
        return this.cardList.stream().filter(card -> decryptData(card.getCardNumber()).equals(decryptedCardId)).findFirst().orElse(null);
    }
    
    public String decryptData(String data) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : data.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                decrypted.append((char) (((c - base + 23) % 26) + base));
            } else if (Character.isDigit(c)) {
                decrypted.append((char) (((c - '0' + 7) % 10) + '0'));
            } else {
                decrypted.append(c);
            }
        }
        return decrypted.toString();
    }
}

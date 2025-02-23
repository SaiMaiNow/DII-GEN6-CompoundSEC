package cardModule;

import java.util.ArrayList;

public class CardManagement implements CardManagementInterface {
    private ArrayList<CardAccess> cardList;

    public CardManagement() {
        this.cardList = new ArrayList<>();
    }

    public void addCard(CardAccess card) {
        this.cardList.add(card);
    }

    public void modifyCard(String cardId, String newLevel) { 
        CardAccess card = getCard(cardId);
        if (card == null) {
            System.out.println("Card not found");
            return;
        }

        card.setCardLevel(newLevel);
    }

    public void revokeCard(String cardId) {
        CardAccess card = getCard(cardId);
        if (card == null) {
            System.out.println("Card not found");
            return;
        }

        this.cardList.remove(card);
    }

    public CardAccess getCard(String cardId) {
        return this.cardList.stream().filter(card -> card.getCardNumber().equals(cardId)).findFirst().orElse(null);
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

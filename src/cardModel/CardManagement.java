package cardModel;

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

    }

    public void revokeCard(String cardId) {
        
    }

    public CardAccess getCard(String cardId) {
        return this.cardList.stream().filter(card -> card.getCardNumber().equals(cardId)).findFirst().orElse(null);
    }
}

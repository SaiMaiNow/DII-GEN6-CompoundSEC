package cardModule;

import java.util.ArrayList;

public class CardAccess extends CardAccessAbstract {
    private String cardId;
    private ArrayList<String> cardLevel;

    public CardAccess(ArrayList<String> cardLevel) {
        this.cardId = generateCardId();
        this.cardLevel = cardLevel;
    }

    @Override
    public String getCardNumber() {
        return this.cardId;
    }

    @Override
    public ArrayList<String> getCardPermission() {
        return this.cardLevel;
    }

    @Override
    public void setCardLevel(String newLevel) {
        this.cardLevel.add(newLevel);
    }
}
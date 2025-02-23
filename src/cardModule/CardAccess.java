package cardModule;

import java.util.ArrayList;
import java.util.Date;
public class CardAccess extends CardAccessAbstract {
    private String cardId;
    private ArrayList<String> cardLevel;
    private Date expiryDate;

    public CardAccess(ArrayList<String> cardLevel, Date expiryDate) {
        this.cardId = generateCardId();
        this.cardLevel = cardLevel;
        this.expiryDate = expiryDate;
    }

    @Override
    public String getCardNumber() {
        return encryptData(this.cardId);
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
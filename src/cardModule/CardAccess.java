package cardModule;

import java.util.ArrayList;
import java.util.Date;
public class CardAccess extends CardAccessAbstract {
    private final String cardId;
    private ArrayList<String> cardLevel;
    private Date expiryDate;
    private final String cardNumber;

    public CardAccess(ArrayList<String> cardLevel, Date expiryDate) {
        this.cardId = generateCardId();
        this.cardNumber = encryptData(this.cardId);
        this.cardLevel = cardLevel;
        this.expiryDate = expiryDate;
        System.out.println(this.cardNumber);
    }

    @Override
    public String getCardNumber() {
        return this.cardNumber;
    }

    @Override
    public ArrayList<String> getCardPermission() {
        return this.cardLevel;
    }

    @Override
    public void setCardLevel(ArrayList<String> newLevel) {
        this.cardLevel = newLevel;
    }

    @Override
    public Date getExpiryDate() {
        return this.expiryDate;
    }

    @Override
    public void setExpiryDate(Date newExpiryDate) {
        this.expiryDate = newExpiryDate;
    }
}
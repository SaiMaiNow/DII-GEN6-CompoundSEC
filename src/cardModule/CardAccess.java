package cardModule;

import java.util.ArrayList;
import java.util.Date;
public class CardAccess extends CardAccessAbstract {
    private final String cardNumber;
    private ArrayList<String> cardLevel;
    private Date expiryDate;

    public CardAccess(ArrayList<String> cardLevel, Date expiryDate) {
        this.cardNumber = encryptData(generateCardId());
        this.cardLevel = cardLevel;
        this.expiryDate = expiryDate;
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
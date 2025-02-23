package cardModule;

import java.util.Date;
import java.util.ArrayList;
public interface CardManagementInterface {
    boolean addCard(CardAccess card);
    boolean modifyCard(String cardId, ArrayList<String> newLevel, Date newExpiryDate);
    boolean revokeCard(String cardId);
    CardAccess getCard(String cardId);
    String decryptData(String data);
}
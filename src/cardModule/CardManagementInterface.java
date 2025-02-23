package cardModule;

public interface CardManagementInterface {
    boolean addCard(CardAccess card);
    boolean modifyCard(String cardId, String newLevel);
    boolean revokeCard(String cardId);
    CardAccess getCard(String cardId);
    String decryptData(String data);
}
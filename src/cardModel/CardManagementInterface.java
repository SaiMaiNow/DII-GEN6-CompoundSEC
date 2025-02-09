package cardModel;

public interface CardManagementInterface {
    void addCard(CardAccess card);
    void modifyCard(String cardId, String newLevel);
    void revokeCard(String cardId);
    CardAccess getCard(String cardId);
}
package cardModule.room;

import cardModule.management.CardManagement;
import cardModule.card.CardAccess;

public class RoomAccessImpl implements RoomAccess {
    private CardManagement cardManagement;

    public RoomAccessImpl(CardManagement cardManagement) {
        this.cardManagement = cardManagement;
    }

    @Override
    public boolean checkAccess(String roomNumber, String cardId) {
        CardAccess card = cardManagement.getCard(cardId);
        if (card == null) return false;
        
        int roomNum = Integer.parseInt(roomNumber);
        String requiredPermission = getRequiredPermissionLevel(roomNum);
        return card.getCardPermission().contains(requiredPermission);
    }

    @Override
    public String getRequiredPermissionLevel(int roomNumber) {
        if (roomNumber >= 8) return "High";
        if (roomNumber >= 4) return "Medium";
        return "Low";
    }
} 
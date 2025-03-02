package cardModule.room;

public interface RoomAccess {
    boolean checkAccess(String roomNumber, String cardId);
    String getRequiredPermissionLevel(int roomNumber);
} 
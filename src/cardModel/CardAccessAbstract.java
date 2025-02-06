package cardModel;

import java.util.ArrayList;

public abstract class CardAccessAbstract {
    public abstract String getCardNumber();
    public abstract ArrayList<String> getCardPermission();
    
    public abstract String encryptData(String data);
    public abstract String decryptData(String data);
}

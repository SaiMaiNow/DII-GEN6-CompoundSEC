package cardModel;

import java.util.ArrayList;
public class CardAccess extends CardAccessAbstract {
    @Override
    public String getCardNumber() {
        return "1234567890";
    }

    @Override
    public ArrayList<String> getCardPermission() {
        return new ArrayList<String>();
    }

    @Override
    public String encryptData(String data) {
        return data;
    }

    @Override
    public String decryptData(String data) {
        return data;
    }
}

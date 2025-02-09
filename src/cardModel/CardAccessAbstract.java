package cardModel;

import java.util.ArrayList;
import java.util.Random;

public abstract class CardAccessAbstract {
    public abstract String getCardNumber();
    public abstract ArrayList<String> getCardPermission();

    public String encryptData(String data) {
        return data;
    };

    public String decryptData(String data) {
        return data;
    };

    public String generateCardId() {
        Random random = new Random();
        return String.valueOf(random.nextInt(900000000) + 100000000);
    };

}

package cardModule;

import java.util.ArrayList;
import java.util.Random;

public abstract class CardAccessAbstract {
    public abstract String getCardNumber();
    public abstract ArrayList<String> getCardPermission();

    protected String encryptData(String data) {
        return data;
    };

    protected String decryptData(String data) {
        return data;
    };

    protected String generateCardId() {
        Random random = new Random();
        return String.valueOf(random.nextInt(900000000) + 100000000);
    };

}

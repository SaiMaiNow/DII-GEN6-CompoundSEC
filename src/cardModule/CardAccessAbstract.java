package cardModule;

import java.util.ArrayList;
import java.util.Random;

public abstract class CardAccessAbstract {
    public abstract String getCardNumber();
    public abstract ArrayList<String> getCardPermission();
    public abstract void setCardLevel(String newLevel);

    protected String encryptData(String data) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : data.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                encrypted.append((char) (((c - base + 3) % 26) + base));
            } else if (Character.isDigit(c)) {
                encrypted.append((char) (((c - '0' + 3) % 10) + '0'));
            } else {
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }

    protected String generateCardId() {
        Random random = new Random();
        return String.valueOf(random.nextInt(900000000) + 100000000);
    };

}

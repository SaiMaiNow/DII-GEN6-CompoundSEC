package cardModule.card;

import java.util.ArrayList;
import java.util.Random;
import java.util.Date;

public abstract class CardAccessAbstract {
    public abstract String getCardNumber();
    public abstract ArrayList<String> getCardPermission();
    public abstract void setCardLevel(ArrayList<String> newLevel);
    public abstract void setExpiryDate(Date newExpiryDate);
    public abstract Date getExpiryDate();

    protected String encryptData(String data) {
        long currentTime = System.currentTimeMillis() / 1000;
        int timeShift = (int)(currentTime % 10);
        
        StringBuilder encrypted = new StringBuilder();
        for (char c : data.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                encrypted.append((char) (((c - base + timeShift) % 26) + base));
            } else if (Character.isDigit(c)) {
                encrypted.append((char) (((c - '0' + timeShift) % 10) + '0'));
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
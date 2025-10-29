package vasyurin.work.bank_rest.util;

public class CardUtils {

    private CardUtils() {}

    public static String maskCardNumber(String number) {
        if (number == null || number.length() < 4) {
            return "****";
        }
        String lastFour = number.substring(number.length() - 4);
        return "**** **** **** " + lastFour;
    }
}

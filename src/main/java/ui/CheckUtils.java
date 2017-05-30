package ui;

public final class CheckUtils {
    private CheckUtils() {
    }

    public static Boolean checkMaxLength(String checkString, int countSymbol) {
        return checkString.length() <= countSymbol;
    }

    public static Boolean checkLength(String checkString, int countSymbol) {
        return checkString.length() == countSymbol;
    }

    public static Boolean isInteger(String checkString) {
        try {
            Integer.parseInt(checkString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

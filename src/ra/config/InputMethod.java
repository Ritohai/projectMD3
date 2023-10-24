package ra.config;
import java.util.Scanner;

public class InputMethod {
    private static final String ERROR_ALERT = "===>> Định dạng không hợp lệ, hoặc ngoài phạm vi! Vui lòng thử lại....";
    public static final String EMPTY_ALERT = "===>> Trường nhập vào không thể để trống! Vui lòng thử lại....";
    public static final String ERROR_NUMBER = "===>> Vui lòng nhập số nguyên lớn hơn 0";
    public static final String ERROR_SONGID = "===>> Id phải bắt đầu bằng kí tự P và có 4 kí tự số";
    public static final String ERROR_ALERT_10 = "====>> Ít nhất là 10 <<====";
    public static final String ERROR_USENAME = "===>> Usename phải lớn hơn 5 kí tự";
    public static final String ERROR_PASS = "===>> password phải lớn hơn 5 kí tự và không chứa khoảng trắng";
    public static final String ERROR_PHONE = "===>> Số điện thoại bạn nhập không đúng định dạng";
    /*========================================Input Method Start========================================*/

    /**
     * getString()  Return a String value from the user.
     */
    public static String getString() {
        while (true) {
            String result = getInput();
            if (result.trim().equals("")) {
                System.err.println(EMPTY_ALERT);
                continue;
            }
            return result;
        }
    }

    /**
     * getChar()  Return a Character value from the user.
     */
    public static char getChar() {
        return getString().charAt(0);
    }

    /**
     * getBoolean()  Return a Boolean value from the user.
     */
    public static boolean getBoolean() {
        String result = getString();
        return result.equalsIgnoreCase("true");
    }

    /**
     * getByte()  Return a Byte value from the user.
     */
    public static byte getByte() {
        while (true) {
            try {
                return Byte.parseByte(getString());
            } catch (NumberFormatException errException) {
                System.err.println(ERROR_ALERT);
            }
        }
    }

    /**
     * getShort()  Return a Short value from the user.
     */
    public static short getShort() {
        while (true) {
            try {
                return Short.parseShort(getString());
            } catch (NumberFormatException errException) {
                System.err.println(ERROR_ALERT);
            }
        }
    }

    /**
     * getInteger()  Return a Integer value from the user.
     */
    public static int getInteger() {
        while (true) {
            try {
                return Integer.parseInt(getString());
            } catch (NumberFormatException errException) {
                System.err.println(ERROR_ALERT);
            }
        }
    }

    /**
     * getLong()  Return a Long value from the user.
     */
    public static long getLong() {
        while (true) {
            try {
                return Long.parseLong(getString());
            } catch (NumberFormatException errException) {
                System.err.println(ERROR_ALERT);
            }
        }
    }

    /**
     * getFloat()  Return a Float value from the user.
     */
    public static float getFloat() {
        while (true) {
            try {
                return Float.parseFloat(getString());
            } catch (NumberFormatException errException) {
                System.err.println(ERROR_ALERT);
            }
        }
    }

    /**
     * getDouble()  Return a Double value from the user.
     */
    public static double getDouble() {
        while (true) {
            try {
                return Double.parseDouble(getString());
            } catch (NumberFormatException errException) {
                System.err.println(ERROR_ALERT);
            }
        }
    }
    /*========================================Input Method End========================================*/

    /**
     * getInput()  Return any String value from the user.
     */
    public static String getInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /**
     * pressAnyKey()  Press any key to continue....
     */
    public static void pressAnyKey() {
        getInput();
    }

    //kiểm tra số nhập vào có lớn hon 0
    public static int getPositiveInteger() {
        while (true) {
            int result = getInteger();
            if (result > 0) {
                return result;
            }
            System.err.println(ERROR_NUMBER);
        }
    }

    // kiểm tra id có đúng định dạng không
    public static String getProductId() {
        while (true) {
            String result = getString();
            if (result.startsWith("P") && result.length() == 5) {

                return result;
            }
            System.err.println(ERROR_SONGID);
        }
    }

    public static int getProductInt() {
        while (true) {
            int result = getInteger();
            if (result > 10) {
                return result;
            }
            System.err.println(ERROR_ALERT_10);
        }
    }
    public static String getusename() {
        while (true) {
            String result = getString();
            if (result.trim().length() >= 6 && result.trim().replaceAll("\\s+", "").equals(result.trim())) {
                return result;
            }
            System.err.println(ERROR_USENAME);
        }
    }
    public static String getpassword() {
        while (true) {
            String result = getString();
            if (result.trim().length() >= 5 && result.trim().replaceAll("\\s+", "").equals(result.trim())) {
                return result;
            }
            System.err.println(ERROR_PASS);
        }
    }
    public static String getPhoneNumber() {
        while (true) {
            String result = getString();
            if (result.matches("^(\\+?84|0)\\d{9}$")) {
                // Hợp lệ nếu số điện thoại có đúng định dạng
                return result;
            }
            System.err.println(ERROR_PHONE);
        }
    }
    /*========================================Other Method========================================*/
}

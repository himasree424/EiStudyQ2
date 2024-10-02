package smarthome;
public class LoggingUtility {
    public static void log(String message) {
        System.out.println("LOG: " + message);
    }

    public static void handleException(Exception e) {
        System.out.println("ERROR: " + e.getMessage());
    }
}

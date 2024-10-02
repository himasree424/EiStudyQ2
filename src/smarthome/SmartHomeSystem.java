package smarthome;
import java.util.HashMap;
import java.util.Map;

public class SmartHomeSystem {
    private static SmartHomeSystem instance = null;
    private Map<String, SmartDevice> devices = new HashMap<>();

    private SmartHomeSystem() {}

    public static SmartHomeSystem getInstance() {
        if (instance == null) {
            instance = new SmartHomeSystem();
        }
        return instance;
    }
    
    public void addDevice(String type, String id) {
        SmartDevice device = null;

        switch (type.toLowerCase()) {
            case "light":
                device = new Light(id);
                break;
            case "thermostat":
                device = new Thermostat(id, 100); // Default temp (adjust as needed)
                break;
            case "doorlock":
                device = new DoorLock(id);
                break;
        }

        if (device != null) {
            devices.put(id, device);
            System.out.println(type + " " + id + " added to the system.");
        } else {
            System.out.println("Unsupported device type.");
        }
    }

    // New method to add a device directly as an object
    public void addDevice(SmartDevice device) {
        devices.put(device.getDeviceID(), device);
        System.out.println(device.getClass().getSimpleName() + " " + device.getDeviceID() + " added to the system.");
    }
    
    /*public void addDevice(String type, String id) {
        SmartDevice device = null;
        switch (type.toLowerCase()) {
            case "light":
                device = new Light(id);
                break;
            case "thermostat":
                device = new Thermostat(id);
                break;
            case "doorlock":
                device = new DoorLock(id);
                break;
            default:
                System.out.println("Unsupported device type: " + type);
                return;
        }
        devices.put(id, device);
        System.out.println(id + " added to the system.");
    }*/

    public SmartDevice getDevice(String id) {
        return devices.get(id);
    }

    public void controlDevice(String deviceID, boolean turnOn) {
        SmartDevice device = devices.get(deviceID);
        if (device != null) {
            if (turnOn) {
                device.turnOn();
            } else {
                device.turnOff();
            }
        } else {
            System.out.println("Device not found.");
        }
    }

    public void scheduleTask(String deviceID, String time, String command) {
        System.out.println("Scheduled " + command + " for device " + deviceID + " at " + time);
    }
    public void triggerAutomation(String condition, String action) {
        try {
            // Parse the condition (e.g., "temperature > 75")
            String[] conditionParts = condition.split(" ");
            String conditionType = conditionParts[0]; // e.g., temperature
            String operator = conditionParts[1];     // e.g., >
            int value = Integer.parseInt(conditionParts[2]);  // e.g., 75

            // Handle the temperature condition
            if (conditionType.equalsIgnoreCase("temperature")) {
                // Assuming we have a thermostat with ID "thermo1"
                Thermostat thermostat = (Thermostat) devices.get("thermo1");

                if (thermostat != null) {
                    int currentTemp = thermostat.getTemperature();  // Getter for temperature

                    // Evaluate the condition
                    boolean conditionMet = false;
                    if (operator.equals(">")) {
                        conditionMet = currentTemp > value;
                    } else if (operator.equals("<")) {
                        conditionMet = currentTemp < value;
                    } else if (operator.equals("==")) {
                        conditionMet = currentTemp == value;
                    }

                    // Execute action if condition is met
                    if (conditionMet) {
                        System.out.println("Condition met: " + condition);
                        executeAction(action);
                    } else {
                        System.out.println("Condition not met: " + condition);
                    }
                } else {
                    System.out.println("Thermostat not found.");
                }
            } else {
                System.out.println("Unsupported condition type: " + conditionType);
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Invalid condition or action format. Please check your input.");
        }
    }

    private void executeAction(String action) {
        try {
            // Example input: "turnOff(light1)"
            String[] actionParts = action.split("\\(");
            if (actionParts.length < 2) {
                System.out.println("Invalid action format. Expected format: action(deviceID)");
                return;
            }

            String actionType = actionParts[0];  // e.g., "turnOff"
            String deviceID = actionParts[1].replace(")", "");  // e.g., "light1"

            SmartDevice device = devices.get(deviceID);

            if (device != null) {
                if (actionType.equalsIgnoreCase("turnOff")) {
                    device.turnOff();
                } else if (actionType.equalsIgnoreCase("turnOn")) {
                    device.turnOn();
                } else {
                    System.out.println("Unsupported action: " + actionType);
                }
            } else {
                System.out.println("Device not found for action: " + deviceID);
            }
        } catch (Exception e) {
            System.out.println("Error executing action: " + e.getMessage());
        }
    }
}
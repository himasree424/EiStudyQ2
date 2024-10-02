package smarthome;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SmartHomeSystem system = SmartHomeSystem.getInstance();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Device");
            System.out.println("2. Control Device");
            System.out.println("3. Schedule Task");
            System.out.println("4. Trigger Automation");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter device type (light/thermostat/doorlock): ");
                    String type = scanner.next();
                    System.out.print("Enter device ID: ");
                    String id = scanner.next();

                    if (type.equalsIgnoreCase("thermostat")) {
                        // Prompt for initial temperature if adding a thermostat
                        System.out.print("Enter initial temperature: ");
                        int initialTemp = scanner.nextInt();
                        Thermostat thermostat = new Thermostat(id, initialTemp); // Use the constructor with initialTemp
                        system.addDevice(thermostat); // Add the thermostat to the system
                    } else {
                        // For other devices, add them directly
                        system.addDevice(type, id);
                    }
                    break;

                case 2:
                    System.out.print("Enter device ID: ");
                    String deviceID = scanner.next();

                    // Check if the device is a light
                    SmartDevice device = system.getDevice(deviceID);
                    if (device instanceof Light) {  // Check if the device is a Light
                        System.out.println("1. Turn on/off");
                        System.out.println("2. Adjust brightness");
                        int subChoice = scanner.nextInt();

                        // Handle turning light on/off or adjusting brightness
                        if (subChoice == 1) {
                            System.out.print("Turn on (true/false): ");
                            boolean turnOn = scanner.nextBoolean();
                            system.controlDevice(deviceID, turnOn);
                        } else if (subChoice == 2) {
                            System.out.print("Enter brightness level (0-100): ");
                            int brightness = scanner.nextInt();
                            Light light = (Light) device;
                            light.setBrightness(brightness);  // Set brightness of the light
                        }
                    } else {
                        // Handle other devices (thermostat, doorlock)
                        System.out.print("Turn on (true/false): ");
                        boolean turnOn = scanner.nextBoolean();
                        system.controlDevice(deviceID, turnOn);
                    }
                    break;

                case 3:
                    System.out.print("Enter device ID: ");
                    deviceID = scanner.next();
                    System.out.print("Enter time (e.g., 18:00): ");
                    String time = scanner.next();
                    System.out.print("Enter command (turnOn/turnOff): ");
                    String command = scanner.next();
                    system.scheduleTask(deviceID, time, command);
                    break;

                case 4:
                    // Trigger automation based on device ID, condition, and action
                    System.out.print("Enter device ID: ");
                    deviceID = scanner.next(); // Retrieve device ID for the condition
                    SmartDevice targetDevice = system.getDevice(deviceID);

                    if (targetDevice == null) {
                        System.out.println("Device not found.");
                        break;
                    }

                    System.out.print("Enter condition (e.g., temperature > 75): ");
                    scanner.nextLine(); // Consume the newline character
                    String condition = scanner.nextLine();
                    System.out.print("Enter action (e.g., turnOff): ");
                    String action = scanner.nextLine();

                    if (targetDevice instanceof Thermostat) {
                        Thermostat thermostat = (Thermostat) targetDevice;
                        if (evaluateCondition(thermostat.getTemperature(), condition)) {
                            if (action.equalsIgnoreCase("turnOff")) {
                                thermostat.turnOff();
                            } else if (action.equalsIgnoreCase("turnOn")) {
                                thermostat.turnOn();
                            } else {
                                System.out.println("Invalid action.");
                            }
                        } else {
                            System.out.println("Condition not met.");
                        }
                    } else {
                        System.out.println("Automation only supports thermostats.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Helper function to evaluate the condition
    private static boolean evaluateCondition(int currentValue, String condition) {
        // Parse the condition string (e.g., temperature > 75 or temperature < 100)
        String[] parts = condition.split(" ");
        if (parts.length != 3) {
            System.out.println("Invalid condition format.");
            return false;
        }

        String operator = parts[1];
        int threshold;
        try {
            threshold = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid threshold value.");
            return false;
        }

        // Evaluate the condition
        switch (operator) {
            case ">":
                return currentValue > threshold;
            case "<":
                return currentValue < threshold;
            case ">=":
                return currentValue >= threshold;
            case "<=":
                return currentValue <= threshold;
            case "==":
                return currentValue == threshold;
            case "!=":
                return currentValue != threshold;
            default:
                System.out.println("Invalid operator.");
                return false;
        }
    }
}


/*package smarthome;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SmartHomeSystem system = SmartHomeSystem.getInstance();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Device");
            System.out.println("2. Control Device");
            System.out.println("3. Schedule Task");
            System.out.println("4. Trigger Automation");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                	System.out.print("Enter device type (light/thermostat/doorlock): ");
                    String type = scanner.next();
                    System.out.print("Enter device ID: ");
                    String id = scanner.next();

                    if (type.equalsIgnoreCase("thermostat")) {
                        // Prompt for initial temperature if adding a thermostat
                        System.out.print("Enter initial temperature: ");
                        int initialTemp = scanner.nextInt();
                        Thermostat thermostat = new Thermostat(id, initialTemp); // Use the constructor with initialTemp
                        system.addDevice(thermostat); // Add the thermostat to the system
                    } else {
                        // For other devices, add them directly
                        system.addDevice(type, id);
                    }
                    break;

                case 2:
                	System.out.print("Enter device ID: ");
                    String deviceID = scanner.next();

                    // Check if the device is a light
                    SmartDevice device = system.getDevice(deviceID);
                    if (device instanceof Light) {  // Check if the device is a Light
                        System.out.println("1. Turn on/off");
                        System.out.println("2. Adjust brightness");
                        int subChoice = scanner.nextInt();

                        // Handle turning light on/off or adjusting brightness
                        if (subChoice == 1) {
                            System.out.print("Turn on (true/false): ");
                            boolean turnOn = scanner.nextBoolean();
                            system.controlDevice(deviceID, turnOn);
                        } else if (subChoice == 2) {
                            System.out.print("Enter brightness level (0-100): ");
                            int brightness = scanner.nextInt();
                            Light light = (Light) device;
                            light.setBrightness(brightness);  // Set brightness of the light
                        }
                    } else {
                        // Handle other devices (thermostat, doorlock)
                        System.out.print("Turn on (true/false): ");
                        boolean turnOn = scanner.nextBoolean();
                        system.controlDevice(deviceID, turnOn);
                    }
                    break;

                case 3:
                    System.out.print("Enter device ID: ");
                    deviceID = scanner.next();
                    System.out.print("Enter time (e.g., 18:00): ");
                    String time = scanner.next();
                    System.out.print("Enter command (turnOn/turnOff): ");
                    String command = scanner.next();
                    system.scheduleTask(deviceID, time, command);
                    break;

                case 4:
                    System.out.print("Enter condition (e.g., temperature > 75): ");
                    scanner.nextLine();
                    String condition = scanner.nextLine();
                    System.out.print("Enter action (e.g., turnOff): ");
                    String action = scanner.nextLine();
                    system.triggerAutomation(condition, action);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}*/

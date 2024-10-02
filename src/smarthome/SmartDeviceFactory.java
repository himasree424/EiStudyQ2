package smarthome;
public class SmartDeviceFactory {
    public static SmartDevice createDevice(String type, String deviceID) {
        switch (type.toLowerCase()) {
            case "light":
                return new Light(deviceID);
            case "thermostat":
                return new Thermostat(deviceID, 72);  // Default temp
            case "doorlock":
                return new DoorLock(deviceID);
            default:
                throw new IllegalArgumentException("Unknown device type: " + type);
        }
    }
}

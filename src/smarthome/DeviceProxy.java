package smarthome;

public class DeviceProxy {
    private SmartDevice realDevice;
    private boolean authorized;

    public DeviceProxy(SmartDevice realDevice, boolean isAuthorized) {
        this.realDevice = realDevice;
        this.authorized = isAuthorized;
    }

    public void turnOn() {
        if (authorized) {
            realDevice.turnOn();
        } else {
            System.out.println("Unauthorized access to device: " + realDevice.getDeviceID());
        }
    }

    public void turnOff() {
        if (authorized) {
            realDevice.turnOff();
        } else {
            System.out.println("Unauthorized access to device: " + realDevice.getDeviceID());
        }
    }
}
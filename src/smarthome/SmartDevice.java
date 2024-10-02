package smarthome;
public interface SmartDevice {
    void turnOn();
    void turnOff();
    String getStatus();
    String getDeviceID();
}
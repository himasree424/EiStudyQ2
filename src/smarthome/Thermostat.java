package smarthome;
public class Thermostat implements SmartDevice {
    private String deviceID;
    private boolean isOn;
    private int temperature;

    public Thermostat(String deviceID, int initialTemp) {
        this.deviceID = deviceID;
        this.isOn = false;
        this.temperature = initialTemp;
    }
    
    public Thermostat(String deviceID) {
        this.deviceID = deviceID;
        this.temperature = 72;  // Default temperature
    }
    

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("Thermostat " + deviceID + " is On.");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("Thermostat " + deviceID + " is Off.");
    }

    public void setTemperature(int temp) {
        this.temperature = temp;
        System.out.println("Thermostat " + deviceID + " temperature set to " + temp);
    }

    public int getTemperature() {
        return this.temperature;
    }

    @Override
    public String getStatus() {
        return isOn ? "On" : "Off";
    }

    @Override
    public String getDeviceID() {
        return this.deviceID;
    }
}


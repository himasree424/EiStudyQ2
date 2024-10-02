package smarthome;

public class Light implements SmartDevice {
    private String deviceID;
    private boolean isOn;
    private int brightness;

    public Light(String deviceID) {
        this.deviceID = deviceID;
        this.isOn = false;
        this.brightness = 100;  // Default brightness
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("Light " + deviceID + " is On.");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("Light " + deviceID + " is Off.");
    }

    @Override
    public String getStatus() {
        return isOn ? "On" : "Off";
    }

    @Override
    public String getDeviceID() {
        return this.deviceID;
    }
    
    public void setBrightness(int brightness) {
        if (brightness < 0 || brightness > 100) {
            System.out.println("Invalid brightness level. Please set a value between 0 and 100.");
        } else {
            this.brightness = brightness;
            System.out.println("Brightness of light " + deviceID + " set to " + brightness + "%.");
        }
    }

    public int getBrightness() {
        return this.brightness;
    }
}

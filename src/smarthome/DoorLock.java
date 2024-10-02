package smarthome;
public class DoorLock implements SmartDevice {
    private String deviceID;
    private boolean isLocked;

    public DoorLock(String deviceID) {
        this.deviceID = deviceID;
        this.isLocked = true;  // Default to locked
    }

    public void lock() {
        isLocked = true;
        System.out.println("Door " + deviceID + " is Locked.");
    }

    public void unlock() {
        isLocked = false;
        System.out.println("Door " + deviceID + " is Unlocked.");
    }

    @Override
    public void turnOn() {
        unlock();
    }

    @Override
    public void turnOff() {
        lock();
    }

    @Override
    public String getStatus() {
        return isLocked ? "Locked" : "Unlocked";
    }

    @Override
    public String getDeviceID() {
        return this.deviceID;
    }
}


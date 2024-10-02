package smarthome;
import java.util.ArrayList;
import java.util.List;

public class Observer {
    private List<SmartDevice> devices = new ArrayList<>();
    
    public void addDevice(SmartDevice device) {
        devices.add(device);
    }

    public void notifyDevices(String message) {
        for (SmartDevice device : devices) {
            System.out.println("Notifying " + device.getDeviceID() + ": " + message);
        }
    }
}

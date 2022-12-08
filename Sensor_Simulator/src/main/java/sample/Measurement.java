package sample;

public class Measurement {
    private String timestamp;
    private double value;
    private int deviceId;

    public Measurement(String timestamp, double value, int deviceId) {
        this.timestamp = timestamp;
        this.value = value;
        this.deviceId = deviceId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }
}

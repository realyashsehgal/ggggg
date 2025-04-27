package src.models;

import java.time.LocalDateTime;

public class CarExit {
    int carexitid;
    String carnumber;
    String drivername;
    LocalDateTime exittime;
    float amount;

    private String availability;

    public CarExit(String carnumber, String drivername, LocalDateTime exittime, float amount) {
        this.carnumber = carnumber;
        this.drivername = drivername;
        this.exittime = exittime;
        this.amount = amount;
    }

    public int getCarexitid() {
        return carexitid;
    }

    public String getCarnum() {
        return carnumber;
    }

    public String getDrivername() {
        return drivername;
    }

    public LocalDateTime getExittime() {
        return exittime;
    }

    public float getAmount() {
        return amount;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "ID: ";
    }
}

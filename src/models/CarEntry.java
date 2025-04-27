package src.models;

import java.time.LocalDateTime;

public class CarEntry {

    String carnumber;
    String drivername;
    LocalDateTime exittimestamp;

    public CarEntry(String carnumber, String drivername, LocalDateTime exittimestamp) {
        this.carnumber = carnumber;
        this.exittimestamp = exittimestamp;
        this.drivername = drivername;
    }

    public String getCarnum() {
        return carnumber;
    }

    public LocalDateTime getEntrytime() {
        return exittimestamp;
    }

    public String getDrivername() {
        return drivername;
    }

    // @Override
    // public String toString() {
    // return "ERP_ID: " + erpId + ", Name: " + name + ", Course: " + course + "\n";
    // }
}

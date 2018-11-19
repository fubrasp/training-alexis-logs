package model;

import java.util.Calendar;
import java.util.Objects;

public class Temperature {
    private String id;
    private Calendar date;
    private double celsiusValue;
    private Location location;

    public Temperature(String id, Calendar date, double celsiusValue, Location location) {
        this.id = id;
        this.date = date;
        this.celsiusValue = celsiusValue;
        this.location = location;
    }

    public Temperature() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public double getCelsiusValue() {
        return celsiusValue;
    }

    public void setCelsiusValue(double celsiusValue) {
        this.celsiusValue = celsiusValue;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Temperature)) return false;
        Temperature that = (Temperature) o;
        return Double.compare(that.celsiusValue, celsiusValue) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(date, that.date) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, celsiusValue, location);
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "id='" + id + '\'' +
                ", date=" + date.getTime() +
                ", celsiusValue=" + celsiusValue +
                ", location=" + location +
                '}';
    }
}

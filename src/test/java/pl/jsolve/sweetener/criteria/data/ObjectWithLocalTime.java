package pl.jsolve.sweetener.criteria.data;

import org.joda.time.LocalTime;

public class ObjectWithLocalTime {

    private int index;
    private LocalTime now;

    public ObjectWithLocalTime(int index, LocalTime date) {
        this.index = index;
        now = date;
    }

    public int getIndex() {
        return index;
    }

    public LocalTime getNow() {
        return now;
    }

    @Override
    public String toString() {
        return "ObjectWithDate [index=" + index + ", now=" + now + "]";
    }

}
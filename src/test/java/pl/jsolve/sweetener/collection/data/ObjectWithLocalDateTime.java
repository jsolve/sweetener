package pl.jsolve.sweetener.collection.data;

import org.joda.time.LocalDateTime;

public class ObjectWithLocalDateTime {

    private int index;
    private LocalDateTime now;

    public ObjectWithLocalDateTime(int index, LocalDateTime date) {
        this.index = index;
        now = date;
    }

    public int getIndex() {
        return index;
    }

    public LocalDateTime getNow() {
        return now;
    }

    @Override
    public String toString() {
        return "ObjectWithDate [index=" + index + ", now=" + now + "]";
    }

}
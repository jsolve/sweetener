package pl.jsolve.sweetener.criteria.data;

import org.joda.time.LocalDate;

public class ObjectWithLocalDate {

    private int index;
    private LocalDate now;

    public ObjectWithLocalDate(int index, LocalDate date) {
        this.index = index;
        now = date;
    }

    public int getIndex() {
        return index;
    }

    public LocalDate getNow() {
        return now;
    }

    @Override
    public String toString() {
        return "ObjectWithDate [index=" + index + ", now=" + now + "]";
    }

}
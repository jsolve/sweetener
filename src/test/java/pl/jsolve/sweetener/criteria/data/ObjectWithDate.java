package pl.jsolve.sweetener.criteria.data;

import java.util.Date;

public class ObjectWithDate {

    private int index;
    private Date now;

    public ObjectWithDate(int index, Date date) {
        this.index = index;
        now = date;
    }

    public int getIndex() {
        return index;
    }

    public Date getNow() {
        return now;
    }

    @Override
    public String toString() {
        return "ObjectWithDate [index=" + index + ", now=" + now + "]";
    }

}
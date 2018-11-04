package buildings.office;

import buildings.exceptions.InvalidRoomsCountException;
import buildings.exceptions.InvalidSpaceAreaException;
import buildings.interfaces.Space;

import java.io.Serializable;
import java.util.Formatter;
import java.util.Locale;

public class Office implements Space, Serializable, Cloneable {
    private float officeArea;
    private int roomsQuantity;
    private static final float DEFAULT_AREA = 250;
    private static final int DEFAULT_ROOMS = 1;

    public Office(){
        this(DEFAULT_ROOMS, DEFAULT_AREA);
    }
    public Office(float area){
        this(DEFAULT_ROOMS, area);
    }
    public Office(int rooms, float area){
        if(area <= 0){
            throw new InvalidSpaceAreaException("Area must be > 0");
        }
        if(rooms <= 0){
            throw new InvalidRoomsCountException("Rooms quantity must be > 0");
        }
        officeArea = area;
        roomsQuantity = rooms;
    }

    public float getArea() {
        return officeArea;
    }

    public void setArea(float officeArea) {
        this.officeArea = officeArea;
    }

    public int getRoomsQuantity() {
        return roomsQuantity;
    }

    public void setRoomsQuantity(int roomsQuantity) {
        this.roomsQuantity = roomsQuantity;
    }

    @Override
    public String toString(){
        Formatter form = new Formatter(Locale.US);
        form.format("Office (%d, %.1f)", roomsQuantity, officeArea);
        return form.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(obj instanceof Office){
            return ((Office) obj).roomsQuantity == this.roomsQuantity && ((Office) obj).officeArea == this.officeArea;
        }
        return false;
    }

    @Override
    public int hashCode() {
        String sd = String.valueOf(Float.floatToIntBits(officeArea));
        return roomsQuantity ^ Integer.parseInt(sd.substring(0,4)) ^ Integer.parseInt(sd.substring(5,8));
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public int compareTo(Space o) {
        if(getArea() > o.getArea()){
            return 1;
        } else if(getArea() < o.getArea()){
            return -1;
        } else return 0;
    }
}

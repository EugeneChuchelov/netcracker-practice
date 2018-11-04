package buildings.dwelling;

import buildings.exceptions.InvalidRoomsCountException;
import buildings.exceptions.InvalidSpaceAreaException;
import buildings.interfaces.Space;

import java.io.Serializable;
import java.util.Formatter;
import java.util.Locale;

public class Flat implements Space, Serializable, Cloneable {
    private float flatArea;
    private int roomsQuantity;
    private static final float DEFAULT_AREA = 50;
    private static final int DEFAULT_ROOMS = 2;

    public Flat(){
        this(DEFAULT_ROOMS, DEFAULT_AREA);
    }

    public Flat(float area){
        this(DEFAULT_ROOMS, area);
    }

    public Flat(int rooms, float area){
        if(area <= 0){
            throw new InvalidSpaceAreaException("Area must be > 0");
        }
        if(rooms <= 0){
            throw new InvalidRoomsCountException("Rooms quantity must be > 0");
        }
        flatArea = area;
        roomsQuantity = rooms;
    }

    public float getArea() {
        return flatArea;
    }

    public void setArea(float flatArea) {
        this.flatArea = flatArea;
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
        form.format("Flat (%d, %.1f)", roomsQuantity, flatArea);
        return form.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(obj instanceof Flat){
            return ((Flat) obj).roomsQuantity == this.roomsQuantity && ((Flat) obj).flatArea == this.flatArea;
        }
        return false;
    }

    @Override
    public int hashCode() {
        String sd = String.valueOf(Float.floatToIntBits(flatArea));
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

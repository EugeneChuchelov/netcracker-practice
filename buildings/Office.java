package buildings;

import buildings.Exceptions.InvalidRoomsCountException;
import buildings.Exceptions.InvalidSpaceAreaException;

import java.io.Serializable;

public class Office implements Space, Serializable {
    private float officeArea;
    private int roomsQuantity;
    private static final float DEFAULT_AREA = 250;
    private static final int DEFAULT_ROOMS = 1;

    public Office(){
        this(DEFAULT_AREA, DEFAULT_ROOMS);
    }
    public Office(float area){
        this(area, DEFAULT_ROOMS);
    }
    public Office(float area, int rooms){
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

    public String toString(){
        StringBuilder output = new StringBuilder("Office: ");
        output.append(roomsQuantity).append(", ").append(officeArea).append("; ");
        return output.toString();
    }
}

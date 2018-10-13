package buildings;

public class Office {
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

    public float getOfficeArea() {
        return officeArea;
    }

    public void setOfficeArea(float officeArea) {
        this.officeArea = officeArea;
    }

    public int getRoomsQuantity() {
        return roomsQuantity;
    }

    public void setRoomsQuantity(int roomsQuantity) {
        this.roomsQuantity = roomsQuantity;
    }
}

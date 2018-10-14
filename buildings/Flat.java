package buildings;

public class Flat implements Space {
    private float flatArea;
    private int roomsQuantity;
    private static final float DEFAULT_AREA = 50;
    private static final int DEFAULT_ROOMS = 2;

    public Flat(){
        this(DEFAULT_AREA, DEFAULT_ROOMS);
    }

    public Flat(float area){
        this(area, DEFAULT_ROOMS);
    }

    public Flat(float area, int rooms){
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
}

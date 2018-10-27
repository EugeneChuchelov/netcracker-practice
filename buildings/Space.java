package buildings;

public interface Space {
    int getRoomsQuantity();
    void setRoomsQuantity(int roomsQuantity);
    float getArea();
    void setArea(float area);
    Object clone();
}

package buildings.interfaces;

public interface Space extends Comparable<Space> {
    int getRoomsQuantity();
    void setRoomsQuantity(int roomsQuantity);
    float getArea();
    void setArea(float area);
    Object clone();
}

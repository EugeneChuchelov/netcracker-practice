package buildings.interfaces;

public interface Building extends Iterable<Floor>{
    int getSize();
    int getSpacesQuantity();
    float getAreaTotal();
    int getRoomsTotal();
    Floor[] toArray();
    Floor getFloor(int number);
    void setFloor(int number, Floor floor);
    Space getSpace(int number);
    void setSpace(int number, Space space);
    void addSpace(int number, Space space);
    void removeSpace(int number);
    Space getBestSpace();
    Space[] getSpacesSorted();
    Object clone();
    java.util.Iterator<Floor> iterator();
}

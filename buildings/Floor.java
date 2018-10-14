package buildings;

public interface Floor {
    int getSize();
    float getAreaTotal();
    int getRoomsTotal();
    Space[] toArray();
    Space getSpace(int number);
    void setSpace(int number,Space space);
    void add(int number, Space space);
    void remove(int number);
    Space getBestSpace();
}

package buildings.interfaces;

public interface Floor extends Iterable<Space>, Comparable<Floor>{
    int getSize();
    float getAreaTotal();
    int getRoomsTotal();
    Space[] toArray();
    Space getSpace(int number);
    void setSpace(int number,Space space);
    void add(int number, Space space);
    void remove(int number);
    Space getBestSpace();
    Object clone();
    java.util.Iterator<Space> iterator();
}

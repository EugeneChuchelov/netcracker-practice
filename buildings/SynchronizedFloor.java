package buildings;

import buildings.interfaces.Floor;
import buildings.interfaces.Space;

import java.util.Iterator;

public class SynchronizedFloor implements Floor {
    private Floor floor;

    public SynchronizedFloor(Floor floor) {
        this.floor = floor;
    }

    @Override
    public int getSize() {
        return floor.getSize();
    }

    @Override
    public float getAreaTotal() {
        return floor.getAreaTotal();
    }

    @Override
    public int getRoomsTotal() {
        return floor.getRoomsTotal();
    }

    @Override
    public Space[] toArray() {
        return floor.toArray();
    }

    @Override
    public Space getSpace(int number) {
        return floor.getSpace(number);
    }

    @Override
    public void setSpace(int number, Space space) {
        floor.setSpace(number, space);
    }

    @Override
    public void add(int number, Space space) {
        floor.add(number, space);
    }

    @Override
    public void remove(int number) {
        floor.remove(number);
    }

    @Override
    public Space getBestSpace() {
        return floor.getBestSpace();
    }

    @Override
    public Iterator<Space> iterator() {
        return floor.iterator();
    }

    @Override
    public int compareTo(Floor o) {
        return floor.compareTo(o);
    }

    @Override
    public Object clone() {
        return new SynchronizedFloor((Floor) floor.clone());
    }
}

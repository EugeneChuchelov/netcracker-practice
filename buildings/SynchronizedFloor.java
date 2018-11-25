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
    public synchronized int getSize() {
        return floor.getSize();
    }

    @Override
    public synchronized float getAreaTotal() {
        return floor.getAreaTotal();
    }

    @Override
    public synchronized int getRoomsTotal() {
        return floor.getRoomsTotal();
    }

    @Override
    public synchronized Space[] toArray() {
        return floor.toArray();
    }

    @Override
    public synchronized Space getSpace(int number) {
        return floor.getSpace(number);
    }

    @Override
    public synchronized void setSpace(int number, Space space) {
        floor.setSpace(number, space);
    }

    @Override
    public synchronized void add(int number, Space space) {
        floor.add(number, space);
    }

    @Override
    public synchronized void remove(int number) {
        floor.remove(number);
    }

    @Override
    public synchronized Space getBestSpace() {
        return floor.getBestSpace();
    }

    @Override
    public synchronized Iterator<Space> iterator() {
        return floor.iterator();
    }

    @Override
    public synchronized int compareTo(Floor o) {
        return floor.compareTo(o);
    }

    @Override
    public synchronized Object clone() {
        return new SynchronizedFloor((Floor) floor.clone());
    }
}

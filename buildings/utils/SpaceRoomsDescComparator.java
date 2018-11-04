package buildings.utils;

import buildings.interfaces.Space;

import java.util.Comparator;

public class SpaceRoomsDescComparator implements Comparator<Space> {
    @Override
    public int compare(Space o1, Space o2) {
        if(o1.getRoomsQuantity() < o2.getRoomsQuantity()){
            return 1;
        } else if(o1.getRoomsQuantity() > o2.getRoomsQuantity()){
            return -1;
        } else {
            return 0;
        }
    }
}

package buildings.utils;

import buildings.interfaces.Floor;

import java.util.Comparator;

public class FloorAreaDescComparator implements Comparator<Floor> {
    @Override
    public int compare(Floor o1, Floor o2) {
        if(o1.getAreaTotal() < o2.getAreaTotal()){
            return 1;
        } else if(o1.getAreaTotal() > o2.getAreaTotal()){
            return -1;
        } else {
            return 0;
        }
    }
}

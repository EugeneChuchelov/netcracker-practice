package buildings.dwelling.hotel;

import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import buildings.dwelling.Dwelling;

public class HotelBuilding extends Dwelling {

    public HotelBuilding(int floorsQuantity, int[] flatsOnFloor) {
        super(floorsQuantity, flatsOnFloor);
    }

    public HotelBuilding(Floor[] floors) {
        super(floors);
    }

    public int getStars() {
        int stars = 0;
        for (Floor floor : toArray()) {
            if(floor instanceof HotelFloor){
                if (((HotelFloor) floor).getStars() > stars) {
                    stars = ((HotelFloor) floor).getStars();
                }
            }
        }
        return stars;
    }

    @Override
    public Space getBestSpace() {
        Space bestSpace = getSpace(0);
        Floor currentFloor;
        for (int i = 0; i < getSize(); i++){
            if(getFloor(i) instanceof HotelFloor){
                currentFloor = getFloor(i);
                for(int k = 0; k < currentFloor.getSize(); k++){
                    if(currentFloor.getSpace(k).getArea() * ((HotelFloor)currentFloor).getCoef()
                            > bestSpace.getArea() * ((HotelFloor)currentFloor).getCoef()){
                        bestSpace = currentFloor.getSpace(k);
                    }
                }
            }
        }
        return bestSpace;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Hotel building (");
        output.append(getStars()).append(getSize());
        for(Floor floor : getFloors()){
            output.append(", ").append(floor.toString());
        }
        output.append(")");
        return output.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(obj instanceof HotelBuilding){
            if(((HotelBuilding) obj).getSize() == this.getSize()){
                for(int i = 0; i < getSize(); i++){
                    if(!((HotelBuilding) obj).getFloor(i).equals(this.getFloor(i))){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = getSize();
        for(Floor floor : getFloors()){
            hash ^= floor.hashCode();
        }
        return hash;
    }
}

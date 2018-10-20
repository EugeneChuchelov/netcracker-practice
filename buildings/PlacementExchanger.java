package buildings;

import buildings.Exceptions.InexchangeableFloorsException;
import buildings.Exceptions.InexchangeableSpacesException;

public class PlacementExchanger {
    public static boolean isSpacesExchangePossible(Space space1, Space space2){
        if(space1.getArea() == space2.getArea() &&
                space1.getRoomsQuantity() == space2.getRoomsQuantity()){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isFloorsExchangePossible(Floor floor1, Floor floor2){
        if(floor1.getAreaTotal() == floor2.getAreaTotal() &&
                floor1.getRoomsTotal() == floor2.getRoomsTotal()){
            return true;
        } else {
            return false;
        }
    }

    public static void exchangeFloorRooms(Floor floor1, int index1,
                                          Floor floor2, int index2) throws InexchangeableSpacesException{
        if(!isSpacesExchangePossible(floor1.getSpace(index1), floor2.getSpace(index2))){
            throw new InexchangeableSpacesException("This spaces cannot be exchanged");
        }

        Space swapBuf = floor1.getSpace(index1);
        floor1.setSpace(index1, floor2.getSpace(index2));
        floor2.setSpace(index2, swapBuf);
    }

    public static void exchangeBuildingFloors(Building building1, int index1,
                                              Building building2, int index2) throws InexchangeableFloorsException{
        if(!isFloorsExchangePossible(building1.getFloor(index1), building2.getFloor(index2))){
            throw new InexchangeableFloorsException("This floors cannot be exchanged");
        }

        Floor swapBuf = building1.getFloor(index1);
        building1.setFloor(index1, building2.getFloor(index2));
        building2.setFloor(index2, swapBuf);
    }
}

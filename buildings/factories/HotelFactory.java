package buildings.factories;

import buildings.dwelling.Flat;
import buildings.dwelling.hotel.HotelBuilding;
import buildings.dwelling.hotel.HotelFloor;
import buildings.interfaces.Building;
import buildings.interfaces.BuildingFactory;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;

public class HotelFactory implements BuildingFactory {
    @Override
    public Space createSpace(float area) {
        return new Flat(area);
    }

    @Override
    public Space createSpace(int roomsCount, float area) {
        return new Flat(roomsCount, area);
    }

    @Override
    public Floor createFloor(int spacesCount) {
        return new HotelFloor(spacesCount);
    }

    @Override
    public Floor createFloor(Space... spaces) {
        return new HotelFloor(spaces);
    }

    @Override
    public Building createBuilding(int floorsCount, int... spacesCounts) {
        return new HotelBuilding(floorsCount, spacesCounts);
    }

    @Override
    public Building createBuilding(Floor... floors) {
        return new HotelBuilding(floors);
    }
}

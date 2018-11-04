package buildings.factories;

import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import buildings.interfaces.Building;
import buildings.interfaces.BuildingFactory;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;

public class DwellingFactory implements BuildingFactory {
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
        return new DwellingFloor(spacesCount);
    }

    @Override
    public Floor createFloor(Space[] spaces) {
        return new DwellingFloor(spaces);
    }

    @Override
    public Building createBuilding(int floorsCount, int[] spacesCounts) {
        return new Dwelling(floorsCount, spacesCounts);
    }

    @Override
    public Building createBuilding(Floor[] floors) {
        return new Dwelling(floors);
    }
}

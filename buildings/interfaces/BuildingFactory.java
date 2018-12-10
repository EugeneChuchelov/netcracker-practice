package buildings.interfaces;

public interface BuildingFactory {
    Space createSpace(float area);
    Space createSpace(int roomsCount, float area);
    Floor createFloor(int spacesCount);
    Floor createFloor(Space... spaces);
    Building createBuilding(int floorsCount, int... spacesCounts);
    Building createBuilding(Floor... floors);
}

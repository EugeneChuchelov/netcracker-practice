package buildings;

import buildings.Exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;

public class DwellingFloor implements Floor, Serializable {
    private Space[] flats;
    private int size;

    public DwellingFloor(int flatsQuantity){
        flats = new Space[flatsQuantity];
        for(int i = 0; i < flatsQuantity; i++){
            flats[i] = new Flat();
            size++;
        }
    }

    public DwellingFloor(Space[] flats) {
        flats = new Flat[flats.length];
        System.arraycopy(flats, 0, this.flats, 0, flats.length);
    }

    public int getSize(){
        return size;
    }

    public float getAreaTotal(){
        float totalArea = 0;
        for(Space flat : flats){
            totalArea += flat.getArea();
        }
        return totalArea;
    }

    public int getRoomsTotal(){
        int totalRoomsQuantity = 0;
        for(Space flat : flats){
            totalRoomsQuantity += flat.getRoomsQuantity();
        }
        return totalRoomsQuantity;
    }

    public Space[] toArray() {
        return flats;
    }

    public Space getSpace(int number){
        testNumber(number);
        return flats[number];
    }

    public void setSpace(int number, Space flat){
        testNumber(number);
        flats[number] = flat;
    }

    public void add(int number, Space flat){
        testNumber(number);
        Flat[] itemsToShift = new Flat[size - number];
        System.arraycopy(flats, number, itemsToShift, 0, size - number);
        size = number + 1;
        flats[number] = flat;
        for(Flat itemToAdd : itemsToShift){
            add(itemToAdd);
        }
    }

    private void add(Space flat)
    {
        Space[] newFlats = new Space[size + 1];
        System.arraycopy(flats, 0, newFlats, 0, size);
        newFlats[size] = flat;
        flats = newFlats;
        size++;
    }

    public void remove(int number) {
        testNumber(number);
        Space[] newFlats = new Space[size - 1];
        System.arraycopy(flats,0,newFlats,0, number);
        System.arraycopy(flats, number + 1, newFlats, number, size - number - 1);
        flats = newFlats;
        size--;
    }

    private void testNumber(int number){
        if(number >= size){
            throw new SpaceIndexOutOfBoundsException("Floor don't have enough flats");
        }
        if(number < 0){
            throw new SpaceIndexOutOfBoundsException("Flats numbers starts on 0");
        }
    }

    public Space getBestSpace(){
        int number = 0;
        for (int i = 0; i < size; i++){
            if(flats[i].getArea() > flats[number].getArea()){
                number = i;
            }
        }
        return flats[number];
    }

    public String toString(){
        StringBuilder output = new StringBuilder("Dwelling floor: ");
        output.append(flats.length).append(" spaces\n");
        for(Space space : flats){
            output.append(space.toString()).append("\n");
        }
        return output.toString();
    }
}

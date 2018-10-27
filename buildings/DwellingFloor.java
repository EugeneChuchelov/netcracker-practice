package buildings;

import buildings.Exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;

public class DwellingFloor implements Floor, Serializable, Cloneable {
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
        this.flats = new Space[flats.length];
        System.arraycopy(flats, 0, this.flats, 0, flats.length);
        size = flats.length;
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

    @Override
    public String toString(){
        StringBuilder output = new StringBuilder("Dwelling floor (");
        output.append(flats.length);
        for(Space space : flats){
            output.append(", ").append(space.toString());
        }
        output.append(")");
        return output.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(obj instanceof DwellingFloor){
            if(((DwellingFloor) obj).size == this.size){
                for(int i = 0; i < size; i++){
                    if(!((DwellingFloor) obj).getSpace(i).equals(this.getSpace(i))){
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
        int hash = size;
        for(Space space : flats){
            hash ^= space.hashCode();
        }
        return hash;
    }

    @Override
    public Object clone() {
        Object result;
        try{
            result = super.clone();
            ((DwellingFloor)result).flats = new Space[size];
            for(int i = 0; i < size; i++){
                ((DwellingFloor) result).setSpace(i, (Space) getSpace(i).clone());
            }
            return result;
        } catch (CloneNotSupportedException e) {
            System.err.println("Dwelling floor can't be cloned");

        }
        return null;
    }
}

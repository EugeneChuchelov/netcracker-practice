package buildings.dwelling;

import buildings.exceptions.FloorIndexOutOfBoundsException;
import buildings.interfaces.Building;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import buildings.utils.Utils;

import java.io.Serializable;

public class Dwelling implements Building, Serializable, Cloneable {
    private Floor[] floors;

    public Dwelling(int floorsQuantity, int... flatsOnFloor){
        floors = new Floor[floorsQuantity];
        for(int i = 0; i < floors.length; i++){
            floors[i] = new DwellingFloor(flatsOnFloor[i]);
        }
    }

    public Dwelling(Floor... floors) {
        this.floors = new Floor[floors.length];
        System.arraycopy(floors, 0, this.floors, 0, floors.length);
    }

    public int getSize(){
        return floors.length;
    }

    public int getSpacesQuantity(){
        int flatsQuantity = 0;
        for(Floor floor : this){
            flatsQuantity += floor.getSize();
        }
        return flatsQuantity;
    }

    public float getAreaTotal(){
        float totalArea = 0;
        for(Floor floor : this){
            totalArea += floor.getAreaTotal();
        }
        return totalArea;
    }

    public int getRoomsTotal(){
        int totalRoomsQuantity = 0;
        for(Floor floor : this){
            totalRoomsQuantity += floor.getRoomsTotal();
        }
        return totalRoomsQuantity;
    }

    public Floor[] toArray() {
        return floors;
    }

    public Floor[] getFloors(){
        return floors;
    }

    public Floor getFloor(int number){
        testNumber(number);
        return floors[number];
    }

    public void setFloor(int number, Floor floor){
        testNumber(number);
        floors[number] = floor;
    }

    private void testNumber(int number){
        if(number >= floors.length){
            throw new FloorIndexOutOfBoundsException("Building don't have enough floors");
        }
        if(number < 0){
            throw new FloorIndexOutOfBoundsException("Floors numbers starts on 0");
        }
    }

    public Space getSpace(int number){
        int[] floorAndNumber = getNumberOnFloor(number);
        return getFloor(floorAndNumber[0]).getSpace(floorAndNumber[1]);
    }

    private int[] getNumberOnFloor(int number){
        int[] floorAndNumber = new int[2];
        int i =0;
        for (Floor floor : this) {
            if (number < floor.getSize()) {
                floorAndNumber[0] = i;
                floorAndNumber[1] = number;
                break;
            } else {
                number -= floor.getSize();
                i++;
            }
        }
        return floorAndNumber;
    }

    public void setSpace(int number, Space flat){
        int[] floorAndNumber = getNumberOnFloor(number);
        getFloor(floorAndNumber[0]).setSpace(floorAndNumber[1], flat);
    }

    public void addSpace(int number, Space flat){
        int[] floorAndNumber = getNumberOnFloor(number);
        getFloor(floorAndNumber[0]).add(floorAndNumber[1], flat);
    }

    public void removeSpace(int number){
        int[] floorAndNumber = getNumberOnFloor(number);
        getFloor(floorAndNumber[0]).remove(floorAndNumber[1]);
    }

    public Space getBestSpace(){
        int number = 0;
        for (int i = 0; i < getSpacesQuantity(); i++){
            if(getSpace(i).getArea() > getSpace(number).getArea()){
                number = i;
            }
        }
        return getSpace(number);
    }

    public Space[] getSpacesSorted(){
        Space[] flats = new Space[getSpacesQuantity()];
        int z = 0;
        for(Floor floor : this){
            System.arraycopy(floor.toArray(), 0, flats, z, floor.getSize());
            z = floor.getSize();
        }

        return Utils.sortByArea(flats);
    }

    @Override
    public String toString(){
        StringBuilder output = new StringBuilder("Dwelling building (");
        output.append(floors.length);
        for(Floor floor : this){
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
        if(obj instanceof Dwelling){
            if(((Dwelling) obj).floors.length == this.floors.length){
                for(int i = 0; i < floors.length; i++){
                    if(!((Dwelling) obj).getFloor(i).equals(this.getFloor(i))){
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
        int hash = floors.length;
        for(Floor floor : this){
            hash ^= floor.hashCode();
        }
        return hash;
    }

    @Override
    public Object clone() {
        Object result;
        try{
            result = super.clone();
            ((Dwelling)result).floors = new Floor[floors.length];
            for(int i = 0; i < floors.length; i++){
                ((Dwelling) result).setFloor(i, (Floor) getFloor(i).clone());
            }
            return result;
        } catch (CloneNotSupportedException e) {
            System.err.println("Dwelling can't be cloned");

        }
        return null;
    }

    @Override
    public java.util.Iterator<Floor> iterator() {
        return new Iterator();
    }

    class Iterator implements java.util.Iterator<Floor>{
        int cursor;

        public Iterator() {
            this.cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return cursor < getSize();
        }

        @Override
        public Floor next() {
            return getFloor(cursor++);
        }
    }
}

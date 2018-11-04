package buildings.office;

import buildings.interfaces.Building;
import buildings.exceptions.FloorIndexOutOfBoundsException;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import buildings.utils.Utils;

import java.io.Serializable;

public class OfficeBuilding implements Building, Serializable, Cloneable {
    private class ListNode{
        ListNode next;
        ListNode previous;
        Floor value;
        ListNode(){}
        ListNode(Floor value){
            this.value = value;
        }
    }

    private int size;
    private ListNode head;

    public OfficeBuilding(int floors, int[] officesOnFloors){
        for(int i = 0; i < floors; i++){
            addNode(i, new ListNode(new OfficeFloor(officesOnFloors[i])));
        }
    }

    public OfficeBuilding(Floor[] floors){
        for(int i = 0; i< floors.length; i++){
            addNode(i, new ListNode(floors[i]));
        }
    }

    private ListNode getNode(int index){
        ListNode current = head;
        for(int i = 0; i < index; i++){
            current = current.next;
        }
        return current;
    }

    private void addNode(int index, ListNode node){
        if(index == 0){
            if(head != null){
                node.next = head;
                head.previous = node;
            }
            head = node;
        } else if(index == size){
            getNode(index - 1).next = node;
            node.previous = getNode(index - 1);
        } else {
            getNode(index - 1).next = node;
            node.previous = getNode(index - 1);
            node.next = getNode(index + 1);
            getNode(index + 1).previous = node;
        }
        size++;
    }

    private void removeNode (int index){
        if(index == 0){
            head = head.next;
        } else if(index == size){
            getNode(index - 1).next = null;
        } else {
            getNode(index - 1).next = getNode(index + 1);
            getNode(index + 1).previous = getNode(index - 1);
        }
        size--;
    }

    public int getSize(){
        return size;
    }

    public int getSpacesQuantity(){
        int quantity = 0;
        for(Floor floor : this){
            quantity += floor.getSize();
        }
        return quantity;
    }

    public float getAreaTotal(){
        float area = 0;
        for(Floor floor : this){
            area += floor.getAreaTotal();
        }
        return area;
    }

    public int getRoomsTotal(){
        int rooms = 0;
        for(Floor floor : this){
            rooms += floor.getRoomsTotal();
        }
        return rooms;
    }

    public Floor[] toArray(){
        Floor[] floors = new Floor[size];
        int i = 0;
        for(Floor floor : this) {
            floors[i] = floor;
            i++;
        }
        return floors;
    }

    public Floor getFloor(int number){
        testNumber(number);
        return getNode(number).value;
    }

    public void setFloor(int number, Floor floor){
        testNumber(number);
        getNode(number).value = floor;
    }

    private void testNumber(int number){
        if(number >= size){
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

    public void setSpace(int number, Space office){
        int[] floorAndNumber = getNumberOnFloor(number);
        getFloor(floorAndNumber[0]).setSpace(floorAndNumber[1], office);
    }

    public void addSpace(int number, Space office){
        int[] floorAndNumber = getNumberOnFloor(number);
        getFloor(floorAndNumber[0]).add(floorAndNumber[1], office);
    }

    public void removeSpace(int number){
        int[] floorAndNumber = getNumberOnFloor(number);
        getFloor(floorAndNumber[0]).remove(floorAndNumber[1]);
    }

    private int[] getNumberOnFloor(int number){
        int[] floorAndNumber = new int[2];
        int i = 0;
        for(Floor floor : this) {
            if(number <= floor.getSize()){
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

    public Space getBestSpace(){
        Space best = new Office(0, 0);
        for(Floor floor : this) {
            if(floor.getBestSpace().getArea() > best.getArea()){
                best = floor.getBestSpace();
            }
        }
        return best;
    }

    public Space[] getSpacesSorted(){
        Space[] offices = new Space[getSpacesQuantity()];
        int z = 0;
        for(Floor floor : this) {
            System.arraycopy(floor.toArray(), 0, offices, z, floor.getSize());
            z = floor.getSize();
        }

        return Utils.sortByArea(offices);
    }

    @Override
    public String toString(){
        StringBuilder output = new StringBuilder("Office building (");
        output.append(size);
        for(Floor floor : this) {
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
        if(obj instanceof OfficeBuilding){
            if(((OfficeBuilding) obj).size == this.size){
                for(int i = 0; i < size; i++){
                    if(!((OfficeBuilding) obj).getFloor(i).equals(this.getFloor(i))){
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
        for(Floor floor : this) {
            hash ^= floor.hashCode();
        }
        return hash;
    }

    @Override
    public Object clone() {
        Object result;
        try{
            result = super.clone();
            ((OfficeBuilding)result).head = null;
            ((OfficeBuilding)result).size = 0;
            for(int i = 0; i < size; i++){
                ((OfficeBuilding) result).addNode(i, new ListNode((Floor) getFloor(i).clone()));
            }
            return result;
        } catch (CloneNotSupportedException e) {
            System.err.println("Office floor can't be cloned");

        }
        return null;
    }

    @Override
    public Iterator iterator() {
        return new Iterator();
    }

    class Iterator implements java.util.Iterator<Floor>{
        ListNode cursor;

        public Iterator() {
            this.cursor = head;
        }

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public Floor next() {
            Floor next = cursor.value;
            cursor = cursor.next;
            return next;
        }
    }
}

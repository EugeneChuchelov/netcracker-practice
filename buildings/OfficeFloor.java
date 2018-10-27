package buildings;

import buildings.Exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;

public class OfficeFloor implements Floor, Serializable, Cloneable {
    private class ListNode{
        ListNode next;
        Space value;
        ListNode(){}
        ListNode(Space value){
            this.value = value;
        }
    }

    private int size;
    private ListNode head;

    public OfficeFloor(int officesQuantity){
        for (int i = 0; i < officesQuantity; i++){
            addNode(i, new ListNode(new Office()));
        }
    }

    public OfficeFloor(Space[] offices){
        for (int i = 0; i < offices.length; i++){
            addNode(i, new ListNode(offices[i]));
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
            node.next = head;
            head = node;
        } else if(index == size){
            getNode(index - 1).next = node;
        } else {
            node.next = getNode(index);
            getNode(index - 1).next = node;
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
        }
        size--;
    }

    public int getSize(){
        return size;
    }

    public float getAreaTotal(){
        float area = 0;
        for(ListNode node = head; node != null; node = node.next){
            area += node.value.getArea();
        }
        return area;
    }

    public int getRoomsTotal(){
        int rooms = 0;
        for(ListNode node = head; node != null; node = node.next){
            rooms += node.value.getRoomsQuantity();
        }
        return rooms;
    }

    public Space[] toArray(){
        Space[] array = new Space[size];
        int i = 0;
        for(ListNode node = head; node != null; node = node.next){
            array[i] = node.value;
            i++;
        }
        return array;
    }

    public Space getSpace(int number){
        testNumber(number);
        return getNode(number).value;
    }

    public void setSpace(int number, Space office){
        testNumber(number);
        getNode(number).value = office;
    }

    public void add(int number, Space office){
        testNumber(number);
        addNode(number, new ListNode(office));
    }

    public void remove(int number){
        testNumber(number);
        removeNode(number);
    }

    private void testNumber(int number){
        if(number > size){
            throw new SpaceIndexOutOfBoundsException("Floor don't have enough offices");
        }
        if(number < 0){
            throw new SpaceIndexOutOfBoundsException("Offices numbers starts on 0");
        }
    }

    public Space getBestSpace(){
        Space bestOffice = head.value;
        for(ListNode node = head; node != null; node = node.next){
            if(node.value.getArea() > bestOffice.getArea()){
                bestOffice = node.value;
            }
        }
        return bestOffice;
    }

    @Override
    public String toString(){
        StringBuilder output = new StringBuilder("Office floor (");
        output.append(size);
        for(ListNode node = head; node != null; node = node.next){
            output.append(", ").append(node.value.toString());
        }
        output.append(")");
        return output.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(obj instanceof OfficeFloor){
            if(((OfficeFloor) obj).size == this.size){
                for(int i = 0; i < size; i++){
                    if(!((OfficeFloor) obj).getSpace(i).equals(this.getSpace(i))){
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
        for(ListNode node = head; node != null; node = node.next){
            hash ^= node.value.hashCode();
        }
        return hash;
    }

    @Override
    public Object clone() {
        Object result;
        try{
            result = super.clone();
            ((OfficeFloor)result).head = null;
            ((OfficeFloor)result).size = 0;
            for(int i = 0; i < size; i++){
                ((OfficeFloor) result).add(i, (Space) getSpace(i).clone());
            }
            return result;
        } catch (CloneNotSupportedException e) {
            System.err.println("Office floor can't be cloned");

        }
        return null;
    }
}

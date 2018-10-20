package buildings;

import buildings.Exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;

public class OfficeFloor implements Floor, Serializable {
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

    public String toString(){
        StringBuilder output = new StringBuilder("Office floor: ");
        output.append(size).append(" spaces\n");
        for(ListNode node = head; node != null; node = node.next){
            output.append(node.value.toString()).append("\n");
        }
        return output.toString();
    }
}

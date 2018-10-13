package buildings;

public class OfficeFloor {
    private class ListNode{
        ListNode next;
        Office value;
        ListNode(){}
        ListNode(Office value){
            this.value = value;
        }
    }

    private int size;
    private ListNode head;
    //private ListNode tail;

    public OfficeFloor(int officesQuantity){
        for (int i = 0; i < officesQuantity; i++){
            add(new ListNode(new Office()), i);
        }
    }

    public OfficeFloor(Office[] offices){
        for (int i = 0; i < offices.length; i++){
            add(new ListNode(offices[i]), i);
        }
    }

    private ListNode getNode(int index){
        ListNode current = head;
        for(int i = 0; i < index; i++){
            current = current.next;
        }
        return current;
    }

    private void add(ListNode node, int index){
        if(index == 0){
            node.next = head;
            head = node;
        } else if(index == size){
            getNode(index - 1).next = node;
            //tail.next = newNode;
            //tail = newNode;
        } else {
            getNode(index - 1).next = node;
            node.next = getNode(index + 1);
        }
        size++;
    }

    private void removeNode (int index){
        if(index == 0){
            head = head.next;
        } else if(index == size){
            getNode(index - 1).next = null;
            //tail = getNode(index - 1);
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
            area += node.value.getOfficeArea();
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

    public Office[] toArray(){
        Office[] array = new Office[size];
        int i = 0;
        for(ListNode node = head; node != null; node = node.next){
            array[i] = node.value;
            i++;
        }
        return array;
    }

    public Office getOffice(int number){
        testNumber(number);
       return getNode(number).value;
    }

    public void setOffice(Office office, int number){
        testNumber(number);
        getNode(number).value = office;
    }

    public void addOffice(Office office, int number){
        testNumber(number);
        add(new ListNode(office), number);
    }

    public void remove(int number){
        testNumber(number);
        removeNode(number);
    }

    private void testNumber(int number){
        if(number >= size){
            throw new SpaceIndexOutOfBoundsException("Floor don't have enough offices");
        }
        if(number < 0){
            throw new SpaceIndexOutOfBoundsException("Offices numbers starts on 0");
        }
    }

    public Office getBestSpace(){
        Office bestOffice = head.value;
        for(ListNode node = head; node != null; node = node.next){
            if(node.value.getOfficeArea() > bestOffice.getOfficeArea()){
                bestOffice = node.value;
            }
        }
        return bestOffice;
    }
}

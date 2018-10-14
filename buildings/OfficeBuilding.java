package buildings;

public class OfficeBuilding implements Building {
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
            //tail.next = newNode;
            //tail = newNode;
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
        for(ListNode node = head; node != null; node = node.next){
            quantity += node.value.getSize();
        }
        return quantity;
    }

    public float getAreaTotal(){
        float area = 0;
        for(ListNode node = head; node != null; node = node.next){
            area += node.value.getAreaTotal();
        }
        return area;
    }

    public int getRoomsTotal(){
        int rooms = 0;
        for(ListNode node = head; node != null; node = node.next){
            rooms += node.value.getRoomsTotal();
        }
        return rooms;
    }

    public Floor[] toArray(){
        Floor[] floors = new Floor[size];
        int i = 0;
        for(ListNode node = head; node != null; node = node.next){
            floors[i] = node.value;
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
        for(ListNode node = head; node != null; node = node.next){
            if(number <= node.value.getSize()){
                floorAndNumber[0] = i;
                floorAndNumber[1] = number;
                break;
            } else {
                number -= node.value.getSize();
                i++;
            }
        }
        return floorAndNumber;
    }

    public Space getBestSpace(){
        Space best = new Office(0,0);
        for(ListNode node = head; node != null; node = node.next){
            if(node.value.getBestSpace().getArea() > best.getArea()){
                best = node.value.getBestSpace();
            }
        }
        return best;
    }

    public Space[] getSpacesSorted(){
        Space[] offices = new Space[getSpacesQuantity()];
        int z = 0;
        for(ListNode node = head; node != null; node = node.next){
            System.arraycopy(node.value.toArray(), 0, offices, z, node.value.getSize());
            z = node.value.getSize();
        }

        return Utils.sortByArea(offices);
    }
}

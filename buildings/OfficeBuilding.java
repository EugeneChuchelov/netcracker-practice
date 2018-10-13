package buildings;

public class OfficeBuilding {
    private class ListNode{
        ListNode next;
        ListNode previous;
        OfficeFloor value;
        ListNode(){}
        ListNode(OfficeFloor value){
            this.value = value;
        }
    }

    private int size;
    private ListNode head;

    public OfficeBuilding(int floors, int[] officesOnFloors){
        for(int i = 0; i < floors; i++){
            add(new ListNode(new OfficeFloor(officesOnFloors[i])), i);
        }
    }

    public OfficeBuilding(OfficeFloor[] floors){
        for(int i = 0; i< floors.length; i++){
            add(new ListNode(floors[i]), i);
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
            //tail = getNode(index - 1);
        } else {
            getNode(index - 1).next = getNode(index + 1);
            getNode(index + 1).previous = getNode(index - 1);
        }
        size--;
    }

    public int getSize(){
        return size;
    }

    public int getOfficesQuantity(){
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

    public OfficeFloor[] toArray(){
        OfficeFloor[] floors = new OfficeFloor[size];
        int i = 0;
        for(ListNode node = head; node != null; node = node.next){
            floors[i] = node.value;
            i++;
        }
        return floors;
    }

    public OfficeFloor getFloor(int number){
        testNumber(number);
        return getNode(number).value;
    }

    public void setFloor(OfficeFloor floor, int number){
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

    public Office getOffice(int number){
        int[] floorAndNumber = getNumberOnFloor(number);
        return getFloor(floorAndNumber[0]).getOffice(floorAndNumber[1]);
    }

    public void setOffice(Office office, int number){
        int[] floorAndNumber = getNumberOnFloor(number);
        getFloor(floorAndNumber[0]).setOffice(office, floorAndNumber[1]);
    }

    public void addOffice(Office office, int number){
        int[] floorAndNumber = getNumberOnFloor(number);
        getFloor(floorAndNumber[0]).addOffice(office, floorAndNumber[1]);
    }

    public void removeOffice(int number){
        int[] floorAndNumber = getNumberOnFloor(number);
        getFloor(floorAndNumber[0]).remove(floorAndNumber[1]);
    }

    private int[] getNumberOnFloor(int number){
        int[] floorAndNumber = new int[2];
        int i = 0;
        for(ListNode node = head; node != null; node = node.next){
            if(number < node.value.getSize()){
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

    public Office getBestSpace(){
        Office best = new Office(0,0);
        for(ListNode node = head; node != null; node = node.next){
            if(node.value.getBestSpace().getOfficeArea() > best.getOfficeArea()){
                best = node.value.getBestSpace();
            }
        }
        return best;
    }

    public Office[] getSortedOffices(){
        Office[] offices = new Office[getOfficesQuantity()];
        int z = 0;
        for(ListNode node = head; node != null; node = node.next){
            System.arraycopy(node.value.toArray(), 0, offices, z, node.value.getSize());
            z = node.value.getSize();
        }

        Office swapBuf;
        for(int i = offices.length - 1; i > 0; i--)
        {
            for(int j = 0; j < i; j++)
            {
                if(offices[j].getOfficeArea() < offices[j+1].getOfficeArea())
                {
                    swapBuf = offices[j];
                    offices[j] = offices[j+1];
                    offices[j+1] = swapBuf;
                }
            }
        }
        return offices;
    }
}

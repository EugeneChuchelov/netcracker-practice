package buildings;

public class Dwelling implements Building {
    private Floor[] floors;

    public Dwelling(int floorsQuantity){
        floors = new DwellingFloor[floorsQuantity];
        for(int i =0; i < floorsQuantity; i++){
            floors[i] = new DwellingFloor();
        }
    }

    public Dwelling(int[] flatsOnFloor){
        floors = new DwellingFloor[flatsOnFloor.length];
        for(int i = 0; i < floors.length; i++){
            floors[i] = new DwellingFloor(flatsOnFloor[i]);
        }
    }

    public Dwelling(DwellingFloor[] floors) {
        this.floors = floors;
    }

    public int getSize(){
        return floors.length;
    }

    public int getSpacesQuantity(){
        int flatsQuantity = 0;
        for(Floor floor : floors){
            flatsQuantity += floor.getSize();
        }
        return flatsQuantity;
    }

    public float getAreaTotal(){
        float totalArea = 0;
        for(Floor floor : floors){
            totalArea += floor.getAreaTotal();
        }
        return totalArea;
    }

    public int getRoomsTotal(){
        int totalRoomsQuantity = 0;
        for(Floor floor : floors){
            totalRoomsQuantity += floor.getRoomsTotal();
        }
        return totalRoomsQuantity;
    }

    public Floor[] toArray() {
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
        for (Floor floor : floors) {
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
        for(Floor floor : floors){
            System.arraycopy(floor.toArray(), 0, flats, z, floor.getSize());
            z = floor.getSize();
        }

        Space swapBuf;
        for(int i = flats.length - 1; i > 0; i--)
        {
            for(int j = 0; j < i; j++)
            {
                if(flats[j].getArea() < flats[j+1].getArea())
                {
                    swapBuf = flats[j];
                    flats[j] = flats[j+1];
                    flats[j+1] = swapBuf;
                }
            }
        }
        return flats;
    }
}

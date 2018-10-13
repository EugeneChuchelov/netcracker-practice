package buildings;

public class Dwelling {
    private DwellingFloor[] floors;

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

    public int getFloorsQuantity(){
        return floors.length;
    }

    public int getFlatsQuantity(){
        int flatsQuantity = 0;
        for(DwellingFloor floor : floors){
            flatsQuantity += floor.getFlatsQuantity();
        }
        return flatsQuantity;
    }

    public float getTotalArea(){
        float totalArea = 0;
        for(DwellingFloor floor : floors){
            totalArea += floor.getTotalArea();
        }
        return totalArea;
    }

    public int getTotalRoomsQuantity(){
        int totalRoomsQuantity = 0;
        for(DwellingFloor floor : floors){
            totalRoomsQuantity += floor.getTotalRoomsQuantity();
        }
        return totalRoomsQuantity;
    }

    public DwellingFloor[] getFloors() {
        return floors;
    }

    public DwellingFloor getFloor(int number){
        testNumber(number);
        return floors[number];
    }

    public void setFloor(int number, DwellingFloor floor){
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

    public Flat getFlat(int number){
        int[] floorAndNumber = getNumberOnFloor(number);
        return getFloor(floorAndNumber[0]).getFlat(floorAndNumber[1]);
    }

    private int[] getNumberOnFloor(int number){
        int[] floorAndNumber = new int[2];
        int i =0;
        for (DwellingFloor floor : floors) {
            if (number < floor.getFlatsQuantity()) {
                floorAndNumber[0] = i;
                floorAndNumber[1] = number;
                break;
            } else {
                number -= floor.getFlatsQuantity();
                i++;
            }
        }
        return floorAndNumber;
    }

    public void setFlat(int number, Flat flat){
        int[] floorAndNumber = getNumberOnFloor(number);
        getFloor(floorAndNumber[0]).setFlat(floorAndNumber[1], flat);
    }

    public void addFlat(int number, Flat flat){
        int[] floorAndNumber = getNumberOnFloor(number);
        getFloor(floorAndNumber[0]).add(floorAndNumber[1], flat);
    }

    public void removeFlat(int number){
        int[] floorAndNumber = getNumberOnFloor(number);
        getFloor(floorAndNumber[0]).remove(floorAndNumber[1]);
    }

    public Flat getBestSpace(){
        int number = 0;
        for (int i = 0; i < getFlatsQuantity(); i++){
            if(getFlat(i).getFlatArea() > getFlat(number).getFlatArea()){
                number = i;
            }
        }
        return getFlat(number);
    }

    public Flat[] getSortedFlats(){
        Flat[] flats = new Flat[getFlatsQuantity()];
        int z = 0;
        for(DwellingFloor floor : floors){
            System.arraycopy(floor.getFlats(), 0, flats, z, floor.getFlatsQuantity());
            z = floor.getFlatsQuantity();
        }

        Flat swapBuf;
        for(int i = flats.length - 1; i > 0; i--)
        {
            for(int j = 0; j < i; j++)
            {
                if(flats[j].getFlatArea() < flats[j+1].getFlatArea())
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

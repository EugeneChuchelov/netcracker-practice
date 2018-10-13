package buildings;

public class DwellingFloor {
    private Flat[] flats;
    private static final int DEFAULT_FLATS = 4;
    private int size;

    public DwellingFloor(){
        this(DEFAULT_FLATS);
    }

    public DwellingFloor(int flatsQuantity){
        flats = new Flat[flatsQuantity];
        for(int i = 0; i < flatsQuantity; i++){
            flats[i] = new Flat();
        }
    }

    public DwellingFloor(Flat[] flats) {
        flats = new Flat[flats.length];
        System.arraycopy(flats, 0, this.flats, 0, flats.length);
    }

    public int getFlatsQuantity(){
        return size;
    }

    public float getTotalArea(){
        float totalArea = 0;
        for(Flat flat : flats){
            totalArea += flat.getFlatArea();
        }
        return totalArea;
    }

    public int getTotalRoomsQuantity(){
        int totalRoomsQuantity = 0;
        for(Flat flat : flats){
            totalRoomsQuantity += flat.getRoomsQuantity();
        }
        return totalRoomsQuantity;
    }

    public Flat[] getFlats() {
        return flats;
    }

    public Flat getFlat(int number){
        testNumber(number);
        return flats[number];
    }

    public void setFlat(int number, Flat flat){
        testNumber(number);
        flats[number] = flat;
    }

    public void add(int number, Flat flat){
        testNumber(number);
        Flat[] itemsToShift = new Flat[size - number];
        System.arraycopy(flats, number, itemsToShift, 0, size - number);
        size = number + 1;
        flats[number] = flat;
        for(Flat itemToAdd : itemsToShift){
            add(itemToAdd);
        }
    }

    private void add(Flat flat)
    {
        Flat[] newFlats = new Flat[size + 1];
        System.arraycopy(flats, 0, newFlats, 0, size);
        newFlats[size] = flat;
        flats = newFlats;
        size++;
    }

    public void remove(int number) {
        testNumber(number);
        Flat[] newFlats = new Flat[size - 1];
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

    public Flat getBestSpace(){
        int number = 0;
        for (int i = 0; i < size; i++){
            if(flats[i].getFlatArea() > flats[number].getFlatArea()){
                number = i;
            }
        }
        return flats[number];
    }
}

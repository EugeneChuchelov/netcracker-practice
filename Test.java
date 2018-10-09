import buildings.Dwelling;
import buildings.DwellingFloor;
import buildings.Flat;

public class Test {
    public static void main(String[] args) {
        Flat flat00 = new Flat(10,1);
        Flat flat01 = new Flat(20,2);
        Flat flat10 = new Flat(30,3);
        Flat flat11 = new Flat(40,4);
        Flat newFlat = new Flat(100,4);

        DwellingFloor floor0 = new DwellingFloor(2);
        DwellingFloor floor1 = new DwellingFloor(2);

        Dwelling building = new Dwelling(2);

        floor0.add(0,flat00);
        floor0.add(1,flat01);
        floor1.add(0,flat10);
        floor1.add(1,flat11);

        building.setFloor(0,floor0);
        building.setFloor(1,floor1);

        building.addFlat(3,newFlat);
        ///building.getFlat(1).setFlatArea(16000);

        building.addFlat(2, flat00);

        building.removeFlat(3);

        //Flat[] sgadl = building.getSortedFlats();

        //for(Flat flat : sgadl){
        //    System.out.println(flat.getFlatArea());
        //}


        //System.out.println(building.getBestSpace().getFlatArea());

    }
}

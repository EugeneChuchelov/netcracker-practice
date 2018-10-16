import buildings.*;

public class Test {
    public static void main(String[] args) {
        Office o00 = new Office(10,1);
        Office o01 = new Office(20,2);
        Office o10 = new Office(30,3);
        Office o11 = new Office(40,4);
        Flat f00 = new Flat(10,1);
        Flat f01 = new Flat(20,2);
        Flat f10 = new Flat(30,3);
        Flat f11 = new Flat(40,4);

        Office od = new Office(50,5);
        Flat fd = new Flat(50,5);

        OfficeFloor of0 = new OfficeFloor(2);
        OfficeFloor of1 = new OfficeFloor(2);
        DwellingFloor df0 = new DwellingFloor(2);
        DwellingFloor df1 = new DwellingFloor(2);

        int[] qw = {2, 2};
        OfficeBuilding ob = new OfficeBuilding(2, qw);
        Dwelling db = new Dwelling(2, qw);

        of0.add(0, o00);
        of0.add(1, o01);
        of1.add(0, o10);
        of1.add(1, o11);
        ob.setFloor(0, of0);
        ob.setFloor(1, of1);

        ob.addSpace(4, od);

        df0.add(0,f00);
        df0.add(1,f01);
        df1.add(0,f10);
        df1.add(1,f11);
        db.setFloor(0,df0);
        db.setFloor(1,df1);

        db.addSpace(4,fd);
        db.addSpace(0, o11);
        db.addSpace(3, o10);

        ob.setFloor(1, df1);

        System.out.println(db.toString());
        System.out.println(ob.toString());
    }
}

import buildings.*;

public class Test {
    public static void main(String[] args) {
        Space f11 = new Flat(4, 40);
        Space o11 = new Office(40,4);
        Space f00 = new Flat(10,1);
        Space f01 = new Flat(20,2);
        Space f10 = new Flat(30,3);
        Space[] hsdf = {f11, o11, f00, f01, f10};
        Floor f = new OfficeFloor(hsdf);
        Floor asd = new DwellingFloor(hsdf);
        Floor[] hnh = {f, asd};
        Building sdag = new OfficeBuilding(hnh);
        Building qewt = (OfficeBuilding) sdag.clone();
        System.out.println("ewq");
        /*Space o00 = new Office(10,1);
        Space o01 = new Office(20,2);
        Space o10 = new Office(30,3);
        Space o11 = new Office(40,4);
        Space f00 = new Flat(10,1);
        Space f01 = new Flat(20,2);
        Space f10 = new Flat(30,3);
        Space f11 = new Flat(40,4);

        Space od = new Office(50,5);
        Space fd = new Flat(50,5);

        Floor of0 = new OfficeFloor(2);
        Floor of1 = new OfficeFloor(2);
        Floor df0 = new DwellingFloor(2);
        Floor df1 = new DwellingFloor(2);

        int[] qw = {2, 2};
        Building ob = new OfficeBuilding(2, qw);
        Building db = new Dwelling(2, qw);

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

        System.out.println(db.toString());*/
    }
}

import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import buildings.interfaces.Building;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;
import buildings.utils.Buildings;

import java.io.*;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {
        Space sdaq = new Flat(4, 40);
        Space jgh = new Office(4, 40);
        Space tyu = new Flat(1, 10);
        Space nbmg = new Flat(2, 20);
        Space ytn = new Flat(3, 30);
        Space[] hsdf = {sdaq, jgh, tyu, nbmg, ytn};
        Floor f = new OfficeFloor(hsdf);
        Floor asd = new DwellingFloor(hsdf);
        asd.add(3, sdaq);
        Floor[] hnh = {f, asd};
        Building sdag = new OfficeBuilding(hnh);

        Space o00 = new Office(1, 10);
        Space o01 = new Office(2, 20);
        Space o10 = new Office(3, 30);
        Space o11 = new Office(4, 40);
        Space f00 = new Flat(1, 10);
        Space f01 = new Flat(2, 20);
        Space f10 = new Flat(3, 30);
        Space f11 = new Flat(4, 40);

        Space od = new Office(50, 5);
        Space fd = new Flat(50, 5);

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

        df0.add(0, f00);
        df0.add(1, f01);
        df1.add(0, f10);
        df1.add(1, f11);
        db.setFloor(0, df0);
        db.setFloor(1, df1);

        db.addSpace(4, fd);
        db.addSpace(0, o11);
        db.addSpace(3, o10);

        ob.setFloor(1, df1);
        PrintWriter wr = new PrintWriter(new FileOutputStream("F:\\Учёба\\NC\\netcracker-practice\\src\\buildings\\net\\client\\1.txt"));
        Buildings.writeBuilding(ob, wr);
        wr.close();

        FileReader reader = new FileReader(new File("qwe.txt"));
        Scanner scanner = new Scanner(new File("qwe.txt"));
        Building asdq = Buildings.readBuilding(scanner);
        System.out.println(asdq.toString());

        //System.out.println(db.toString());
    }
}

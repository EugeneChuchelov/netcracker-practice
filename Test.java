import buildings.*;
import buildings.Exceptions.InexchangeableFloorsException;

import java.io.*;

public class Test {
    public static void main(String[] args) {
        Space o00 = new Office(10,1);
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

        System.out.println(db.toString());
        System.out.println("------------");

        /*try(OutputStream out  = new FileOutputStream("test.bin");
            InputStream in = new FileInputStream("test.bin")){
            //Buildings.serializeBuilding(db, out);
            //Building ff = Buildings.deserializeBuilding(in);
            //System.out.println(ff.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        try(OutputStream out  = new FileOutputStream("test.bin");
            InputStream in = new FileInputStream("test.bin");
            PrintWriter print = new PrintWriter(new BufferedWriter(new FileWriter("out.txt")));
            BufferedReader read = new BufferedReader(new FileReader("out.txt"))){
            //Buildings.writeBuilding(db, print);
            Buildings.writeBuildingFormat(db, print);
            //Building ff = Buildings.readBuilding(read);
            //Buildings.outputBuilding(db, out);
            //db = Buildings.inputBuilding(in);
            //Building ff = Buildings.inputBuilding(in);
            //System.out.println(ff.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(db.toString());

        /*Floor ef0 = new DwellingFloor(1);
        Space as = new Flat(250,1);
        ef0.setSpace(0,as);
        Floor ef1 = new OfficeFloor(1);
        db.setFloor(0, ef1);
        ob.setFloor(0, ef0);

        System.out.println(db.toString());
        System.out.println(ob.toString());
        System.out.println("------------");

        try {
            PlacementExchanger.exchangeBuildingFloors(ob, 0, db, 0);
        } catch (InexchangeableFloorsException e) {
            e.printStackTrace();
        }

        System.out.println(db.toString());
        System.out.println(ob.toString());*/
    }
}

import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import buildings.interfaces.Building;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import buildings.utils.Buildings;
import swingGUI.Window;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Mane {
    public static void main(String[] args) throws IOException {
        //Window window = new Window();
        //window.setVisible(true);
        /*
        Scanner scanner = new Scanner(System.in);
        System.out.println("Class name: ");
        String className = scanner.nextLine();
        System.out.println("Method name: ");
        String methodName = scanner.nextLine();
        System.out.println("Arguments: ");
        double argument = Double.parseDouble(scanner.nextLine());

        try {
            Class loadedClass = Class.forName(className);
            Method method = loadedClass.getMethod(methodName, Double.TYPE);
            Object result = method.invoke(null, argument);
            System.out.println(result);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        */
        /*
        Space sp1 = new Flat(2, 40);
        Space sp2 = new Flat(3, 60);
        Space sp3 =new Flat(4, 80);
        Space sp4 = new Flat (5, 100);
        Space[] spa1 = {sp1, sp2};
        Space[] spa2 = {sp3, sp4};
        Floor fl1 = new DwellingFloor(spa1);
        Floor fl2 = new DwellingFloor(spa2);
        Floor[] fla = {fl1, fl2};
        Building bd = new Dwelling(fla);

        FileOutputStream fos = new FileOutputStream(new File("rfbt.bin"));
        FileWriter fwt = new FileWriter(new File("rfbt.txt"));

        Buildings.outputBuilding(bd, fos);
        Buildings.writeBuilding(bd, fwt);
        */
        Building rbd1 = null;
        Building rbd2 = null;
        try {
            Class spaceClass = Class.forName("buildings.office.Office");
            Class floorClass = Class.forName("buildings.dwelling.hotel.HotelFloor");
            Class buildingClass = Class.forName("buildings.dwelling.Dwelling");

            FileInputStream fis = new FileInputStream(new File("rfbt.bin"));
            rbd1 = Buildings.inputBuilding(fis, spaceClass, floorClass, buildingClass);
            System.out.println(rbd1.toString());
            System.out.println();
            FileReader frd = new FileReader(new File("rfbt.txt"));
            rbd2 = Buildings.readBuilding(frd, spaceClass, floorClass, buildingClass);
            System.out.println(rbd2.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        assert rbd1 != null;
        System.out.println();
        Floor[] floors = rbd2.toArray();
        Space[] spaces = floors[1].toArray();
        for(Floor floor : floors){
            System.out.println(floor.toString());
        }
        floors = Buildings.sort(floors, (Floor o1, Floor o2) ->
                Float.compare(o2.getAreaTotal(), o1.getAreaTotal()));
        System.out.println();
        for(Floor floor : floors){
            System.out.println(floor.toString());
        }
        System.out.println();
        for (Space space : spaces ){
            System.out.println(space.toString());
        }
        spaces = Buildings.sort(spaces, (Space o1, Space o2) ->
                Float.compare(o1.getArea(), o2.getArea()));
        System.out.println();
        for (Space space : spaces ){
            System.out.println(space.toString());
        }

    }
}
//16 5 4 40.0 4 40.0 1 10.0 2 20.0 3 30.0 6 4 40.0 4 40.0 1 10.0 4 40.0 2 20.0 3 30.0 5 4 40.0 4 40.0 1 10.0 2 20.0 3 30.0 6 4 40.0 4 40.0 1 10.0 4 40.0 2 20.0 3 30.0 5 4 40.0 4 40.0 1 10.0 2 20.0 3 30.0 6 4 40.0 4 40.0 1 10.0 4 40.0 2 20.0 3 30.0 5 4 40.0 4 40.0 1 10.0 2 20.0 3 30.0 6 4 40.0 4 40.0 1 10.0 4 40.0 2 20.0 3 30.0 5 4 40.0 4 40.0 1 10.0 2 20.0 3 30.0 6 4 40.0 4 40.0 1 10.0 4 40.0 2 20.0 3 30.0 5 4 40.0 4 40.0 1 10.0 2 20.0 3 30.0 6 4 40.0 4 40.0 1 10.0 4 40.0 2 20.0 3 30.0 5 4 40.0 4 40.0 1 10.0 2 20.0 3 30.0 6 4 40.0 4 40.0 1 10.0 4 40.0 2 20.0 3 30.0 5 4 40.0 4 40.0 1 10.0 2 20.0 3 30.0 6 4 40.0 4 40.0 1 10.0 4 40.0 2 20.0 3 30.0

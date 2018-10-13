import buildings.*;

public class Test {
    public static void main(String[] args) {
        Office o00 = new Office(10,1);
        Office o01 = new Office(20,2);
        Office o10 = new Office(30,3);
        Office o11 = new Office(40,4);
        Office od = new Office(50,5);

        OfficeFloor f0 = new OfficeFloor(2);
        OfficeFloor f1 = new OfficeFloor(2);

        int[] qw = {2, 2};
        OfficeBuilding b = new OfficeBuilding(2, qw);

        f0.setOffice(o00, 0);
        f0.setOffice(o01, 1);
        f1.setOffice(o10, 0);
        f1.setOffice(o11, 1);
        b.setFloor(f0,0);
        b.setFloor(f1,1);


    }
}

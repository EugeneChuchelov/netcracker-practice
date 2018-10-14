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

        f0.setSpace(0, o00);
        f0.setSpace(1, o01);
        f1.setSpace(0, o10);
        f1.setSpace(1, o11);
        b.setFloor(0, f0);
        b.setFloor(1, f1);

        b.getSpace(3);

        b.addSpace(4, od);

        b.removeSpace(3);
    }
}

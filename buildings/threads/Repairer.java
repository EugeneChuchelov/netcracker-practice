package buildings.threads;

import buildings.interfaces.Floor;
import buildings.interfaces.Space;

public class Repairer extends Thread {
    private Floor floor;

    public Repairer(Floor floor){
        this.floor = floor;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    @Override
    public void run() {
        int i = 0;
        for (Space space : floor) {
            System.out.printf("Repairing space number %d with total area %.1f square meters\n", i, space.getArea());
            i++;
        }
        System.out.println("Repairer stopped running");
    }

    @Override
    public void interrupt() {
        System.out.println("Repairer was interrupted");
        super.interrupt();
    }
}

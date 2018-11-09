package buildings.threads;

import buildings.interfaces.Floor;
import buildings.interfaces.Space;

public class SequentialCleaner implements Runnable {
    private Floor floor;
    private QueueSemaphore semaphore;

    public SequentialCleaner(Floor floor, QueueSemaphore semaphore) {
        this.floor = floor;
        this.semaphore = semaphore;
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
            try {
                semaphore.enter(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("Cleaning space number %d with total area %.1f square meters\n", i, space.getArea());
            semaphore.leave();
            i++;
        }
        System.out.println("Cleaner stopped running");
    }
}

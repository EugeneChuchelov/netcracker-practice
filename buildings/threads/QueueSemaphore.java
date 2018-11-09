package buildings.threads;

public class QueueSemaphore {
    private String lastThread = "SequentialCleaner";
    private boolean isTaken = false;

    synchronized void enter(Runnable runnable) throws InterruptedException {
        String[] runnableClass = runnable.getClass().getName().split("\\.");
        while (isTaken || lastThread.equals(runnableClass[runnableClass.length - 1])) {
            wait();
        }
        isTaken = true;
        lastThread = runnableClass[runnableClass.length - 1];
    }

    synchronized void leave() {
        isTaken = false;
        notifyAll();
    }
}

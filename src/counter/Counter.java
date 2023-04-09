package counter;

public class Counter {
    private int counter = 0;

    public synchronized void syncIncrement() {
        counter++;
        // Synchronized block:
        /*synchronized (this) {
            counter++;
        }*/
    }

    public synchronized void syncDecrement() {
        counter--;
    }

    public int getCounter() {
        return counter;
    }
}

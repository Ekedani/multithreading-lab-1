package counter;

public class Counter {
    private int counter = 0;

    public synchronized void increment() {
        counter++;
        // Synchronized block:
        /*synchronized (this) {
            counter++;
        }*/
    }

    public synchronized void decrement() {
        counter--;
    }

    public int getCounter() {
        return counter;
    }
}

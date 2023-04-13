package counter;

public class Counter {
    protected int counter = 0;

    public synchronized void syncIncrement() {
        counter++;
        /* synchronized (this) {
            counter++;
        }*/
    }

    public synchronized void syncDecrement() {
        counter--;
        /* synchronized (this) {
            counter--;
        }*/
    }

    public void asyncIncrement() {
        counter++;
    }

    public void asyncDecrement() {
        counter--;
    }

    public int getCounter() {
        return counter;
    }
}

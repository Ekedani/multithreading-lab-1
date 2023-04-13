package counter;

import java.util.concurrent.locks.ReentrantLock;

public class LockingCounter extends Counter {
    private final ReentrantLock reentrantLock = new ReentrantLock();

    public void increment() {
        try {
            reentrantLock.lock();
            counter++;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void decrement() {
        try {
            reentrantLock.lock();
            counter--;
        } finally {
            reentrantLock.unlock();
        }
    }
}

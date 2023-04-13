package counter;

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        // Non-synchronized
        /*FunctionExecutorThread incrementThread = new FunctionExecutorThread(counter::asyncIncrement, 10000);
        FunctionExecutorThread decrementThread = new FunctionExecutorThread(counter::asyncDecrement, 10000);*/

        // Synchronized method/block
        FunctionExecutorThread incrementThread = new FunctionExecutorThread(counter::syncIncrement, 10000);
        FunctionExecutorThread decrementThread = new FunctionExecutorThread(counter::syncDecrement, 10000);

        /*LockingCounter counter = new LockingCounter();
        FunctionExecutorThread incrementThread = new FunctionExecutorThread(counter::increment, 10000);
        FunctionExecutorThread decrementThread = new FunctionExecutorThread(counter::decrement, 10000);*/

        incrementThread.start();
        decrementThread.start();

        try {
            incrementThread.join();
            decrementThread.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Final counter value: " + counter.getCounter());
    }
}

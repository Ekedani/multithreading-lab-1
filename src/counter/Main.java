package counter;

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        FunctionExecutorThread incrementThread = new FunctionExecutorThread(counter::syncIncrement, 10000);
        FunctionExecutorThread decrementThread = new FunctionExecutorThread(counter::syncDecrement, 10000);

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

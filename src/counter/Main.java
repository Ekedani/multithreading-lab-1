package counter;

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        FunctionExecutorThread incrementThread = new FunctionExecutorThread(counter::increment, 10000);
        FunctionExecutorThread decrementThread = new FunctionExecutorThread(counter::decrement, 10000);

        incrementThread.start();
        decrementThread.start();

        try {
            incrementThread.join();
            decrementThread.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Final counter value: " + counter.getCounter());

        // May be replaced with lambdas like this:
        /*Thread incrementThread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });*/
        /*Thread decrementThread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.decrement();
            }
        });*/
    }
}

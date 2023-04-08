package counter;

public class FunctionExecutorThread extends Thread {
    private final Runnable function;
    private final int iterations;

    FunctionExecutorThread(Runnable function, int iterations) {
        this.function = function;
        this.iterations = iterations;
    }

    @Override
    public void run() {
        for (int i = 0; i < iterations; i++) {
            this.function.run();
        }
    }
}

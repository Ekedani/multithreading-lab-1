package bounce;

public class BallThread extends Thread {
    private final Ball ball;
    private final Runnable callback;

    public BallThread(Ball ball, Runnable callback) {
        this.ball = ball;
        this.callback = callback;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i < 10000; i++) {
                System.out.println("Thread name = " + Thread.currentThread().getName());
                ball.move();
                if (ball.isInPocket()) {
                    ball.die();
                    callback.run();
                    return;
                }
                Thread.sleep(5);
            }
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
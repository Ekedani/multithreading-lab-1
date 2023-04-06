package bounce;

public class BallThread extends Thread {
    private final Ball ball;

    public BallThread(Ball ball) {
        this.ball = ball;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i < 10000; i++) {
                ball.move();
                if (ball.isInPocket()) {
                    return;
                }
                System.out.println("Thread name = " + Thread.currentThread().getName());
                Thread.sleep(5);
            }
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
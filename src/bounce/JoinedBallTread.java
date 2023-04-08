package bounce;

public class JoinedBallTread extends BallThread {

    private final BallThread parentBallThread;

    public JoinedBallTread(Ball ball, Runnable callback, BallThread parentBallThread) {
        super(ball, callback);
        this.parentBallThread = parentBallThread;
    }

    @Override
    public void run() {
        try {
            parentBallThread.join();
            super.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

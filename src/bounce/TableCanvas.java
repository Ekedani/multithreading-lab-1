package bounce;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TableCanvas extends JPanel {
    private final ArrayList<Ball> balls = new ArrayList<>();
    private final ArrayList<Pocket> pockets = new ArrayList<>();

    public TableCanvas() {
        createPockets();
    }

    public void addBall(Ball ball) {
        this.balls.add(ball);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2 = (Graphics2D) graphics;
        for (Pocket pocket : pockets) {
            pocket.draw(g2);
        }
        for (Ball ball : balls) {
            ball.draw(g2);
        }
    }

    private void createPockets() {
        final int canvasWidth = this.getWidth();
        final int canvasHeight = this.getHeight();
        this.pockets.add(new Pocket(0, 0));
        this.pockets.add(new Pocket(canvasWidth, 0));
        this.pockets.add(new Pocket(0, canvasHeight));
        this.pockets.add(new Pocket(canvasWidth, canvasHeight));
        this.pockets.add(new Pocket(canvasWidth/2, 0));
        this.pockets.add(new Pocket(canvasWidth/2, canvasHeight));
    }
}
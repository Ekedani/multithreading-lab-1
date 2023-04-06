package bounce;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BallCanvas extends JPanel {
    private final ArrayList<Ball> balls = new ArrayList<>();
    private final ArrayList<Pocket> pockets;

    public BallCanvas(ArrayList<Pocket> pockets) {
        this.pockets = pockets;
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
}
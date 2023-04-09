package bounce;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class TableCanvas extends JPanel {
    private final ArrayList<Ball> balls = new ArrayList<>();
    private ArrayList<Pocket> pockets = new ArrayList<>();

    public TableCanvas() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updatePockets();
            }
        });
    }

    public void addBall(Ball ball) {
        this.balls.add(ball);
    }

    public synchronized void removeBall(Ball ball) {
        this.balls.remove(ball);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        try {
            super.paintComponent(graphics);
            Graphics2D g2 = (Graphics2D) graphics;
            for (Pocket pocket : pockets) {
                pocket.draw(g2);
            }
            for (Ball ball : balls) {
                ball.draw(g2);
            }
        } catch (ConcurrentModificationException ignored) {
        }
    }

    public ArrayList<Pocket> getPockets() {
        return this.pockets;
    }


    private void updatePockets() {
        this.pockets = new ArrayList<>();

        int tableWidth = this.getWidth();
        int tableHeight = this.getHeight();
        int pocketWidth = Pocket.getWidth();
        int pocketHeight = Pocket.getHeight();

        this.pockets.add(new Pocket(0, 0));
        this.pockets.add(new Pocket(0, tableHeight - pocketHeight));
        this.pockets.add(new Pocket(tableWidth - pocketWidth, 0));
        this.pockets.add(new Pocket(tableWidth - pocketWidth, tableHeight - pocketHeight));
    }
}
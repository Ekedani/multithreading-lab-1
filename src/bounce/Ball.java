package bounce;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;

class Ball {
    private final TableCanvas tableCanvas;
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    private int x;
    private int y;
    private int dx = 2;
    private int dy = 2;


    public Ball(TableCanvas tableCanvas) {
        this.tableCanvas = tableCanvas;
        if (Math.random() < 0.5) {
            x = new Random().nextInt(this.tableCanvas.getWidth());
            y = 0;
        } else {
            x = 0;
            y = new Random().nextInt(this.tableCanvas.getHeight());
        }
    }


    public void draw(Graphics2D g2) {
        g2.setColor(Color.darkGray);
        g2.fill(new Ellipse2D.Double(x, y, WIDTH, HEIGHT));
    }

    public void move() {
        x += dx;
        y += dy;
        if (x < 0) {
            x = 0;
            dx = -dx;
        }
        if (x + WIDTH >= this.tableCanvas.getWidth()) {
            x = this.tableCanvas.getWidth() - WIDTH;
            dx = -dx;
        }
        if (y < 0) {
            y = 0;
            dy = -dy;
        }
        if (y + HEIGHT >= this.tableCanvas.getHeight()) {
            y = this.tableCanvas.getHeight() - HEIGHT;
            dy = -dy;
        }
        this.tableCanvas.repaint();
    }

    public boolean isInPocket() {
        final int gravityCenterX = x + (WIDTH / 2);
        final int gravityCenterY = y + (HEIGHT / 2);
        final ArrayList<Pocket> pockets = tableCanvas.getPockets();
        for (Pocket pocket : pockets) {
            if (pocket.contains(gravityCenterX, gravityCenterY)) {
                return true;
            }
        }
        return false;
    }

    public void die() {
        this.tableCanvas.removeBall(this);
    }
}
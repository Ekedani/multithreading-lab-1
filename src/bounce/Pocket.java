package bounce;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Pocket {
    private static final int XSIZE = 30;
    private static final int YSIZE = 30;
    private final int x;
    private final int y;

    public Pocket(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.black);
        g2.fill(new Ellipse2D.Double(x, y, XSIZE, YSIZE));
    }
}

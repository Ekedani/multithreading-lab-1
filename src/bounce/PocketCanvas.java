package bounce;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PocketCanvas extends JPanel {
    private final ArrayList<Pocket> pockets = new ArrayList<>();
    public void add(Pocket pocket) {
        this.pockets.add(pocket);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Pocket pocket : pockets) {
            pocket.draw(g2);
        }
    }
}

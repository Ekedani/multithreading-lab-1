package bounce;

import javax.swing.*;
import java.awt.*;

public class BounceFrame extends JFrame {
    private final TableCanvas ballCanvas;
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("bounce.Bounce program");
        this.ballCanvas = new TableCanvas();

        System.out.println("In Frame Thread name = " + Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(this.ballCanvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        JButton buttonStart = new JButton("Start");
        buttonStart.addActionListener(e -> {
            Ball b = new Ball(ballCanvas);
            ballCanvas.addBall(b);
            BallThread thread = new BallThread(b);
            thread.start();
            System.out.println("Thread name = " + thread.getName());
        });

        JButton buttonStop = new JButton("Stop");
        buttonStop.addActionListener(e -> System.exit(0));

        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);
        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}
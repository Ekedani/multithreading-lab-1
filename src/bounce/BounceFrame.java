package bounce;

import javax.swing.*;
import java.awt.*;

public class BounceFrame extends JFrame {
    private final TableCanvas tableCanvas;
    public static final int WIDTH = 550;
    public static final int HEIGHT = 450;

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("bounce.Bounce program");
        this.tableCanvas = new TableCanvas();

        System.out.println("In Frame Thread name = " + Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(this.tableCanvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        JButton buttonStart = new JButton("Start");
        buttonStart.addActionListener(e -> {
            Ball ball = new Ball(tableCanvas);
            BallThread ballThread = new BallThread(ball);
            tableCanvas.addBall(ball);
            ballThread.start();
            System.out.println("Thread name = " + ballThread.getName());
        });

        JButton buttonStop = new JButton("Stop");
        buttonStop.addActionListener(e -> System.exit(0));

        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);
        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}
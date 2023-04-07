package bounce;

import javax.swing.*;
import java.awt.*;

public class BounceFrame extends JFrame {
    private final TableCanvas tableCanvas;
    private int ballsInPocketCounter = 0;
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

        JLabel ballsInPocketLabel = new JLabel();
        ballsInPocketLabel.setText("Balls in pockets: " + ballsInPocketCounter);
        Runnable incrementBallsInPocket = () -> {
            synchronized (this) {
                ballsInPocketCounter++;
                ballsInPocketLabel.setText("Balls in pockets: " + ballsInPocketCounter);
            }
        };

        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            Ball ball = new Ball(tableCanvas);
            BallThread ballThread = new BallThread(ball, incrementBallsInPocket);
            tableCanvas.addBall(ball);
            ballThread.start();
            System.out.println("Thread name = " + ballThread.getName());
        });

        JButton startRedButton = new JButton("Start Red");
        startRedButton.addActionListener(e -> {
            Ball ball = new Ball(tableCanvas, Color.RED);
            tableCanvas.addBall(ball);
            BallThread ballThread = new BallThread(ball, incrementBallsInPocket);
            ballThread.setPriority(Thread.MAX_PRIORITY);
            ballThread.start();
        });

        JButton startBlueButton = new JButton("Start Blue");
        startBlueButton.addActionListener(e -> {
            Ball ball = new Ball(tableCanvas, Color.BLUE);
            tableCanvas.addBall(ball);
            BallThread ballThread = new BallThread(ball, incrementBallsInPocket);
            ballThread.setPriority(Thread.MIN_PRIORITY);
            ballThread.start();
        });

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(startButton);
        buttonPanel.add(startRedButton);
        buttonPanel.add(startBlueButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(ballsInPocketLabel);
        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}
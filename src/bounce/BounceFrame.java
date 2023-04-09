package bounce;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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

        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(Color.lightGray);

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


        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> System.exit(0));

        initializeRedAndBlueControls(controlPanel, incrementBallsInPocket);
        initializePriorityExperimentControls(controlPanel, incrementBallsInPocket);
        initializeJoinIllustrationControls(controlPanel, incrementBallsInPocket);

        controlPanel.add(startButton);
        controlPanel.add(stopButton);
        controlPanel.add(ballsInPocketLabel);
        content.add(controlPanel, BorderLayout.SOUTH);
    }

    private void initializeRedAndBlueControls(JPanel controlPanel, Runnable threadCallback) {
        JButton startRedButton = new JButton("Start Red");
        startRedButton.addActionListener(e -> {
            Ball ball = new Ball(tableCanvas, Color.RED);
            tableCanvas.addBall(ball);
            BallThread ballThread = new BallThread(ball, threadCallback);
            ballThread.setPriority(Thread.MAX_PRIORITY);
            ballThread.start();
        });

        JButton startBlueButton = new JButton("Start Blue");
        startBlueButton.addActionListener(e -> {
            Ball ball = new Ball(tableCanvas, Color.BLUE);
            tableCanvas.addBall(ball);
            BallThread ballThread = new BallThread(ball, threadCallback);
            ballThread.setPriority(Thread.MIN_PRIORITY);
            ballThread.start();
        });
        controlPanel.add(startRedButton);
        controlPanel.add(startBlueButton);
    }

    private void initializePriorityExperimentControls(JPanel controlPanel, Runnable threadCallback) {
        final int blueNum = 100;
        JButton experimentButton = new JButton("Priority Experiment");
        experimentButton.addActionListener(e -> {
            final int startX = this.tableCanvas.getWidth() / 2;
            final int startY = this.tableCanvas.getHeight() / 2;
            ArrayList<BallThread> ballThreads = new ArrayList<>();
            for (int i = 0; i < blueNum; i++) {
                Ball blueBall = new Ball(tableCanvas, Color.BLUE, startX, startY);
                BallThread blueBallThread = new BallThread(blueBall, threadCallback);
                blueBallThread.setPriority(Thread.MIN_PRIORITY);
                ballThreads.add(blueBallThread);
                tableCanvas.addBall(blueBall);
            }
            Ball redBall = new Ball(tableCanvas, Color.RED, startX, startY);
            BallThread redBallThread = new BallThread(redBall, threadCallback);
            redBallThread.setPriority(Thread.MAX_PRIORITY);
            ballThreads.add(redBallThread);
            tableCanvas.addBall(redBall);
            for (BallThread ballThread : ballThreads) {
                ballThread.start();
            }
        });
        controlPanel.add(experimentButton);
    }

    private void initializeJoinIllustrationControls(JPanel controlPanel, Runnable threadCallback) {
        JButton experimentButton = new JButton("Join Illustration");
        experimentButton.addActionListener(e -> {
            final int startX = this.tableCanvas.getWidth() / 2;
            final int startY = this.tableCanvas.getHeight() / 2;
            Ball parentBall = new Ball(tableCanvas, Color.MAGENTA, startX, startY);
            Ball childBall = new Ball(tableCanvas, Color.CYAN, startX, startY);
            BallThread parentBallThread = new BallThread(parentBall, threadCallback);
            JoinedBallTread childBallThread = new JoinedBallTread(childBall, threadCallback, parentBallThread);
            tableCanvas.addBall(parentBall);
            tableCanvas.addBall(childBall);
            parentBallThread.start();
            childBallThread.start();
        });
        controlPanel.add(experimentButton);
    }
}
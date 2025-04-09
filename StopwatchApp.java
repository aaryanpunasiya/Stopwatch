
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StopwatchApp extends JFrame {
    private JLabel timerLabel;
    private JButton startButton, stopButton, resetButton;
    private Timer timer;
    private long startTime, pausedTime;
    private boolean isRunning = false;

    public StopwatchApp() {
        setTitle("Stopwatch");
        setSize(300, 180);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Timer display
        timerLabel = new JLabel("00:00:00.000", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Consolas", Font.BOLD, 30));
        add(timerLabel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Timer updates every 10 milliseconds
        timer = new Timer(10, e -> updateTimer());

        // Start button logic
        startButton.addActionListener(e -> {
            if (!isRunning) {
                startTime = System.currentTimeMillis() - pausedTime;
                timer.start();
                isRunning = true;
            }
        });

        // Stop button logic
        stopButton.addActionListener(e -> {
            if (isRunning) {
                pausedTime = System.currentTimeMillis() - startTime;
                timer.stop();
                isRunning = false;
            }
        });

        // Reset button logic
        resetButton.addActionListener(e -> {
            timer.stop();
            isRunning = false;
            pausedTime = 0;
            timerLabel.setText("00:00:00.000");
        });

        setVisible(true);
    }

    private void updateTimer() {
        long currentTime = System.currentTimeMillis();
        long elapsed = currentTime - startTime;

        long hours = elapsed / 3600000;
        long minutes = (elapsed / 60000) % 60;
        long seconds = (elapsed / 1000) % 60;
        long millis = elapsed % 1000;

        timerLabel.setText(String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, millis));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StopwatchApp::new);
    }
}

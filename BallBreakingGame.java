import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BallBreakingGame extends JPanel implements ActionListener, KeyListener {
    private int ballX = 250;
    private int ballY = 300;
    private int ballXSpeed = 2;
    private int ballYSpeed = -2;
    private int paddleX = 200;
    private int paddleWidth = 100;
    private int paddleHeight = 10;
    private Timer timer;
    
    public BallBreakingGame() {
        timer = new Timer(10, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.WHITE);
        g.fillRect(paddleX, getHeight() - paddleHeight, paddleWidth, paddleHeight);
        
        g.setColor(Color.RED);
        g.fillOval(ballX, ballY, 20, 20);
        
        g.dispose();
    }
    
    public void actionPerformed(ActionEvent e) {
        ballX += ballXSpeed;
        ballY += ballYSpeed;
        
        if (ballX <= 0 || ballX >= getWidth() - 20) {
            ballXSpeed = -ballXSpeed;
        }
        
        if (ballY <= 0) {
            ballYSpeed = -ballYSpeed;
        }
        
        if (ballY >= getHeight() - 20) {
            // Game over logic
            timer.stop();
        }
        
        if (ballY >= getHeight() - paddleHeight - 20 && ballX >= paddleX && ballX <= paddleX + paddleWidth) {
            ballYSpeed = -ballYSpeed;
        }
        
        repaint();
    }
    
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            if (paddleX > 0) {
                paddleX -= 20;
            }
        } else if (key == KeyEvent.VK_RIGHT) {
            if (paddleX < getWidth() - paddleWidth) {
                paddleX += 20;
            }
        }
    }
    
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ball Breaking Game");
        BallBreakingGame game = new BallBreakingGame();
        frame.add(game);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

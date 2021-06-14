import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Physics extends JPanel implements KeyListener, ActionListener {

    private boolean play = false;
    private int score = 0;

    private int totalBricks = 21;

    private final Timer timer;

    private int playerX = 310;

    private int ballPosX = 120;
    private int ballPosY = 350;
    private int ballDirX = -3;
    private int ballDirY = -4;

    private Board board;

    public Physics() {
        board = new Board(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        int delay = 8;
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {

        g.setColor(Color.WHITE);
        g.fillRect(1, 1, 692, 592);

        board.draw((Graphics2D)g);

        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        g.setColor(Color.BLUE);
        g.fillRect(playerX, 550, 100, 8);

        g.setColor(Color.GREEN);
        g.fillOval(ballPosX, ballPosY, 20, 20);

        g.setColor(Color.BLACK);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("" + score, 590, 30);

        if (totalBricks <= 0) {
            play = false;
            ballDirX = 0;
            ballDirY = 0;
            g.setColor(Color.GREEN);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You Won, Score: " + score, 190, 300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart.", 230, 350);
        }

        if (ballPosY > 570) {
            play = false;
            ballDirX = 0;
            ballDirY = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over, Score: " + score, 190, 300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart.", 230, 350);
        }

        g.dispose();

    }

    public void actionPerformed(ActionEvent actionEvent) {

        timer.start();

        if(play) {
            if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
                ballDirY =- ballDirY;
            }
            for(int i = 0; i < board.board.length; i++) {
                for(int j = 0; j < board.board[0].length; j++) {
                    if(board.board[i][j] > 0) {
                        int brickX = j * board.brickWidth + 80;
                        int brickY = i * board.brickHeight + 50;
                        int brickWidth = board.brickWidth;
                        int brickHeight = board.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20,20);

                        if(ballRect.intersects(rect) ) {
                            board.setBrickValue(0, i, j);
                            totalBricks--;
                            score += 5;

                            if(ballPosX + 19 <= rect.x || ballPosX + 1 >= rect.x + rect.width)
                                ballDirX =- ballDirX;
                            else {
                                ballDirY =- ballDirY;
                            }
                        }
                    }
                }
            }

            ballPosX += ballDirX;
            ballPosY += ballDirY;

            if(ballPosX < 0) {
                ballDirX =- ballDirX;
            }

            if(ballPosY < 0) {
                ballDirY =- ballDirY;
            }

            if(ballPosX > 670) {
                ballDirX =- ballDirX;
            }
        }
        repaint();
    }

    public void keyTyped(KeyEvent keyEvent) {

    }

    public void keyPressed(KeyEvent keyEvent) {

        if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(playerX >= 600) {
                playerX = 600;
            } else {
                moveRight();
            }
        }

        if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            if(playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        }

        if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
            if(!play) {
                play = true;
                ballPosX = 120;
                ballPosY = 350;
                ballDirX = -3;
                ballDirY = -4;
                score = 0;
                totalBricks = 21;
                board = new Board(3,7);

                repaint();
            }
        }
    }

    public void moveRight() {
        play = true;
        playerX += 20;
    }
    public void moveLeft() {
        play = true;
        playerX -= 20;
    }

    public void keyReleased(KeyEvent keyEvent) {

    }
}

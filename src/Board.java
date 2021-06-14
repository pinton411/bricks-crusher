import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Board {

    public int board[][];
    public int brickWidth;
    public int brickHeight;

    public Board(int row, int col) {
        board = new int [row][col];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j< board[0].length; j++) {
                board[i][j] = 1;
            }
        }

        brickWidth = 540/col;
        brickHeight = 150/row;
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j< board[0].length; j++) {
                if(board[i][j] > 0) {
                    g.setColor(Color.black);
                    g.fillRect(j*brickWidth + 80, i*brickHeight + 50, brickWidth, brickHeight);

                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.white);
                    g.drawRect(j*brickWidth + 80, i*brickHeight + 50, brickWidth, brickHeight);
                }
            }

        }

    }

    public void setBrickValue(int value, int row, int col) {
        board[row][col] = value;
    }

}


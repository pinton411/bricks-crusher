import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        Physics physics = new Physics();
        frame.setBounds(10, 10, 700, 600);
        frame.setTitle("Bricks Crusher");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(physics);
    }
}

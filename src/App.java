import javax.swing.*;
import java.awt.Color;
import java.awt.GridLayout;

public class App {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("DII-GEN6-CompoundSEC");
        frame.setSize(1100, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setLayout(new GridLayout(1, 3, 5, 0));

        JPanel LogsLayout = new JPanel();
        LogsLayout.setBackground(Color.GREEN);

        JPanel RoomLayout = new JPanel();
        RoomLayout.setBackground(Color.BLUE);

        JPanel CardLayout = new JPanel();
        CardLayout.setBackground(Color.RED);
        
        frame.add(LogsLayout);
        frame.add(RoomLayout);
        frame.add(CardLayout);
        frame.setVisible(true);
    }
}

package guiModule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUICilent {
    public GUICilent() {
        JFrame frame = new JFrame("Client View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        
        JPanel roomContainer = new JPanel();
        roomContainer.setLayout(new BoxLayout(roomContainer, BoxLayout.Y_AXIS));
        roomContainer.setBorder(BorderFactory.createTitledBorder("Room Selection"));
        roomContainer.add(RoomPanel());
        
        JPanel cardContainer = new JPanel();
        cardContainer.setLayout(new BoxLayout(cardContainer, BoxLayout.Y_AXIS));
        cardContainer.setBorder(BorderFactory.createTitledBorder("Card Information"));
        cardContainer.add(CardPanel());

        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(roomContainer);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(cardContainer);
        mainPanel.add(Box.createHorizontalStrut(10));

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JPanel RoomPanel() {
        JPanel RoomPanel = new JPanel();
        RoomPanel.setLayout(new GridLayout(4, 3, 10, 10));
        RoomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 1; i <= 12; i++) {
            JButton roomButton = new JButton("Room " + i);

            if (i >= 8) {
                roomButton.setBorder(BorderFactory.createTitledBorder("High"));
                roomButton.setBackground(new Color(255, 200, 200));
            } else if (i >= 4) {
                roomButton.setBorder(BorderFactory.createTitledBorder("Medium"));
                roomButton.setBackground(new Color(255, 255, 200));
            } else {
                roomButton.setBorder(BorderFactory.createTitledBorder("Low"));
                roomButton.setBackground(new Color(200, 255, 200));
            }

            roomButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // comeing soon...
                }
            });

            RoomPanel.add(roomButton);
        }

        return RoomPanel;
    }

    private JPanel CardPanel() {
        JPanel cardsDisplayPanel = new JPanel();
        cardsDisplayPanel.setLayout(new BoxLayout(cardsDisplayPanel, BoxLayout.Y_AXIS));
        cardsDisplayPanel.setPreferredSize(new Dimension(300, 350));

        JPanel cardInPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        cardInPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        final JButton[] selectedButton = {null};

        for (int i = 1; i <= 4; i++) {
            JButton cardButton = new JButton();
            cardButton.setPreferredSize(new Dimension(280, 60));
            cardButton.setBackground(new Color(255, 223, 186));
            
            JLabel number = new JLabel(String.valueOf(i));
            cardButton.add(number);
            
            cardButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (selectedButton[0] != null) {
                        selectedButton[0].setBorder(null);
                    }

                    cardButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    selectedButton[0] = cardButton;
                }
            });
            
            cardInPanel.add(cardButton);
        }

        cardsDisplayPanel.add(cardInPanel);
        cardsDisplayPanel.add(Box.createVerticalGlue());

        return cardsDisplayPanel;
    }
}

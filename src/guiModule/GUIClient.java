package guiModule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

import java.util.ArrayList;
import cardModule.card.CardAccess;
import cardModule.management.CardManagement;
import cardModule.logging.Logs;
import cardModule.room.RoomAccess;
import cardModule.room.RoomAccessImpl;

public class GUIClient {
    private CardManagement cardManagement;
    private RoomAccess roomAccess;
    private JPanel cardContainer;
    private String selectedCard;

    public GUIClient(CardManagement cardManagement) {
        this.cardManagement = cardManagement;
        this.roomAccess = new RoomAccessImpl(cardManagement);
        JFrame frame = new JFrame("Client View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        
        JPanel roomContainer = new JPanel();
        roomContainer.setLayout(new BoxLayout(roomContainer, BoxLayout.Y_AXIS));
        roomContainer.setBorder(BorderFactory.createTitledBorder("Room Selection"));
        roomContainer.add(RoomPanel());
        
        cardContainer = new JPanel();
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
                    if (selectedCard.isEmpty()) {
                        JOptionPane.showMessageDialog(null, 
                            "Please select a card first", 
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    
                    String roomNumber = roomButton.getText().replaceAll("Room ", "");
                    CardAccess selectedCardObj = cardManagement.getCard(selectedCard);
                    
                    if (selectedCardObj.getExpiryDate().before(new Date())) {
                        JOptionPane.showMessageDialog(null,
                            "Card has expired",
                            "Expired",
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String requiredPermission = roomAccess.getRequiredPermissionLevel(Integer.parseInt(roomNumber));
                    boolean hasAccess = roomAccess.checkAccess(roomNumber, selectedCard);
                    
                    if (hasAccess) {
                        JOptionPane.showMessageDialog(null,
                            "Selected Room: " + roomNumber + "\nCard ID: " + selectedCard + "\nPermission: " + requiredPermission,
                            "Confirmation",
                            JOptionPane.INFORMATION_MESSAGE);
                        Logs.logUpdate("Access granted - Room: " + roomNumber + ", Card: " + selectedCard + ", Permission: " + requiredPermission);
                    } else {
                        JOptionPane.showMessageDialog(null,
                            "Access Denied: does not have permission for this room",
                            "Access Denied",
                            JOptionPane.ERROR_MESSAGE);
                        Logs.logUpdate("Access denied - Room: " + roomNumber + ", Card: " + selectedCard + ", Required Permission: " + requiredPermission);
                    }
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
        selectedCard = "";
        ArrayList<CardAccess> cards = cardManagement.getCards();
        
        for (int i = 0; i < Math.min(4, cards.size()); i++) {
            CardAccess card = cards.get(i);
            JButton cardButton = new JButton();
            cardButton.setPreferredSize(new Dimension(280, 60));
            cardButton.setBackground(new Color(255, 223, 186));

            JLabel cardInfo = new JLabel(String.format("CardID: %s - Expiry Date: %s", card.getCardNumber(), card.getExpiryDate()));
            cardButton.add(cardInfo);
    
            cardButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (selectedButton[0] != null) {
                        selectedButton[0].setBorder(null);
                    }

                    cardButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    selectedButton[0] = cardButton;
                    selectedCard = card.getCardNumber();
                }
            });
            
            cardInPanel.add(cardButton);
        }

        cardsDisplayPanel.add(cardInPanel);
        cardsDisplayPanel.add(Box.createVerticalGlue());

        return cardsDisplayPanel;
    }

    public void refreshCardPanel() {
        cardContainer.removeAll();
        cardContainer.add(CardPanel());
        cardContainer.revalidate();
        cardContainer.repaint();
    }
}

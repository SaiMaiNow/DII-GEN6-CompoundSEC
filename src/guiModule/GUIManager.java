package guiModule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import cardModule.CardAccess;
import cardModule.CardManagement;

import java.util.ArrayList;

public class GUIManager {
    private CardManagement cardManagement;

    public GUIManager() {
        cardManagement = new CardManagement();
        initializeUI();
    }

    private void initializeUI() {
        JFrame frame = new JFrame("Card Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(createAddCardPanel(), BorderLayout.NORTH);

        mainPanel.add(createLogsPanel(), BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JPanel createAddCardPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder("Card Management"));
        JButton addCardButton = new JButton("Add Card");
        JButton revokeCardButton = new JButton("Revoke Card");
        JButton modifyCardButton = new JButton("Modify Card");

        addCardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame addCardFrame = new JFrame("Add Card");
                addCardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                addCardFrame.setSize(300, 200);

                JPanel addCardPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

                JCheckBox lowPermission = new JCheckBox("Low");
                JCheckBox mediumPermission = new JCheckBox("Medium");
                JCheckBox highPermission = new JCheckBox("High");
                
                addCardPanel.add(lowPermission);
                addCardPanel.add(mediumPermission);
                addCardPanel.add(highPermission);

                JButton addCardButton = new JButton("Add Card");
                addCardPanel.add(addCardButton);

                addCardButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        ArrayList<String> cardLevel = new ArrayList<>();

                        if (lowPermission.isSelected()) {
                            cardLevel.add("Low");
                        }
                        
                        if (mediumPermission.isSelected()) {
                            cardLevel.add("Medium");
                        }

                        if (highPermission.isSelected()) {
                            cardLevel.add("High");
                        }

                        CardAccess card = new CardAccess(cardLevel);
                        cardManagement.addCard(card);
                        addCardFrame.dispose();
                    }
                });

                addCardFrame.add(addCardPanel);
                addCardFrame.setVisible(true);
            }
        });

        panel.add(addCardButton);
        panel.add(revokeCardButton);
        panel.add(modifyCardButton);
        return panel;
    }

    private JPanel createLogsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Logs"));
        JScrollPane scrollPane = new JScrollPane();

        JTextArea logsArea = new JTextArea("dadawdawdawdawdadawda");
        logsArea.setEditable(false);
        
        scrollPane.setViewportView(logsArea);

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
}

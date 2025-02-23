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
                addCardFrame.setSize(400, 300);
                addCardFrame.setLocationRelativeTo(null);

                JPanel addCardPanel = new JPanel(new GridBagLayout());
                addCardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.insets = new Insets(5, 5, 5, 5);

                JPanel expiryPanel = new JPanel(new BorderLayout(10, 0));
                expiryPanel.setBorder(BorderFactory.createTitledBorder("Expiry Date"));
                JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
                JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy HH:mm");
                dateSpinner.setEditor(dateEditor);
                expiryPanel.add(dateSpinner, BorderLayout.CENTER);

                JPanel permissionPanel = new JPanel(new GridLayout(3, 1, 0, 5));
                permissionPanel.setBorder(BorderFactory.createTitledBorder("Permission Levels"));
                JCheckBox lowPermission = new JCheckBox("Low");
                JCheckBox mediumPermission = new JCheckBox("Medium");
                JCheckBox highPermission = new JCheckBox("High");

                permissionPanel.add(lowPermission);
                permissionPanel.add(mediumPermission);
                permissionPanel.add(highPermission);

                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                JButton confirmButton = new JButton("Add Card");
                JButton cancelButton = new JButton("Cancel");
                buttonPanel.add(confirmButton);
                buttonPanel.add(cancelButton);

                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 1.0;
                addCardPanel.add(expiryPanel, gbc);

                gbc.gridy = 1;
                gbc.insets = new Insets(15, 5, 15, 5);
                addCardPanel.add(permissionPanel, gbc);

                gbc.gridy = 2;
                gbc.insets = new Insets(5, 5, 5, 5);
                addCardPanel.add(buttonPanel, gbc);

                confirmButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ArrayList<String> cardLevel = new ArrayList<>();
                        if (lowPermission.isSelected())
                            cardLevel.add("Low");
                        if (mediumPermission.isSelected())
                            cardLevel.add("Medium");
                        if (highPermission.isSelected())
                            cardLevel.add("High");

                        java.util.Date expiryDate = (java.util.Date) dateSpinner.getValue();
                        CardAccess card = new CardAccess(cardLevel, expiryDate);
                        cardManagement.addCard(card);
                        addCardFrame.dispose();
                    }
                });

                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
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

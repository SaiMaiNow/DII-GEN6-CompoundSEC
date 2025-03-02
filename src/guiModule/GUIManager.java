package guiModule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import cardModule.card.CardAccess;
import cardModule.management.CardManagement;
import cardModule.logging.Logs;

import java.util.ArrayList;

public class GUIManager {
    private CardManagement cardManagement;

    private GUIClient guiClient;

    public GUIManager(CardManagement cardManagement, GUIClient guiClient) {
        this.cardManagement = cardManagement;
        this.guiClient = guiClient;
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
                gbc.weightx = 1.0;

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
                        boolean isAdded = cardManagement.addCard(card);
                        if (isAdded) {
                            JOptionPane.showMessageDialog(addCardFrame, "Card added successfully", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                            addCardFrame.dispose();
                            guiClient.refreshCardPanel();
                        } else {
                            JOptionPane.showMessageDialog(addCardFrame, "Failed to add card", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
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

        revokeCardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame revokeCardFrame = new JFrame("Revoke Card");
                revokeCardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                revokeCardFrame.setSize(400, 200);
                revokeCardFrame.setLocationRelativeTo(null);

                JPanel revokeCardPanel = new JPanel(new GridBagLayout());
                revokeCardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.insets = new Insets(5, 5, 5, 5);

                JPanel inputPanel = new JPanel(new BorderLayout(10, 0));
                inputPanel.setBorder(BorderFactory.createTitledBorder("Card ID"));
                JTextField cardIdField = new JTextField(20);
                inputPanel.add(cardIdField, BorderLayout.CENTER);

                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                JButton confirmButton = new JButton("Revoke Card");
                JButton cancelButton = new JButton("Cancel");
                buttonPanel.add(confirmButton);
                buttonPanel.add(cancelButton);

                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 1.0;
                revokeCardPanel.add(inputPanel, gbc);

                gbc.gridy = 1;
                gbc.insets = new Insets(15, 5, 5, 5);
                revokeCardPanel.add(buttonPanel, gbc);

                confirmButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        boolean isRevoked = cardManagement.revokeCard(cardIdField.getText());
                        if (isRevoked) {
                            JOptionPane.showMessageDialog(revokeCardFrame, "Card revoked successfully", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                            revokeCardFrame.dispose();
                            guiClient.refreshCardPanel();
                        } else {
                            JOptionPane.showMessageDialog(revokeCardFrame, "Failed to revoke card", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        revokeCardFrame.dispose();
                    }
                });

                revokeCardFrame.add(revokeCardPanel);
                revokeCardFrame.setVisible(true);
            }
        });

        modifyCardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame modifyCardFrame = new JFrame("Modify Card");
                modifyCardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                modifyCardFrame.setSize(400, 200);
                modifyCardFrame.setLocationRelativeTo(null);

                JPanel modifyCardPanel = new JPanel(new GridBagLayout());
                modifyCardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.insets = new Insets(5, 5, 5, 5);

                JPanel inputPanel = new JPanel(new BorderLayout(10, 0));
                inputPanel.setBorder(BorderFactory.createTitledBorder("Card ID"));
                JTextField cardIdField = new JTextField(20);
                inputPanel.add(cardIdField, BorderLayout.CENTER);

                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                JButton confirmButton = new JButton("Confirm");
                JButton cancelButton = new JButton("Cancel");
                buttonPanel.add(confirmButton);
                buttonPanel.add(cancelButton);

                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 1.0;
                modifyCardPanel.add(inputPanel, gbc);

                gbc.gridy = 1;
                gbc.insets = new Insets(15, 5, 5, 5);
                modifyCardPanel.add(buttonPanel, gbc);

                confirmButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        CardAccess card = cardManagement.getCard(cardIdField.getText());
                        if (card != null) {
                            modifyCardFrame.dispose();

                            JFrame editFrame = new JFrame("Modify Card");
                            editFrame.setSize(400, 300);
                            
                            JPanel editPanel = new JPanel(new GridBagLayout());
                            editPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                            GridBagConstraints gbc = new GridBagConstraints();
                            gbc.fill = GridBagConstraints.HORIZONTAL;
                            gbc.insets = new Insets(5, 5, 5, 5);
                            gbc.weightx = 1.0;

                            JPanel expiryPanel = new JPanel(new BorderLayout(10, 0));
                            expiryPanel.setBorder(BorderFactory.createTitledBorder("Expiry Date"));
                            java.util.Date expiryDate = card.getExpiryDate();
                            JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
                            JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy HH:mm");
                            dateSpinner.setEditor(dateEditor);
                            dateSpinner.setValue(expiryDate);
                            expiryPanel.add(dateSpinner, BorderLayout.CENTER);

                            JPanel permissionPanel = new JPanel(new GridLayout(3, 1, 0, 5));
                            permissionPanel.setBorder(BorderFactory.createTitledBorder("Permission Levels"));

                            JCheckBox lowPermission = new JCheckBox("Low");
                            if (card.getCardPermission().contains("Low")) {
                                lowPermission.setSelected(true);
                            }

                            JCheckBox mediumPermission = new JCheckBox("Medium");
                            if (card.getCardPermission().contains("Medium")) {
                                mediumPermission.setSelected(true);
                            }

                            JCheckBox highPermission = new JCheckBox("High");
                            if (card.getCardPermission().contains("High")) {
                                highPermission.setSelected(true);
                            }

                            permissionPanel.add(lowPermission);
                            permissionPanel.add(mediumPermission);
                            permissionPanel.add(highPermission);

                            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                            JButton saveButton = new JButton("Save");
                            JButton cancelButton = new JButton("Cancel");
                            buttonPanel.add(saveButton);
                            buttonPanel.add(cancelButton);

                            gbc.gridx = 0;
                            gbc.gridy = 0;
                            editPanel.add(expiryPanel, gbc);

                            gbc.gridy = 1;
                            gbc.insets = new Insets(15, 5, 15, 5);
                            editPanel.add(permissionPanel, gbc);

                            gbc.gridy = 2;
                            gbc.insets = new Insets(5, 5, 5, 5);
                            editPanel.add(buttonPanel, gbc);

                            editFrame.add(editPanel);

                            saveButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    ArrayList<String> cardLevel = new ArrayList<>();
                                    if (lowPermission.isSelected())
                                        cardLevel.add("Low");
                                    if (mediumPermission.isSelected())
                                        cardLevel.add("Medium");
                                    if (highPermission.isSelected())
                                        cardLevel.add("High");
                                    cardManagement.modifyCard(cardIdField.getText(), cardLevel, (java.util.Date) dateSpinner.getValue());
                                    JOptionPane.showMessageDialog(editFrame, "Card modified successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    guiClient.refreshCardPanel();
                                    editFrame.dispose();
                                }
                            });

                            cancelButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    editFrame.dispose();
                                }
                            });

                            editFrame.setLocationRelativeTo(null);
                            editFrame.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(modifyCardFrame, "Failed to modify card", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        modifyCardFrame.dispose();
                    }
                });

                modifyCardFrame.add(modifyCardPanel);
                modifyCardFrame.setVisible(true);
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

        JTextArea logsArea = new JTextArea();
        logsArea.setEditable(false);
        logsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Create refresh button
        JButton refreshButton = new JButton("Refresh Logs");
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateLogs(logsArea);
            }
        });

        // Create clear button
        JButton clearButton = new JButton("Clear Logs");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Logs.clearLogs();
                updateLogs(logsArea);
            }
        });

        // Add buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(refreshButton);
        buttonPanel.add(clearButton);

        scrollPane.setViewportView(logsArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Initial logs update
        updateLogs(logsArea);

        return panel;
    }

    private void updateLogs(JTextArea logsArea) {
        ArrayList<String> logs = Logs.getLogs();
        StringBuilder sb = new StringBuilder();
        for (String log : logs) {
            sb.append(log).append("\n");
        }
        logsArea.setText(sb.toString());
        logsArea.setCaretPosition(logsArea.getDocument().getLength());
    }
}
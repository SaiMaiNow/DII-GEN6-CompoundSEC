package guiModule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.jar.JarInputStream;

import cardModule.CardAccess;
import cardModule.CardManagement;

public class test extends JFrame {
    public test() {
        setTitle("Test GUI");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setVisible(true);

        CardManagement cardManagement = new CardManagement();

        JButton button = new JButton("add card");
        add(button);

        JTextField textField = new JTextField(10);
        add(textField);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardAccess card = new CardAccess("LOW");
                cardManagement.addCard(card);
                System.out.println(card.getCardNumber());
            }
        });
    }
}

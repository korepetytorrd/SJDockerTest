package org.example.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.example.controls.LoginManager;

public class Login {

    public static void start() throws IOException {
        Menu menu = new Menu();
        String[] fileToCheck = { "kursant_dataMD5.txt", "instruktor_dataMD5.txt", "admin_dataMD5.txt" };
        LoginManager loginManager = new LoginManager(fileToCheck);

        JFrame frame = new JFrame("Logowanie");
        JPanel panel = new JPanel();
        JLabel emailLabel = new JLabel("Email");
        JTextField emailField = new JTextField(20);
        JLabel passLabel = new JLabel("Haslo");
        JPasswordField passField = new JPasswordField(20);
        JButton loginButton = new JButton("Zaloguj");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // "jan@example.com";
                // "password123";

                String email = emailField.getText();
                @SuppressWarnings("deprecation")
                String password = passField.getText();

                switch (loginManager.login(email, password)) {
                    case "kursant_dataMD5.txt": // zalogowano jako kursant
                        JOptionPane.showMessageDialog(frame, "Logowanie udane jako kursant", "Sukces",
                                JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose(); // zamkniecie okna logowania
                        try {
                            menu.startKursant(email);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case "instruktor_dataMD5.txt":
                        JOptionPane.showMessageDialog(frame, "Logowanie udane jako instruktor", "Sukces",
                                JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose(); // zamkniecie okna logowania
                        try {
                            menu.startAdministrator(email);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case "admin_dataMD5.txt":
                        JOptionPane.showMessageDialog(frame, "Logowanie udane jako admin", "Sukces",
                                JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose(); // zamkniecie okna logowania
                        try {
                            menu.startIntruktor(email);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    default:
                        JOptionPane.showMessageDialog(frame, "Logowanie nieudane", "Błąd", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Login failed");

                }
            }
        });
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passLabel);
        panel.add(passField);
        panel.add(loginButton);

        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

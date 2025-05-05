package org.example.views;

import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.example.controls.ZarzadzaniePlikamiTekstowymi;
import org.example.models.Kursant;

public class Menu {

    public String getNameUser(String email) {
        Kursant[] Kursants = ZarzadzaniePlikamiTekstowymi.readKursantFromCSV("Kursant.csv");

        for (Kursant kursant : Kursants) {
            if (kursant.getEmail() == email) {
                return kursant.getFirstName();
            }
        }
        return "Brak imienia";

    }

    public void startKursant(String email) throws IOException {
        JFrame frame = new JFrame("Menu kursanta");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));

        String name = getNameUser(email);
        // nagłowek
        JLabel welcomeLabel = new JLabel("Witaj" + name + "!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // przyciski
        JButton harmonogramJazdButton = new JButton("Zobacz harmonogramm jazd");
        JButton progresButton = new JButton("Zobacz progres jazd praktycznych");
        JButton testyButton = new JButton("Testy teoretyczne");
        JButton logoutButton = new JButton("Wyloguj");

        // akcje do przycisków
        harmonogramJazdButton.addActionListener(e -> {
            frame.dispose();
            // Kursant.displayHarmonogram(email)
        });
        progresButton.addActionListener(e -> {
            frame.dispose();
            // Kursant.displayProgres(email)
        });
        testyButton.addActionListener(e -> {
            frame.dispose();
            // Kursant.displayTesty(email)
        });
        logoutButton.addActionListener(e -> {
            frame.dispose();
            System.exit(0); // zamkniecie aplikacji
        });

        // dodanie przciskow do panelu
        panel.add(welcomeLabel);
        panel.add(harmonogramJazdButton);
        panel.add(progresButton);
        panel.add(testyButton);
        panel.add(logoutButton);

        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void startAdministrator(String email) throws IOException {

    }

    public void startIntruktor(String email) throws IOException {

    }

}

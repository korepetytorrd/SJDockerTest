package org.example.models;

import org.example.controls.ZarzadzanieJazdami;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static org.example.controls.ZarzadzanieJazdami.zapiszDoPliku;

public class Instruktor extends User {
    private String liceneNumber;

    public String getLiceneNumber() {
        return liceneNumber;
    }

    public void setLiceneNumber(String liceneNumber) {
        this.liceneNumber = liceneNumber;
    }


    public Instruktor() {
        super();
    }

    public Instruktor(
            int privilege, String firstName, String lastName, String privince, String town,
            String postCode, String street, String houseNumber, String flatNumber,
            String phone, String pesel, String email, Boolean email_ver, String password, Date created_at, String liceneNumber
    ) {
        super(
                privilege, firstName, lastName, privince, town,
                postCode, street, houseNumber, flatNumber, phone, pesel, email, email_ver
                , password, created_at
        );
        this.liceneNumber = liceneNumber;
    }

    public JazdyPraktyczne utworzenieJazdPraktycznych() {
        List<JazdyPraktyczne> jazdy = ZarzadzanieJazdami.wczytajJazdy();
        int startId = jazdy.size();
        if (!jazdy.isEmpty()) {
            boolean powtorkaId = false;
            do {
                startId++;
                for (JazdyPraktyczne jazda : jazdy) {
                    if (jazda.getIdJazdyPraktyczne() == startId) {
                        powtorkaId = true;
                        break;
                    }
                }
            } while (powtorkaId);
        }

        LocalDateTime gR = null, gZ = null;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj dzień i godzinę rozpoczęcia jazd (yyyy-MM-dd HH:mm): ");
        String temp = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            gR = LocalDateTime.parse(temp, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Błędny format daty i czasu. Poprawny format to yyyy-MM-dd HH:mm.");
        }

        System.out.println("Podaj dzień i godzinę zakończenia jazd (yyyy-MM-dd HH:mm): ");
        temp = scanner.nextLine();
        try {
            gZ = LocalDateTime.parse(temp, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Błędny format daty i czasu. Poprawny format to yyyy-MM-dd HH:mm.");
        }

        JazdyPraktyczne noweJazdy = new JazdyPraktyczne(startId,getId(), gR,gZ);
        jazdy.add(noweJazdy);
        zapiszDoPliku(jazdy);
        return new JazdyPraktyczne(startId,getId(), gR,gZ);
    }
}

package org.example.models;

import java.util.Date;

public class Kursant extends User {

    private String pkkNumber; //musi byc typ string!!! za duza wartosc liczbowa 20 cyfr

    public Kursant() {
        super();
    }

    public Kursant(int id, int privilage, String firstName, String email, String flatNumber,
            String lastName, String pesel, String houseNumber, String privince,
            String phone, String pkkNumber) {
        super(id, privilage, firstName, email, flatNumber, lastName, pesel, houseNumber, privince, phone);
        this.pkkNumber = pkkNumber;
    }

    public Kursant(int privilage, String firstName, String lastName, String privince, String town, String postCode,
            String street, String houseNumber, String flatNumber, String phone, String pesel, String email,
            boolean email_ver, String password, Date created_at, String pkkNumber) {
        super(privilage, firstName, lastName, privince, town, postCode, street, houseNumber, flatNumber, phone,
                pesel, email, email_ver, password, created_at);
        this.pkkNumber = pkkNumber;
    }

    public Kursant(int id, int privilege, String firstName, String lastName, String province, String town,
            String postCode, String street, String houseNumber, String flatNumber, String phone, String pesel,
            String email, Boolean email_ver, String password, Date created_at, String pkkNumber) {
        super(created_at, email, email_ver, firstName, flatNumber, houseNumber, id, lastName, password, pesel, phone,
                postCode, privilege, province, street, town);
        this.pkkNumber = pkkNumber;
    }

    public String getPkkNumber() {
        return pkkNumber;
    }

    public void setPkkNumber(String pkkNumber) {
        this.pkkNumber = pkkNumber;
    }

    public static void displayKursantow(Kursant[] kursants) {
        for (Kursant kursant : kursants) {
            System.out.println(kursant.getId() + " " + kursant.getFirstName() + " " + kursant.getLastName());
        }

    }

    @Override
    public String toCSV() {
        return super.toCSV() + pkkNumber + ";";
    }
}

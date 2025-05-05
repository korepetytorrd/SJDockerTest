package org.example.models;

import org.example.controls.ZarzadzaniePlikamiTekstowymi;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class Admin extends User {
    private String ksywka;


    public Admin(
            int id, int privilege, String firstName, String lastName, String privince, String town,
            String postCode, String street, String houseNumber, String flatNumber, String phone,
            String pesel, String email, Boolean email_ver, String password, Date created_at, String ksywka
    ) {
        super(privilege, firstName, lastName, privince, town, postCode, street, houseNumber,
                flatNumber, phone, pesel, email, email_ver, password, created_at
        );
        this.ksywka = ksywka;
    }

    public Admin() {
        super();
    }


    public String getKsywka() {
        return ksywka;
    }

    public void setKsywka(String ksywka) {
        this.ksywka = ksywka;
    }

    public static int genUnicID (int privilege, int id){
        if (privilege == 1){
            Kursant [] kursants = ZarzadzaniePlikamiTekstowymi.readKursantFromCSV("Kursant.csv");
            while (ZarzadzaniePlikamiTekstowymi.kursantExists("Kursant.csv", id)){
                id++;
            }
            return id;
        }
        if (privilege == 2){
            Instruktor [] instruktors = ZarzadzaniePlikamiTekstowymi.readInstruktorFromCSV("Instruktor.csv");
            while (ZarzadzaniePlikamiTekstowymi.instruktorExists("Instruktor.csv", id)){
                id++;
            }
            return id;
        }
        return 0;
    }

    public static void addUserow(Kursant[] tab) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Kogo chcesz dodać? 1-kursant, 2-instruktor, 3-admin");
        switch (scanner.nextInt()) {
            case 1: //kursant
                Kursant kursant = new Kursant();
                kursant.setId(genUnicID(1,1)); //TODO wygenerować unikalną liczbe i przekazać w parametrze
                kursant.setPrivilege(1);
                kursant.setEmail_ver(false);
                System.out.print("Ustaw mu imię: ");
                kursant.setFirstName(scanner.next());

                System.out.print("Ustaw mu nazwisko: ");
                kursant.setLastName(scanner.next());

                System.out.print("Z jakiego jest województwa: ");
                kursant.setProvince(scanner.next());

                System.out.print("Podaj kod pocztowy: ");
                kursant.setPostCode(scanner.next());

                System.out.print("Podaj ulicę: ");
                kursant.setStreet(scanner.next());

                System.out.print("Podaj miasto: ");
                kursant.setTown(scanner.next());

                System.out.print("Podaj numer domu: ");
                kursant.setHouseNumber(scanner.next());

                System.out.print("Podaj numer mieszkania: ");
                kursant.setFlatNumber(scanner.next());

                System.out.print("Podaj telefon: ");
                kursant.setPhone(scanner.next());

                System.out.print("Podaj pesel: ");
                kursant.setPesel(scanner.next());

                System.out.print("Podaj email: ");
                kursant.setEmail(scanner.next());

                System.out.print("Podaj hasło: ");
                kursant.setPassword(scanner.next());

                System.out.print("Podaj datę utworzenia: ");
                kursant.setCreated_at(new Date());

                System.out.print("Podaj numer PKK: ");
                kursant.setPkkNumber(scanner.next());

                ZarzadzaniePlikamiTekstowymi.creatKursantSaveToCSV("Testowo.csv",kursant);
                break;
            case 2: //instruktor
                Instruktor instruktor = new Instruktor();
                instruktor.setId(genUnicID(2,1)); //TODO wygenerować unikalną liczbe i przekazać w parametrze
                instruktor.setPrivilege(1);
                instruktor.setEmail_ver(false);
                System.out.print("Ustaw mu imię: ");
                instruktor.setFirstName(scanner.next());

                System.out.print("Ustaw mu nazwisko: ");
                instruktor.setLastName(scanner.next());

                System.out.print("Z jakiego jest województwa: ");
                instruktor.setProvince(scanner.next());

                System.out.print("Podaj kod pocztowy: ");
                instruktor.setPostCode(scanner.next());

                System.out.print("Podaj ulicę: ");
                instruktor.setStreet(scanner.next());

                System.out.print("Podaj miasto: ");
                instruktor.setTown(scanner.next());

                System.out.print("Podaj numer domu: ");
                instruktor.setHouseNumber(scanner.next());

                System.out.print("Podaj numer mieszkania: ");
                instruktor.setFlatNumber(scanner.next());

                System.out.print("Podaj telefon: ");
                instruktor.setPhone(scanner.next());

                System.out.print("Podaj pesel: ");
                instruktor.setPesel(scanner.next());

                System.out.print("Podaj email: ");
                instruktor.setEmail(scanner.next());

                System.out.print("Podaj hasło: ");
                instruktor.setPassword(scanner.next());

                System.out.print("Podaj datę utworzenia: ");
                instruktor.setCreated_at(new Date());

                System.out.print("Podaj numer PKK: ");
                instruktor.setLiceneNumber(scanner.next());

                ZarzadzaniePlikamiTekstowymi.creatInstruktorSaveToCSV("Instruktor.csv",instruktor);
                break;
            case 3: //admin
                break;
        }
    }

    public static void updateKursantow(Kursant[] tab) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Którego użytkownika chciałbyś zmienić? --Liczymy od zera-- (1 użytkownik - 0)");
        for (Kursant kursant : tab) {
            System.out.println(kursant.wyswietl());
        }
        System.out.print("Wybor:");
        int which = scanner.nextInt();

        if (which > tab.length) {
            System.out.println("Nie mamy tylu użytkowników");
        } else {
            Kursant toEditKursant = tab[which];
            System.out.println("Wybierz jakie dane chcesz zedytować u tego użytkownika (1 - zmian_privilege, 2- zmian_firstName " + "\n" +
                    "3 - zmian_email, 4 - zmian_flatNumber, 5 - zmian_lastName," + "\n" +
                    " 6 - zmian_pesel, 7 - zmian_houseNumber, 8 - zmian_privince,  9- zmian_phone, 10- zmian_pkkNumber )");
            System.out.print("Wybor:");
            int whichDataKursant = scanner.nextInt();
            //TODO uzupełnic sety by zapisywać zmiany w obiektach do edycji

            switch (whichDataKursant) {
                case 1:
                    toEditKursant.setPrivilege(scanner.nextInt());
                    break;
                case 2:
                    toEditKursant.setFirstName(scanner.next());
                    break;
                case 3:
                    toEditKursant.setEmail(scanner.next());
                    break;
                case 4:
                    toEditKursant.setFlatNumber(scanner.next());
                    break;
                case 5:
                    toEditKursant.setLastName(scanner.next());
                    break;
                case 6:
                    toEditKursant.setPesel(scanner.next());
                    break;
                case 7:
                    toEditKursant.setHouseNumber(scanner.next());
                    break;
                case 8:
                    toEditKursant.setProvince(scanner.next());
                    break;
                case 9:
                    toEditKursant.setPhone(scanner.next());
                    break;
                case 10:
                    toEditKursant.setPkkNumber(scanner.next());
                    break;

            }
            //save edited data to file
            ZarzadzaniePlikamiTekstowymi.updateKursantSaveToCSV(tab, "Kursant.csv");
        }
    }
}

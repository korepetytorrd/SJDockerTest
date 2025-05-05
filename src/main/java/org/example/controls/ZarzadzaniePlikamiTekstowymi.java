package org.example.controls;

import org.example.models.Admin;
import org.example.models.Instruktor;
import org.example.models.JazdyPraktyczne;
import org.example.models.Kursant;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ZarzadzaniePlikamiTekstowymi {
    public static void zapiszDoPliku(String fileName, String[] cols, String[] toSave) {
        Scanner scanner = new Scanner(System.in);


    }

    public static void zapiszJazde(JazdyPraktyczne jazda, String PLIK) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(PLIK, true));
        writer.write(jazda.toString());
        writer.newLine();

    }

    //CRUD instruktor
    public static void creatInstruktorSaveToCSV(String fileName, Instruktor instruktor) throws IOException {
        if (!instruktorExists(fileName, instruktor.getId())) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
                bw.write(instruktor.toCSV());
                bw.newLine();
                System.out.println("Kursant został zapisany do pliku.");
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            }
        } else {
            System.out.println("Kursant już istnieje.");
        }
    }

    public static Instruktor[] readInstruktorFromCSV(String fileName) {
        ArrayList<Instruktor> instruktorList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                Instruktor instruktor = new Instruktor();

                instruktor.setId(Integer.parseInt(values[0]));
                instruktor.setPrivilege(Integer.parseInt(values[1]));
                instruktor.setFirstName(values[2]);
                instruktor.setLastName(values[3]);
                instruktor.setEmail(values[4]);
                instruktor.setFlatNumber(values[5]);
                instruktor.setPesel(values[6]);
                instruktor.setHouseNumber(values[7]);
                instruktor.setProvince(values[8]);
                instruktor.setPhone(values[9]);
                instruktor.setLiceneNumber(values[10]);

                instruktorList.add(instruktor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return instruktorList.toArray(new Instruktor[0]);
    }

    public static boolean instruktorExists(String fileName, int instruktor) {
        Instruktor[] instruktors = readInstruktorFromCSV(fileName);
        boolean exist = false;
        for (Instruktor instruktor1 : instruktors) {
            if (instruktor == instruktor1.getId()) {
                exist = true;
                break;
            }
        }
        return exist;
    }
    ///////////////////////////////////////////////////////////////////////////////
    //CRUD kursant
    public static void creatKursantSaveToCSV(String fileName, Kursant kursant) throws IOException {
        if (!kursantExists(fileName, kursant.getId())) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
                bw.write(kursant.toCSV());
                bw.newLine();
                System.out.println("Kursant został zapisany do pliku.");
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            }
        } else {
            System.out.println("Kursant już istnieje.");
        }
    }

    public static Kursant[] readKursantFromCSV(String fileName) {
        ArrayList<Kursant> kursantList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                Kursant kursant = new Kursant();

                kursant.setId(Integer.parseInt(values[0]));
                kursant.setPrivilege(Integer.parseInt(values[1]));
                kursant.setFirstName(values[2]);
                kursant.setLastName(values[3]);
                kursant.setEmail(values[4]);
                kursant.setFlatNumber(values[5]);
                kursant.setPesel(values[6]);
                kursant.setHouseNumber(values[7]);
                kursant.setProvince(values[8]);
                kursant.setPhone(values[9]);
                kursant.setPkkNumber(values[10]);

                kursantList.add(kursant);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return kursantList.toArray(new Kursant[0]);
    }

    public static void updateKursantSaveToCSV(Kursant[] kursants, String fileName) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (Kursant kursant : kursants) {
                pw.println(kursant.toCSV());
            }
        }
    }

    public static boolean kursantExists(String fileName, int id) {
        Kursant[] kursants = readKursantFromCSV(fileName);
        boolean exist = false;
        for (Kursant kursant1 : kursants) {
            if (id == kursant1.getId()) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    //CRUD Admin
    //////////////////////////////////////////////////////////////////////////////////////////

    public static void creatAdminSaveToCSV(String fileName, Admin administrator) throws IOException {
        if (!AdminExists(fileName, administrator.getId())) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
                bw.write(administrator.toCSV());
                bw.newLine();
                System.out.println("aministator zapisany do pliku.");
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            }
        } else {
            System.out.println("Administrator już istnieje.");
        }
    }
    public static Admin[] readAdminFromCSV(String fileName) {
        ArrayList<Admin> administatorList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                Admin administator = new Admin();

                administator.setId(Integer.parseInt(values[0]));
                administator.setPrivilege(Integer.parseInt(values[1]));
                administator.setFirstName(values[2]);
                administator.setLastName(values[3]);
                administator.setEmail(values[4]);
                administator.setFlatNumber(values[5]);
                administator.setPesel(values[6]);
                administator.setHouseNumber(values[7]);
                administator.setProvince(values[8]);
                administator.setPhone(values[9]);
                administator.setKsywka(values[10]);

                administatorList.add(administator);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return administatorList.toArray(new Admin[0]);
    }
    public static boolean AdminExists(String fileName, int id) {
        Admin[] kursants = readAdminFromCSV(fileName);
        boolean exist = false;
        for (Admin admin1 : kursants) {
            if (id == admin1.getId()) {
                exist = true;
                break;
            }
        }
        return exist;
    }
    public static void updateAdminSaveToCSV(Admin[] administators, String fileName) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (Admin administrator : administators) {
                pw.println(administrator.toCSV());
            }
        }
    }







//////////////////////////////////////////////////////////////////////////////////
    public static void saveToTXT(Kursant[] kursants, String fileName) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (Kursant kursant : kursants) {
                pw.println(kursant.toString());
            }
        }
    }
}

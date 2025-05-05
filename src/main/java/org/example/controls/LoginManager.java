package org.example.controls;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

import org.example.models.Kursant;

public class LoginManager {
    private final String [] filePaths;

    private ArrayList<Kursant> kursanci;

    public LoginManager(String[] filePaths) {
        this.filePaths = filePaths;
    }

    public String login(String email, String password) {
        for(int i = 0; i < filePaths.length; i++){
            String filePath = filePaths[i];
            try {
                //konwersja hasla na md5
                String md5Password = convertToMD5(password);

                //wczytaniepliku
                File file = new File(filePath);
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] dane = line.split(";");
                    if (email.equals(dane[11]) && md5Password.equals(dane[13])) {
                          return filePath;
                     }
                }
                scanner.close();
            } catch (NoSuchAlgorithmException e) {
                System.out.printf("Nie znaleziono algorytmu hashujacego " + e.getMessage());
            } catch (FileNotFoundException e) {
                System.out.println("Nie znaleziono pliku: " + filePath);
            }
    }
        return "not found user";
    }

    private String convertToMD5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}

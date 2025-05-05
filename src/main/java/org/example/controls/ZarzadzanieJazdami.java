package org.example.controls;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.example.models.JazdyPraktyczne;
import org.example.models.Kursant;

public class ZarzadzanieJazdami {
    private static final String PLIK = "JazdyPraktyczne.txt";

    public static void zapiszJazde(JazdyPraktyczne jazda) {
        List<JazdyPraktyczne> jazdy = wczytajJazdy();
        jazdy.add(jazda);
        zapiszDoPliku(jazdy);
    }

    public static List<JazdyPraktyczne> wczytajJazdy() {
        List<JazdyPraktyczne> jazdy = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new ObjectInputStream(new FileInputStream(PLIK)))) {
            jazdy = (List<JazdyPraktyczne>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return jazdy;
    }

    public static void zapiszDoPliku(List<JazdyPraktyczne> jazdy) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PLIK))) {
            oos.writeObject(jazdy);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void wyswietlJazdy() {
        List<JazdyPraktyczne> jazdy = wczytajJazdy();
        for (JazdyPraktyczne jazda : jazdy) {
            System.out.println(jazda);
        }
    }

    public static void edytujJazde(int index, JazdyPraktyczne nowaJazda) {
        List<JazdyPraktyczne> jazdy = wczytajJazdy();
        if (index >= 0 && index < jazdy.size()) {
            jazdy.set(index, nowaJazda);
            zapiszDoPliku(jazdy);
        }
    }

    public static void usunJazde(int index) {
        List<JazdyPraktyczne> jazdy = wczytajJazdy();
        if (index >= 0 && index < jazdy.size()) {
            jazdy.remove(index);
            zapiszDoPliku(jazdy);
        }
    }

    public static void potwierdzZapis(int index, Kursant Kursant) {
        List<JazdyPraktyczne> jazdy = wczytajJazdy();
        if (index >= 0 && index < jazdy.size()) {
            JazdyPraktyczne jazda = jazdy.get(index);
            if (jazda.isCzyWolna()) {
                jazda.setKursant(Kursant);
                jazda.setCzyWolna(false);
                zapiszDoPliku(jazdy);
            }
        }
    }


}

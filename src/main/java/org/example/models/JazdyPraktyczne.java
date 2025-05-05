package org.example.models;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class JazdyPraktyczne implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L; // Dodanie serialVersionUID
    private int idJazdyPraktycznej;
    private Kursant kursant;
    private int instruktor;
    private LocalDateTime godzinaRoz;
    private LocalDateTime godzinaZak;
    private boolean czyWolna;

    //konstuktor
    public JazdyPraktyczne(int idJP, Kursant k, int i, LocalDateTime gR, LocalDateTime gZ) {
        this.idJazdyPraktycznej = idJP;
        this.kursant = k;
        this.instruktor = i;
        this.godzinaRoz = gR;
        this.godzinaZak = gZ;
        this.czyWolna = false;
    }

    public JazdyPraktyczne(int idJazdyPraktycznej, int instruktor, LocalDateTime godzinaRoz, LocalDateTime godzinaZak) {
        this.idJazdyPraktycznej = idJazdyPraktycznej;
        this.instruktor = instruktor;
        this.godzinaRoz = godzinaRoz;
        this.godzinaZak = godzinaZak;
        this.czyWolna = true;
    }

    //metody dostepowe (getter i settery)
    public int getIdJazdyPraktyczne() {
        return idJazdyPraktycznej;
    }

    public void setIdJazdyPraktycznej(int idJazdyPraktycznej) {
        this.idJazdyPraktycznej = idJazdyPraktycznej;
    }

    public Kursant getKursant() {
        return kursant;
    }

    public void setKursant(Kursant kursant) {
        this.kursant = kursant;
    }

//    public Instruktor getInstruktor() {
//        return instruktor;
//    }
//
//    public void setInstruktor(Instruktor instruktor) {
//        this.instruktor = instruktor;
//    }

    public LocalDateTime getGodzinaRoz() {
        return godzinaRoz;
    }

    public void setGodzinaRoz(LocalDateTime godzinaRoz) {
        this.godzinaRoz = godzinaRoz;
    }

    public LocalDateTime getGodzinaZak() {
        return godzinaZak;
    }

    public void setGodzinaZak(LocalDateTime godzinaZak) {
        this.godzinaZak = godzinaZak;
    }

    public boolean isCzyWolna() {
        return czyWolna;
    }

    public void setCzyWolna(boolean czyWolna) {
        this.czyWolna = czyWolna;
    }
}

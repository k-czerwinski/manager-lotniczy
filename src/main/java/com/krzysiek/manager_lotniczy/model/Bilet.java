package com.krzysiek.manager_lotniczy.model;

public class Bilet {
    private int id;
    private int numerLotu;
    private int klasaSiedzenia;
    private String imie;
    private String drugieImie;
    private String nazwisko;
    private String numerDowodu;
    private String numerPaszportu;

    public Bilet(int numerLotu) {
        this.numerLotu = numerLotu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumerLotu() {
        return numerLotu;
    }

    public void setNumerLotu(int numerLotu) {
        this.numerLotu = numerLotu;
    }

    public int getKlasaSiedzenia() {
        return klasaSiedzenia;
    }

    public void setKlasaSiedzenia(int klasaSiedzenia) {
        this.klasaSiedzenia = klasaSiedzenia;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getDrugieImie() {
        return drugieImie;
    }

    public void setDrugieImie(String drugieImie) {
        this.drugieImie = drugieImie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getNumerDowodu() {
        return numerDowodu;
    }

    public void setNumerDowodu(String numerDowodu) {
        this.numerDowodu = numerDowodu;
    }

    public String getNumerPaszportu() {
        return numerPaszportu;
    }

    public void setNumerPaszportu(String numerPaszportu) {
        this.numerPaszportu = numerPaszportu;
    }

    @Override
    public String toString() {
        return "Bilet{" +
                "id=" + id +
                ", numerLotu='" + numerLotu + '\'' +
                ", klasaSiedzenia=" + klasaSiedzenia +
                ", imie='" + imie + '\'' +
                ", drugieImie='" + drugieImie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", numerDowodu='" + numerDowodu + '\'' +
                ", numerPaszportu='" + numerPaszportu + '\'' +
                '}';
    }
}

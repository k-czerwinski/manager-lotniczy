package com.krzysiek.manager_lotniczy.model;

public class PracownikDTO {
    private TypPracownika typPracownika;
    private String miejscePracyId;
    private String imie;
    private String drugieImie;
    private String nazwisko;
    private int funkcjaId;


    public int getFunkcjaId() {
        return funkcjaId;
    }

    public void setFunkcjaId(int funkcjaId) {
        this.funkcjaId = funkcjaId;
    }

    public TypPracownika getTypPracownika() {
        return typPracownika;
    }

    public void setTypPracownika(TypPracownika typPracownika) {
        this.typPracownika = typPracownika;
    }

    public String getMiejscePracyId() {
        return miejscePracyId;
    }

    public void setMiejscePracyId(String miejscePracyId) {
        this.miejscePracyId = miejscePracyId;
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

    @Override
    public String toString() {
        return "PracownikDTO{" +
                "typPracownika=" + typPracownika +
                ", miejscePracyId='" + miejscePracyId + '\'' +
                ", imie='" + imie + '\'' +
                ", drugieImie='" + drugieImie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", funkcjaId=" + funkcjaId +
                '}';
    }

    public enum TypPracownika {
        LOTNISKO("Lotnisko"),
        SKLEP("Sklep"),
        LINIA_LOTNICZA("Linia lotnicza");

        public final String label;
        TypPracownika(String label) {
            this.label = label;
        }
    }
}

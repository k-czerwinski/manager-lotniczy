package com.krzysiek.manager_lotniczy.model;

public class Pracownik {
    private int id;
    private String imie;
    private String drugieImie;
    private String nazwisko;
    private String pracownikLotniska;
    private String pracownikLini;
    private String pracownikSklepu;

    public static final Pracownik.Filtr FILTR = new Filtr();

    public Pracownik(int id, String imie, String drugieImie, String nazwisko, String pracownikLotniska, String pracownikLini, String pracownikSklepu) {
        this.id = id;
        this.imie = imie;
        this.drugieImie = drugieImie;
        this.nazwisko = nazwisko;
        this.pracownikLotniska = pracownikLotniska;
        this.pracownikLini = pracownikLini;
        this.pracownikSklepu = pracownikSklepu;
    }

    public int getId() {
        return id;
    }

    public String getImie() {
        return imie;
    }

    public String getDrugieImie() {
        return drugieImie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getPracownikLotniska() {
        return pracownikLotniska;
    }

    public String getPracownikLini() {
        return pracownikLini;
    }

    public String getPracownikSklepu() {
        return pracownikSklepu;
    }

    public String toHTMLRow() {
        return "<td>%s</td>".repeat(7).formatted(
                id,
                imie,
                drugieImie,
                nazwisko,
                pracownikLotniska,
                pracownikLini,
                pracownikSklepu
        );
    }

    public enum Kolumna {
        ID("id", "id", "ID"),
        IMIE("imie", "imie", "Imię"),
        DRUGIE_IMIE("drugie_imie", "drugie-imie", "Drugie imię"),
        NAZWISKO("nazwisko", "nazwisko", "Nazwisko"),
        PRACOWNIK_LOTNISKA("pracownik_lotniska", "pracownik-lotniska", "Pracownik lotniska"),
        PRACOWNIK_LINI("pracownik_lini", "pracownik-lini", "Pracownik linii"),
        PRACOWNIK_SKLEPU("pracownik_sklepu", "pracownik-sklep", "Pracownik sklepu");

        public final String queryLabel;
        public final String htmlId;
        public final String columnLabel;

        Kolumna(String queryLabel, String htmlId, String columnLabel) {
            this.queryLabel = queryLabel;
            this.htmlId = htmlId;
            this.columnLabel = columnLabel;
        }

        public String getHtmlId() {
            return htmlId;
        }

        public String getColumnLabel() {
            return columnLabel;
        }
    }

    public static class Filtr{
        private String imie = "";
        private String nazwisko = "";

        public String getImie() {
            return imie;
        }

        public void setImie(String imie) {
            this.imie = imie;
        }

        public String getNazwisko() {
            return nazwisko;
        }

        public void setNazwisko(String naziwsko) {
            this.nazwisko = naziwsko;
        }
    }

}

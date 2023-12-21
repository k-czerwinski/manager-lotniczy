package com.krzysiek.manager_lotniczy.model;

public class Samolot {
    private String numer;
    private String model;
    private int liczbaPasazerowKlasa1;
    private int liczbaPasazerowKlasa2;
    private int liczbaZalogi;
    private String nazwaLiniLotniczej;
    private int liniaLotniczaId;

    public int getLiniaLotniczaId() {
        return liniaLotniczaId;
    }

    public void setLiniaLotniczaId(int liniaLotniczaId) {
        this.liniaLotniczaId = liniaLotniczaId;
    }

    public String getNumer() {
        return numer;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getLiczbaPasazerowKlasa1() {
        return liczbaPasazerowKlasa1;
    }

    public void setLiczbaPasazerowKlasa1(int liczbaPasazerowKlasa1) {
        this.liczbaPasazerowKlasa1 = liczbaPasazerowKlasa1;
    }

    public int getLiczbaPasazerowKlasa2() {
        return liczbaPasazerowKlasa2;
    }

    public void setLiczbaPasazerowKlasa2(int liczbaPasazerowKlasa2) {
        this.liczbaPasazerowKlasa2 = liczbaPasazerowKlasa2;
    }

    public int getLiczbaZalogi() {
        return liczbaZalogi;
    }

    public void setLiczbaZalogi(int liczbaZalogi) {
        this.liczbaZalogi = liczbaZalogi;
    }

    public String getNazwaLiniLotniczej() {
        return nazwaLiniLotniczej;
    }

    public void setNazwaLiniLotniczej(String nazwaLiniLotniczej) {
        this.nazwaLiniLotniczej = nazwaLiniLotniczej;
    }


    @Override
    public String toString() {
        return "Samolot{" +
                "numer='" + numer + '\'' +
                ", model='" + model + '\'' +
                ", liczbaPasazerowKlasa1=" + liczbaPasazerowKlasa1 +
                ", liczbaPasazerowKlasa2=" + liczbaPasazerowKlasa2 +
                ", liczbaZalogi=" + liczbaZalogi +
                ", nazwaLiniLotniczej='" + nazwaLiniLotniczej + '\'' +
                ", liniaLotniczaId=" + liniaLotniczaId +
                '}';
    }

    public Samolot() {
    }

    public Samolot(String numer, String model, int liczbaPasazerowKlasa1, int liczbaPasazerowKlasa2, int liczbaZalogi, String nazwaLiniLotniczej) {
        this.numer = numer;
        this.model = model;
        this.liczbaPasazerowKlasa1 = liczbaPasazerowKlasa1;
        this.liczbaPasazerowKlasa2 = liczbaPasazerowKlasa2;
        this.liczbaZalogi = liczbaZalogi;
        this.nazwaLiniLotniczej = nazwaLiniLotniczej;
    }

    public String toHtmlString() {
        return "<td>%s</td>".repeat(6).formatted(
                numer,
                model,
                liczbaPasazerowKlasa1,
                liczbaPasazerowKlasa2,
                liczbaZalogi,
                nazwaLiniLotniczej
        );
    }

    public enum Kolumna{
        NUMER("Numer", "numer", "numer-samolotu"),
        MODEL("Model", "model", "model"),
        LICZBA_PASAZEROW_KLASA_1("Liczba pasażerów w klasie 1", "liczba_pasazerow_klasa_1", "liczba-pasazerow-klasa-1"),
        LICZBA_PASAZEROW_KLASA_2("Liczba pasażerów w klasie 2", "liczba_pasazerow_klasa_2", "liczba-pasazerow-klasa-2"),
        LICZBA_ZALOGI("Liczba załogi", "liczba_zalogi", "liczba-zalogi"),
        NAZWA_LINI_LOTNICZEJ("Nazwa lini lotniczej", "nazwa_lini_lotniczej", "nazwa-lini-lotniczej");

        public final String columnLabel;
        public final String queryLabel;
        public final String htmlId;

        Kolumna(String columnLabel, String queryLabel, String htmlId) {
            this.columnLabel = columnLabel;
            this.queryLabel = queryLabel;
            this.htmlId = htmlId;
        }

        public String getColumnLabel() {
            return columnLabel;
        }

        public String getQueryLabel() {
            return queryLabel;
        }

        public String getHtmlId() {
            return htmlId;
        }
    }
}

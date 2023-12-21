package com.krzysiek.manager_lotniczy.model;

public class Lotnisko {

    public static final Lotnisko WSZYSTKIE_LOTNISKA = new Lotnisko("", "ALL", "Wszystkie lotniska", null);
    private String ICAO;
    private String IATA;
    private String nazwa;
    private String miasto;

    public String toHTMLRow() {
        return String.format("<td>%s</td><td>%s</td><td>%s</td><td>%s</td>", ICAO, IATA, nazwa, miasto);
    }
    public Lotnisko(String ICAO, String IATA, String nazwa, String miasto) {
        this.ICAO = ICAO;
        this.IATA = IATA;
        this.nazwa = nazwa;
        this.miasto = miasto;
    }

    public Lotnisko() {
    }

    public String getICAO() {
        return ICAO;
    }

    public void setICAO(String ICAO) {
        this.ICAO = ICAO;
    }

    public String getIATA() {
        return IATA;
    }

    public void setIATA(String IATA) {
        this.IATA = IATA;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public enum Kolumna{
        ICAO("ICAO", "lotnisko-icao"),
        IATA("IATA", "lotnisko-iata"),
        NAZWA("nazwa", "lotnisko-nazwa"),
        MIASTO("miasto", "lotnisko-miasto");

        private String columnLabel;
        private String htmlId;

        Kolumna(String columnLabel, String htmlId) {
            this.columnLabel = columnLabel;
            this.htmlId = htmlId;
        }

        public String getColumnLabel() {
            return columnLabel;
        }

        public String getHtmlId() {
            return htmlId;
        }
    }
}

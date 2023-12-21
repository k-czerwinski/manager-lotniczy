package com.krzysiek.manager_lotniczy.model;

import org.springframework.util.StringUtils;

import java.time.LocalTime;

public class Rejs {
    private String numerRejsu;
    private String lotniskoOdlotuICAO;
    private String lotniskoPrzylotuICAO;
    private LocalTime godzinaOdlotu;
    private LocalTime godzinaPrzylotu;
    private int liniaLotniczaId;
//    not included during insert

    public String toHTMLRow() {

        return String.format("<td>%s</td>".repeat(6),
                numerRejsu, lotniskoOdlotuICAO, lotniskoPrzylotuICAO, godzinaOdlotu, godzinaPrzylotu, liniaLotniczaNazwa);
    }
    private String liniaLotniczaNazwa;

    public void setLiniaLotniczaNazwa(String liniaLotniczaNazwa) {
        this.liniaLotniczaNazwa = liniaLotniczaNazwa;
    }

    public String getLiniaLotniczaNazwa() {
        return liniaLotniczaNazwa;
    }

    public String getNumerRejsu() {
        return numerRejsu;
    }

    public void setNumerRejsu(String numerRejsu) {
        this.numerRejsu = numerRejsu;
    }

    public String getLotniskoOdlotuICAO() {
        return lotniskoOdlotuICAO;
    }

    public void setLotniskoOdlotuICAO(String lotniskoOdlotuICAO) {
        this.lotniskoOdlotuICAO = lotniskoOdlotuICAO;
    }

    public String getLotniskoPrzylotuICAO() {
        return lotniskoPrzylotuICAO;
    }

    public void setLotniskoPrzylotuICAO(String lotniskoPrzylotuICAO) {
        this.lotniskoPrzylotuICAO = lotniskoPrzylotuICAO;
    }

    public LocalTime getGodzinaOdlotu() {
        return godzinaOdlotu;
    }

    public void setGodzinaOdlotu(LocalTime godzinaOdlotu) {
        this.godzinaOdlotu = godzinaOdlotu;
    }

    public LocalTime getGodzinaPrzylotu() {
        return godzinaPrzylotu;
    }

    public void setGodzinaPrzylotu(LocalTime godzinaPrzylotu) {
        this.godzinaPrzylotu = godzinaPrzylotu;
    }

    public int getLiniaLotniczaId() {
        return liniaLotniczaId;
    }

    public void setLiniaLotniczaId(int liniaLotniczaId) {
        this.liniaLotniczaId = liniaLotniczaId;
    }

    public enum Kolumna{
        NUMER_REJSU("Numer Rejsu", "numer_rejsu", "numer-rejsu"),
        LOTNISKO_ODLOTU_ICAO("Lotnisko odlotu ICAO", "lotnisko_odlotu_ICAO", "lotnisko-odlotu-icao"),
        LOTNISKO_PRZYLOTU_ICAO("Lotnisko przylotu ICAO", "lotnisko_przylotu_ICAO", "lotnisko-przylotu-icao"),
        GODZINA_ODLOTU("Godzina odlotu", "godzina_odlotu", "godzina-odlotu"),
        GODZINA_PRZYLOTU("Godzina przylotu", "godzina_przylotu", "godzina-przylotu"),
        LINIA_LOTNICZA_NAZWA("Nazwa lini lotniczej", "linia_lotnicza_nazwa", "linia-lotnicza-nazwa");

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

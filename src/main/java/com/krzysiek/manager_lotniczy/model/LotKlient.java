package com.krzysiek.manager_lotniczy.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record LotKlient(
        int numerLotu,
        String lotniskoOdlotu,
        String lotniskoOdlotuKod,
        LocalDate dataOdlotu,
        LocalTime czasOdlotu,
        String lotniskoPrzylotu,
        String lotniskoPrzylotuKod,
        LocalDate dataPrzylotu,
        LocalTime czasPrzylotu,
        BigDecimal cenaBiletu1Klasa,
        BigDecimal cenaBiletu2Klasa) {


    public String toHTMLRow() {
        return "<td>" + lotniskoOdlotu + "</td>" +
                "<td>" + lotniskoOdlotuKod + "</td>" +
                "<td>" + dataOdlotu + "</td>" +
                "<td>" + czasOdlotu + "</td>" +
                "<td>" + lotniskoPrzylotu + "</td>" +
                "<td>" + lotniskoPrzylotuKod + "</td>" +
                "<td>" + dataPrzylotu + "</td>" +
                "<td>" + czasPrzylotu + "</td>" +
                "<td>" + cenaBiletu1Klasa + "</td>" +
                "<td>" + cenaBiletu2Klasa + "</td>";
    }

    public static final Filter FILTER = new Filter();

    public enum Kolumna {
        LOTNISKO_ODLOTU("'lo.\"miasto\"'", "lotnisko-odlotu", "Lotnisko odlotu"),
        LOTNISKO_ODLOTU_KOD("'lo.\"ICAO\"'", "lotnisko-odlotu-kod", "Lotnisko odlotu kod"),
        DZIEN_ODLOTU("'lds.\"data_odlotu\"'", "dzien-odlotu", "Dzień odlotu"),
        CZAS_ODLOTU("'r.\"godzina_odlotu\"'", "czas-odlotu", "Czas odlotu"),
        LOTNISKO_PRZYLOTU("'lp.\"miasto\"'", "lotnisko-przylotu", "Lotnisko przylotu"),
        LOTNISKO_PRZYLOTU_KOD("'lp.\"ICAO\"'", "lotnisko-przylotu-kod", "Lotnisko przylotu kod"),
        DZIEN_PRZYLOTU("'lds.\"data_przylotu\"'", "dzien-przylotu", "Dzień przylotu"),
        CZAS_PRZYLOTU("'r.\"godzina_przylotu\"'", "czas-przylotu", "Czas przylotu"),
        CENA_BILETU_1_KLASA("'lds.\"cena_biletu_klasa_1\"'", "cena-biletu-1-klasa", "Cena biletu w klasie 1"),
        CENA_BILETU_2_KLASA("'lds.\"cena_biletu_klasa_2\"'", "cena-biletu-2-klasa", "Cena bilet w klasie 2");

        public final String queryLabel;
        public final String htmlId;
        public final String columnLabel;
        private Kolumna(String queryLabel, String htmlId, String columnLabel) {

            this.queryLabel= queryLabel;
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
    public static class Filter {
        private String lotniskoOdlotuICAO = "";
        private String lotniskoOdlotuIATA = "";
        private String lotniskoOdlotuNazwa = "";
        private String lotniskoOdlotuMiasto = "";
        private String lotniskoPrzylotuICAO = "";
        private String lotniskoPrzylotuIATA = "";
        private String lotniskoPrzylotuNazwa = "";
        private String lotniskoPrzylotuMiasto = "";
        private LocalDate dataOdlotuOd;
        private LocalDate dataOdlotuDo;
        private LocalTime godzinaOdlotuOd;
        private LocalTime godzinaOdlotuDo;
        private BigDecimal cenaBiletu1klasaOd;
        private BigDecimal cenaBiletu1klasaDo;
        private BigDecimal cenaBiletu2klasaOd;
        private BigDecimal cenaBiletu2klasaDo;

        public String getLotniskoOdlotuICAO() {
            return lotniskoOdlotuICAO;
        }

        public void setLotniskoOdlotuICAO(String lotniskoOdlotuICAO) {
            this.lotniskoOdlotuICAO = lotniskoOdlotuICAO;
        }

        public String getLotniskoOdlotuIATA() {
            return lotniskoOdlotuIATA;
        }

        public void setLotniskoOdlotuIATA(String lotniskoOdlotuIATA) {
            this.lotniskoOdlotuIATA = lotniskoOdlotuIATA;
        }

        public String getLotniskoOdlotuNazwa() {
            return lotniskoOdlotuNazwa;
        }

        public void setLotniskoOdlotuNazwa(String lotniskoOdlotuNazwa) {
            this.lotniskoOdlotuNazwa = lotniskoOdlotuNazwa;
        }

        public String getLotniskoOdlotuMiasto() {
            return lotniskoOdlotuMiasto;
        }

        public void setLotniskoOdlotuMiasto(String lotniskoOdlotuMiasto) {
            this.lotniskoOdlotuMiasto = lotniskoOdlotuMiasto;
        }

        public String getLotniskoPrzylotuICAO() {
            return lotniskoPrzylotuICAO;
        }

        public void setLotniskoPrzylotuICAO(String lotniskoPrzylotuICAO) {
            this.lotniskoPrzylotuICAO = lotniskoPrzylotuICAO;
        }

        public String getLotniskoPrzylotuIATA() {
            return lotniskoPrzylotuIATA;
        }

        public void setLotniskoPrzylotuIATA(String lotniskoPrzylotuIATA) {
            this.lotniskoPrzylotuIATA = lotniskoPrzylotuIATA;
        }

        public String getLotniskoPrzylotuNazwa() {
            return lotniskoPrzylotuNazwa;
        }

        public void setLotniskoPrzylotuNazwa(String lotniskoPrzylotuNazwa) {
            this.lotniskoPrzylotuNazwa = lotniskoPrzylotuNazwa;
        }

        public String getLotniskoPrzylotuMiasto() {
            return lotniskoPrzylotuMiasto;
        }

        public void setLotniskoPrzylotuMiasto(String lotniskoPrzylotuMiasto) {
            this.lotniskoPrzylotuMiasto = lotniskoPrzylotuMiasto;
        }

        public LocalDate getDataOdlotuOd() {
            return dataOdlotuOd;
        }

        public void setDataOdlotuOd(LocalDate dataOdlotuOd) {
            this.dataOdlotuOd = dataOdlotuOd;
        }

        public LocalDate getDataOdlotuDo() {
            return dataOdlotuDo;
        }

        public void setDataOdlotuDo(LocalDate dataOdlotuDo) {
            this.dataOdlotuDo = dataOdlotuDo;
        }

        public LocalTime getGodzinaOdlotuOd() {
            return godzinaOdlotuOd;
        }

        public void setGodzinaOdlotuOd(LocalTime godzinaOdlotuOd) {
            this.godzinaOdlotuOd = godzinaOdlotuOd;
        }

        public LocalTime getGodzinaOdlotuDo() {
            return godzinaOdlotuDo;
        }

        public void setGodzinaOdlotuDo(LocalTime godzinaOdlotuDo) {
            this.godzinaOdlotuDo = godzinaOdlotuDo;
        }

        public BigDecimal getCenaBiletu1klasaOd() {
            return cenaBiletu1klasaOd;
        }

        public void setCenaBiletu1klasaOd(BigDecimal cenaBiletu1klasaOd) {
            this.cenaBiletu1klasaOd = cenaBiletu1klasaOd;
        }

        public BigDecimal getCenaBiletu1klasaDo() {
            return cenaBiletu1klasaDo;
        }

        public void setCenaBiletu1klasaDo(BigDecimal cenaBiletu1klasaDo) {
            this.cenaBiletu1klasaDo = cenaBiletu1klasaDo;
        }

        public BigDecimal getCenaBiletu2klasaOd() {
            return cenaBiletu2klasaOd;
        }

        public void setCenaBiletu2klasaOd(BigDecimal cenaBiletu2klasaOd) {
            this.cenaBiletu2klasaOd = cenaBiletu2klasaOd;
        }

        public BigDecimal getCenaBiletu2klasaDo() {
            return cenaBiletu2klasaDo;
        }

        public void setCenaBiletu2klasaDo(BigDecimal cenaBiletu2klasaDo) {
            this.cenaBiletu2klasaDo = cenaBiletu2klasaDo;
        }

        public String toSQLFunctionParameters() {
            return "'" + lotniskoOdlotuICAO + "','" +
                    lotniskoOdlotuIATA + "','" +
                    lotniskoOdlotuNazwa + "','" +
                    lotniskoOdlotuMiasto + "','" +
                    lotniskoPrzylotuICAO + "','" +
                    lotniskoPrzylotuIATA + "','" +
                    lotniskoPrzylotuNazwa + "','" +
                    lotniskoPrzylotuMiasto + "'," +
                    (dataOdlotuOd != null ? ("'" + dataOdlotuOd + "'") : null) + "," +
                    (dataOdlotuDo != null ? ("'" + dataOdlotuDo + "'") : null) + ',' +
                    (godzinaOdlotuOd != null ? ("'" + godzinaOdlotuOd + "'") : null) + ',' +
                    (godzinaOdlotuDo != null ? ("'" + godzinaOdlotuDo + "'") : null) + ',' +
                    (cenaBiletu1klasaOd != null ? cenaBiletu1klasaOd : null) + ',' +
                    (cenaBiletu1klasaDo != null ? cenaBiletu1klasaDo : null) + ',' +
                    (cenaBiletu2klasaOd != null ? cenaBiletu2klasaOd : null) + ',' +
                    (cenaBiletu2klasaDo != null ? cenaBiletu2klasaDo : null);
        }

        public void updateValues(Filter filter) {
            this.lotniskoOdlotuICAO = filter.lotniskoOdlotuICAO;
            this.lotniskoOdlotuIATA = filter.lotniskoOdlotuIATA;
            this.lotniskoOdlotuNazwa = filter.lotniskoOdlotuNazwa;
            this.lotniskoOdlotuMiasto = filter.lotniskoOdlotuMiasto;
            this.lotniskoPrzylotuICAO = filter.lotniskoPrzylotuICAO;
            this.lotniskoPrzylotuIATA = filter.lotniskoPrzylotuIATA;
            this.lotniskoPrzylotuNazwa = filter.lotniskoPrzylotuNazwa;
            this.lotniskoPrzylotuMiasto = filter.lotniskoPrzylotuMiasto;
            this.dataOdlotuOd = filter.dataOdlotuOd;
            this.dataOdlotuDo = filter.dataOdlotuDo;
            this.godzinaOdlotuOd = filter.godzinaOdlotuOd;
            this.godzinaOdlotuDo = filter.godzinaOdlotuDo;
            this.cenaBiletu1klasaOd = filter.cenaBiletu1klasaOd;
            this.cenaBiletu1klasaDo = filter.cenaBiletu1klasaDo;
            this.cenaBiletu2klasaOd = filter.cenaBiletu2klasaOd;
            this.cenaBiletu2klasaDo = filter.cenaBiletu2klasaDo;
        }
    }
}
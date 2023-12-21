package com.krzysiek.manager_lotniczy.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class LotAdmin {
    private String lotniskoOdlotuKod;
    private LocalDate dataOdlotu;
    private LocalTime godzinaOdlotu;
    private String lotniskoPrzylotuKod;
    private LocalDate dataPrzylotu;
    private LocalTime godzinaPrzylotu;
    private BigDecimal cenaBiletu1klasa;
    private BigDecimal cenaBiletu2klasa;
    private String nazwaLiniLotniczej;
    private String modelSamolotu;
    private String numerSamolotu;
    private int liczbaZalogi;
    private int idLiniLotniczej;
    private String numerRejsu;
    private String pilot1;
    private String pilot2;

    public int getPilot1() {
        return Integer.parseInt(pilot1);
    }

    public void setPilot1(String pilot1) {
        this.pilot1 = pilot1;
    }

    public int getPilot2() {
        return Integer.parseInt(pilot2);
    }

    public void setPilot2(String pilot2) {
        this.pilot2 = pilot2;
    }

    public String toHTMLRow() {
        return "<td>%s</td>".repeat(12).formatted(
                lotniskoOdlotuKod,
                dataOdlotu,
                godzinaOdlotu,
                lotniskoPrzylotuKod,
                dataPrzylotu,
                godzinaPrzylotu,
                cenaBiletu1klasa,
                cenaBiletu2klasa,
                nazwaLiniLotniczej,
                modelSamolotu,
                numerSamolotu,
                liczbaZalogi
        );
    }

    public String getNumerRejsu() {
        return numerRejsu;
    }

    public void setNumerRejsu(String numerRejsu) {
        this.numerRejsu = numerRejsu;
    }

    public void setDataOdlotu(LocalDate dataOdlotu) {
        this.dataOdlotu = dataOdlotu;
    }

    public void setDataPrzylotu(LocalDate dataPrzylotu) {
        this.dataPrzylotu = dataPrzylotu;
    }

    public void setCenaBiletu1klasa(BigDecimal cenaBiletu1klasa) {
        this.cenaBiletu1klasa = cenaBiletu1klasa;
    }

    public void setCenaBiletu2klasa(BigDecimal cenaBiletu2klasa) {
        this.cenaBiletu2klasa = cenaBiletu2klasa;
    }

    public void setNumerSamolotu(String numerSamolotu) {
        this.numerSamolotu = numerSamolotu;
    }

    public int getIdLiniLotniczej() {
        return idLiniLotniczej;
    }

    public String getLotniskoOdlotuKod() {
        return lotniskoOdlotuKod;
    }

    public LocalDate getDataOdlotu() {
        return dataOdlotu;
    }

    public LocalTime getGodzinaOdlotu() {
        return godzinaOdlotu;
    }

    public String getLotniskoPrzylotuKod() {
        return lotniskoPrzylotuKod;
    }

    public LocalDate getDataPrzylotu() {
        return dataPrzylotu;
    }

    public LocalTime getGodzinaPrzylotu() {
        return godzinaPrzylotu;
    }

    public BigDecimal getCenaBiletu1klasa() {
        return cenaBiletu1klasa;
    }

    public BigDecimal getCenaBiletu2klasa() {
        return cenaBiletu2klasa;
    }

    public String getNazwaLiniLotniczej() {
        return nazwaLiniLotniczej;
    }

    public String getModelSamolotu() {
        return modelSamolotu;
    }

    public String getNumerSamolotu() {
        return numerSamolotu;
    }

    public int getLiczbaZalogi() {
        return liczbaZalogi;
    }

    public LotAdmin(String lotniskoOdlotuKod, LocalDate dataOdlotu, LocalTime godzinaOdlotu, String lotniskoPrzylotuKod, LocalDate dataPrzylotu, LocalTime godzinaPrzylotu, BigDecimal cenaBiletu1klasa, BigDecimal cenaBiletu2klasa, String nazwaLiniLotniczej, String modelSamolotu, String numerSamolotu, int liczbaZalogi) {
        this.lotniskoOdlotuKod = lotniskoOdlotuKod;
        this.dataOdlotu = dataOdlotu;
        this.godzinaOdlotu = godzinaOdlotu;
        this.lotniskoPrzylotuKod = lotniskoPrzylotuKod;
        this.dataPrzylotu = dataPrzylotu;
        this.godzinaPrzylotu = godzinaPrzylotu;
        this.cenaBiletu1klasa = cenaBiletu1klasa;
        this.cenaBiletu2klasa = cenaBiletu2klasa;
        this.nazwaLiniLotniczej = nazwaLiniLotniczej;
        this.modelSamolotu = modelSamolotu;
        this.numerSamolotu = numerSamolotu;
        this.liczbaZalogi = liczbaZalogi;
    }

    public LotAdmin() {
    }

    public LotAdmin(int idLiniLotniczej) {
        this.idLiniLotniczej = idLiniLotniczej;
    }

    public enum Kolumna {
        LOTNISKO_ODLOTU_KOD("lo.\"ICAO\"", "lotnisko-odlotu-icao", "Lotnisko odlotu kod"),
        DATA_ODLOTU("lds.\"data_odlotu\"", "data-odlotu", "Data odlotu"),
        GODZINA_ODLOTU("r.\"godzina_odlotu\"", "godzina-odlotu", "Godzina odlotu"),
        LOTNISKO_PRZYLOTU_KOD("lp.\"ICAO\"", "lotnisko-przylotu-icao", "Lotnisko przylotu kod"),
        DATA_PRZYLOTU("lds.\"data_przylotu\"", "data-przylotu", "Data przylotu"),
        GODZINA_PRZYLOTU("r.\"godzina_przylotu\"", "godzina-przylotu", "Godzina przylotu"),
        CENA_BILETU_1_KLASA("lds.\"cena_biletu_klasa_1\"", "cena-biletu-1-klasa", "Cena biletu 1. klasa"),
        CENA_BILETU_2_KLASA("lds.\"cena_biletu_klasa_2\"", "cena-biletu-2-klasa", "Cena biletu 2. klasa"),
        NAZWA_LINII_LOTNICZEJ("ll.\"nazwa\"", "nazwa-lini-lotniczej", "Nazwa linii lotniczej"),
        MODEL_SAMOLOTU("s.\"model\"", "model-samolotu", "Model samolotu"),
        NUMER_SAMOLOTU("s.\"numer\"", "numer-samolotu", "Numer samolotu"),
        LICZBA_ZALOGI("s.\"liczba_zalogi\"", "liczba-zalogi", "Liczba załogi");

        public final String queryLabel;
        public final String htmlId;
        public final String columnLabel;
        Kolumna(String queryLabel, String htmlId, String columnLabel) {

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
}

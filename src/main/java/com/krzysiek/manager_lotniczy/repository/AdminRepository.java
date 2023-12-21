package com.krzysiek.manager_lotniczy.repository;

import com.krzysiek.manager_lotniczy.mappers.*;
import com.krzysiek.manager_lotniczy.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class AdminRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void dodajLotnisko(Lotnisko lotnisko) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValues(Map.of("icao", lotnisko.getICAO(), "iata", lotnisko.getIATA(), "nazwa", lotnisko.getNazwa(), "miasto", lotnisko.getMiasto()));
        namedParameterJdbcTemplate.update("INSERT INTO lotnisko(\"ICAO\", \"IATA\", \"nazwa\", \"miasto\") values (:icao, :iata, :nazwa, :miasto)", params);
    }

    public void dodajLinieLotnicza(LiniaLotnicza liniaLotnicza) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValues(Map.of("nazwa", liniaLotnicza.getNazwa()));
        namedParameterJdbcTemplate.update("INSERT INTO linia_lotnicza(\"nazwa\") values (:nazwa)", params);
    }

    public void dodajRejs(Rejs rejs) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValues(Map.of("numerRejsu", rejs.getNumerRejsu(), "lotniskoOdlotuICAO", rejs.getLotniskoOdlotuICAO(),
                        "lotniskoPrzylotuICAO", rejs.getLotniskoPrzylotuICAO(), "godzinaOdlotu", rejs.getGodzinaOdlotu(),
                        "godzinaPrzylotu", rejs.getGodzinaPrzylotu(), "liniaLotniczaId", rejs.getLiniaLotniczaId()));
        namedParameterJdbcTemplate.update("INSERT INTO rejs(\"numer_rejsu\", \"lotnisko_odlotu_ICAO\", \"lotnisko_przylotu_ICAO\", " +
                "\"godzina_odlotu\", \"godzina_przylotu\", \"linia_lotnicza_id\") values (:numerRejsu, :lotniskoOdlotuICAO, :lotniskoPrzylotuICAO, :godzinaOdlotu, :godzinaPrzylotu, :liniaLotniczaId)", params);
    }

    public List<RaportLotniskoPracownicy> raportLotniskoPracownicy() {
        return jdbcTemplate.query("SELECT nazwa_lotniska, kierownik_sklepu, barman, kelner, kucharz, personel_sprzatajacy, sprzedawca, " +
                "agent_obsługi_naziemnej_odprawa_biletowa, agent_obsługi_naziemnej_obsługa_bagażu, " +
                "agent_obsługi_naziemnej_informacyjne, agent_obsługi_naziemnej_pomoc_dla_pasażerów, ochrona, specjalista_bezpieczeństwa, pracownik_biurowy, " +
                "personel_administracyjny, kontroler_bezpieczeństwa, planista_lotów, agent_obsługi_naziemnej_zarządzanie_lotami " +
                "from raport_lotnisko_pracownicy", new RaportLotniskoPracownicyRowMapper());
    }

    public List<RaportLotniskoSklepKategoria> raportLotniskoSklepKategoria() {
        return jdbcTemplate.query("SELECT \"ICAO\", nazwa_lotniska, odziez_podrozna, sklep_pamiatkowy, ksiegarnia_lotnicza, elektronika_podrozna, " +
                "kawiarnia_lotnicza, restauracja_widok, sklep_gadzety, sklep_elektronika, sklep_alkohole, sklep_kosmetyki,sklep_akcesoria, sklep_prasa, " +
                "sklep_bizuteria, sklep_dla_dzieci, sklep_obuwie from raport_lotnisko_sklep_kategoria", new RaportLotniskoSklepKategoriaRowMapper());
    }

    public List<RaportLiniaLotnicza> raportLiniaLotnicza() {
        return jdbcTemplate.query("SELECT id,nazwa, obsluga_klienta, sprzedaz_biletow, obsluga_biletow, pilot_kapitan, pilot_1oficer, mechanik_lotniczy_silnik, " +
                "mechanik_lotniczy_awionika, personel_pokladowy, liczba_samolotow from raport_linia_lotnicza", new RaportLiniaLotniczaRowMapper());
    }

    public void dodajSklep(Sklep sklep, int kategoriaId) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValues(Map.of("nazwaSklepu", sklep.nazwa(), "kategoriaId", kategoriaId,
                        "lotniskoICAO", sklep.lotnisko()));
        namedParameterJdbcTemplate.update("INSERT INTO sklep(\"nazwa_sklepu\", \"kategoria_id\", \"lotnisko_ICAO\") values (:nazwaSklepu, :kategoriaId, :lotniskoICAO)", params);
    }

    public List<LotAdmin> znajdzLoty(LotAdmin.Kolumna parametrSortowania, KierunekSortowania kierunekSortowania, int liczbaRekordowNaStronie, int numerStrony) {
        return jdbcTemplate.query("SELECT * from znajdzLotyDlaAdmina('%s', '%s', %s, %s)".formatted(parametrSortowania.queryLabel, kierunekSortowania, liczbaRekordowNaStronie, numerStrony), new LotAdminRowMapper());
    }

    public int znajdzLiczbeWszystkichLotow() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) from lot_data_samolot", Integer.class);
    }

    public Map<String, String> znajdzWszystkieRejsyDlaLiniLotniczej(int liniaLotniczaId) {
        return jdbcTemplate.query("SELECT numer_rejsu, concat(numer_rejsu, ': ', \"lotnisko_odlotu_ICAO\", '(',godzina_odlotu, ') -> ', \"lotnisko_przylotu_ICAO\", '(', godzina_przylotu, ')') as dane_rejsu " +
                        "from rejs r where r.linia_lotnicza_id=" + liniaLotniczaId,
                (rs, rowNum) -> Map.entry(rs.getString(1), rs.getString(2))).stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<String, String> znajdzWszystkieSamolotyDlaLiniLotniczej(int liniaLotniczaId) {
        return jdbcTemplate.query("SELECT \"numer\", \"model\" from samolot where linia_lotnicza_id=" + liniaLotniczaId,
                (rs, rowNum) -> Map.entry(rs.getString(1), rs.getString(2))).stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<String, String> znajdzPilotowLiniLotniczej(int liniaLotniczaId) {
        return jdbcTemplate.query("SELECT id, imie_nazwisko from znajdzPilotowDlaLiniLotniczej(%s)".formatted(liniaLotniczaId),
                (rs, rowNum) -> Map.entry(rs.getString(1), rs.getString(2))).stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void dodajLot(LotAdmin lot) {
        jdbcTemplate.update("INSERT INTO lot_data_samolot (data_odlotu,data_przylotu,numer_rejsu,samolot_id,pilot_1_id,pilot_2_id,cena_biletu_klasa_1,cena_biletu_klasa_2)" +
                " VALUES(?,?,?,?,?,?,?,?)", lot.getDataOdlotu(), lot.getDataPrzylotu(), lot.getNumerRejsu(), lot.getNumerSamolotu(),
                lot.getPilot1(), lot.getPilot2(), lot.getCenaBiletu1klasa(), lot.getCenaBiletu2klasa());
    }

    public List<Pracownik> znajdzPracownikow(Pracownik.Filtr filtr, Pracownik.Kolumna parametrSortowania, KierunekSortowania kierunekSortowania, int liczbaRekordowNaStronie, int numerStrony) {
        return jdbcTemplate.query("SELECT * from znajdzPracownikow('%s','%s', '%s', '%s',%s, %s)".formatted(
                filtr.getImie(), filtr.getNazwisko(), parametrSortowania.queryLabel, kierunekSortowania, liczbaRekordowNaStronie, numerStrony),
                new PracownikRowMapper());
    }

    public Integer znajdzLiczbePracownikow(Pracownik.Filtr filtr) {
        return jdbcTemplate.queryForObject("SELECT znajdzLiczbePracownikow('%s','%s')".formatted(filtr.getImie(), filtr.getNazwisko()), Integer.class);
    }

    public Pracownik znajdzDanePracownika(int pracownikId) {
        return jdbcTemplate.queryForObject("SELECT id, imie, drugie_imie, nazwisko, pracownik_lotniska, pracownik_lini, pracownik_sklepu from pracownik_widok where id = %s LIMIT 1".formatted(pracownikId), new PracownikRowMapper());
    }

    public Map<String, String> znajdzLotniskaBezUstalonychDostepow(int pracownikId) {
        return jdbcTemplate.query("SELECT lotnisko_icao, lotnisko_nazwa from lotniskaBezDostepowDlaPracownika(%s)".formatted(pracownikId),
                (rs, rowNum) -> Map.entry(rs.getString(1), rs.getString(2))).stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<DostepPracownika> znajdzDostepyPracownika(int pracownikId) {
        return jdbcTemplate.query("SELECT pracownik_id, \"lotnisko_ICAO\", dostep_strefa_kontroli_bezpieczenstwa, dostep_strefa_emigracyjna, dostep_plyta_lotniska, dostep_airside " +
                "FROM pracownik_lotnisko_dostep where pracownik_id = %s".formatted(pracownikId), new DostepPracownikaRowMapper());
    }

    public void dodajDostep(DostepPracownika dostep) {
        jdbcTemplate.update("INSERT INTO pracownik_lotnisko_dostep (pracownik_id, \"lotnisko_ICAO\", dostep_strefa_kontroli_bezpieczenstwa, dostep_strefa_emigracyjna, dostep_plyta_lotniska, dostep_airside) " +
                "VALUES(?,?,?,?,?,?)", dostep.getPracownikId(), dostep.getLotniskoICAO(), dostep.isStrefaKontroliBezpieczenstwa(), dostep.isStrefaEmigracyjna(), dostep.isPlytaLotniska(), dostep.isAirSide());
    }

    public Map<Integer, String> znajdzFunkcjePracownikaLiniaLotnicza() {
        return jdbcTemplate.query("SELECT id, nazwa FROM funkcja_pracownika_linia_lotnicza",
                (rs, rowNum) -> Map.entry(rs.getInt("id"), rs.getString("nazwa"))).stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer, String> znajdzFunkcjePracownikaLotnisko() {
        return jdbcTemplate.query("SELECT id, nazwa FROM funkcja_pracownika_lotnisko",
                (rs, rowNum) -> Map.entry(rs.getInt("id"), rs.getString("nazwa"))).stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer, String> znajdzFunkcjePracownikaSklep() {
        return jdbcTemplate.query("SELECT id, nazwa FROM funkcja_pracownika_sklep",
                (rs, rowNum) -> Map.entry(rs.getInt("id"), rs.getString("nazwa"))).stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer, String> znajdzNazwyWszystkichSklepow() {
        return jdbcTemplate.query("SELECT id, concat(\"lotnisko_ICAO\", ' - ', nazwa_sklepu) as nazwa from sklep",
                (rs, rowNum) -> Map.entry(rs.getInt("id"), rs.getString("nazwa"))).stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void dodajPracownika(PracownikDTO pracownik) {
        switch (pracownik.getTypPracownika()) {
            case LOTNISKO -> jdbcTemplate.update("WITH dodaj_pracownika AS (INSERT INTO pracownik (imie, drugie_imie, nazwisko) VALUES (?,?,?) " +
                    "RETURNING id as prac_id)  " +
                    "INSERT INTO pracownik_lotnisko_funkcja(pracownik_id, \"lotnisko_ICAO\", funkcja_id) VALUES ((SELECT prac_id from dodaj_pracownika), ?, ?)", pracownik.getImie(), pracownik.getDrugieImie(), pracownik.getNazwisko(), pracownik.getMiejscePracyId(), pracownik.getFunkcjaId());
            case SKLEP -> jdbcTemplate.update("WITH dodaj_pracownika as (INSERT INTO pracownik(imie, drugie_imie, nazwisko) VALUES (?,?,?) " +
                    "RETURNING id as prac_id) " +
                    "INSERT INTO pracownik_sklep_funkcja(pracownik_id, sklep_id, funkcja_id) VALUES((SELECT prac_id FROM dodaj_pracownika),?,?)", pracownik.getImie(), pracownik.getDrugieImie(), pracownik.getNazwisko(), Integer.parseInt(pracownik.getMiejscePracyId()), pracownik.getFunkcjaId());
            case LINIA_LOTNICZA -> jdbcTemplate.update("WITH dodaj_pracownika as (INSERT INTO pracownik(imie, drugie_imie, nazwisko) VALUES (?,?,?) " +
                    "RETURNING id as prac_id) " +
                    "INSERT INTO pracownik_linia_lotnicza_funkcja(pracownik_id, linia_lotnicza_id, funkcja_id) VALUES((SELECT prac_id FROM dodaj_pracownika),?,?)", pracownik.getImie(), pracownik.getDrugieImie(), pracownik.getNazwisko(), Integer.parseInt(pracownik.getMiejscePracyId()), pracownik.getFunkcjaId());
        }
    }

    public List<Samolot> znajdzSamoloty(KierunekSortowania kierunekSortowania, Samolot.Kolumna parametrSortowania, int liczbaRekordowNaStronie, int numerStrony) {
        return jdbcTemplate.query("SELECT numer, model, liczba_pasazerow_klasa_1, liczba_pasazerow_klasa_2, liczba_zalogi, nazwa_lini_lotniczej " +
                "FROM samolot_widok ORDER BY %s %s LIMIT %d OFFSET %d"
                        .formatted(parametrSortowania.queryLabel, kierunekSortowania, liczbaRekordowNaStronie, (numerStrony - 1) * liczbaRekordowNaStronie),
                new SamolotRowMapper());
    }

    public void dodajSamolot(Samolot samolot) {
        jdbcTemplate.update("INSERT INTO samolot(numer, model, liczba_pasazerow_klasa_1, liczba_pasazerow_klasa_2, liczba_zalogi, linia_lotnicza_id) " +
                "VALUES(?,?,?,?,?,?)", samolot.getNumer(), samolot.getModel(), samolot.getLiczbaPasazerowKlasa1(),
                samolot.getLiczbaPasazerowKlasa2(), samolot.getLiczbaZalogi(), samolot.getLiniaLotniczaId());
    }

    public int znajdzLiczbeSamolotow() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM samolot", Integer.class);
    }

    public void ustawRoleAdmina() {
        jdbcTemplate.execute("set role app_admin;");
    }

    public List<Lotnisko> znajdzPosortowaneLotniska(Lotnisko.Kolumna kolumnaSortowania,
                                                    KierunekSortowania kierunekSortowania,
                                                    int liczbaRekordowNaStronie,
                                                    int numerStrony) {
        return jdbcTemplate.query("SELECT \"IATA\", \"ICAO\", \"nazwa\", \"miasto\" from lotnisko " +
                "ORDER BY \"" + kolumnaSortowania.getColumnLabel() + "\" " + kierunekSortowania +
                " LIMIT " + liczbaRekordowNaStronie + " OFFSET " + (numerStrony - 1) * liczbaRekordowNaStronie, new LotniskoRowMapper());
    }

    public Integer znajdzLiczbeLini() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) from linia_lotnicza", Integer.class);
    }

    public List<LiniaLotnicza> znajdzPosortowaneLinie(LiniaLotnicza.Kolumna kolumnaSortowania,
                                                      KierunekSortowania kierunekSortowania,
                                                      int liczbaRekordowNaStronie,
                                                      int numerStrony) {
        return jdbcTemplate.query("SELECT \"id\", \"nazwa\" from linia_lotnicza " +
                "ORDER BY \"" + kolumnaSortowania.queryLabel + "\" " + kierunekSortowania +
                " LIMIT " + liczbaRekordowNaStronie + " OFFSET " + (numerStrony - 1) * liczbaRekordowNaStronie, ((rs, rowNum) -> new LiniaLotnicza(
                rs.getInt("id"),
                rs.getString("nazwa")
        )));
    }

    public Integer znajdzLiczbeLotnisk() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) from lotnisko", Integer.class);
    }

    public List<Rejs> znajdzPosortowaneRejsy(Rejs.Kolumna kolumnaSortowania,
                                             KierunekSortowania kierunekSortowania,
                                             int liczbaRekordowNaStronie,
                                             int numerStrony) {
        return jdbcTemplate.query("SELECT * from rejs_widok " +
                "ORDER BY \"" + kolumnaSortowania.queryLabel + "\" " + kierunekSortowania +
                " LIMIT " + liczbaRekordowNaStronie + " OFFSET " + (numerStrony - 1) * liczbaRekordowNaStronie, new RejsRowMapper());
    }

    public Integer znajdzLiczbeRejsow() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) from rejs", Integer.class);
    }
}

package com.krzysiek.manager_lotniczy.repository;

import com.krzysiek.manager_lotniczy.mappers.LotKlientRowMapper;
import com.krzysiek.manager_lotniczy.mappers.KlientSklepMapper;
import com.krzysiek.manager_lotniczy.model.Bilet;
import com.krzysiek.manager_lotniczy.model.KierunekSortowania;
import com.krzysiek.manager_lotniczy.model.LotKlient;
import com.krzysiek.manager_lotniczy.model.Sklep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KlientRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<LotKlient> znajdzLoty(LotKlient.Filter filtr,
                                      String sortowanie,
                                      KierunekSortowania kierunekSortowania,
                                      int liczbaRekordowNaStronie,
                                      int numerStrony) {
        return jdbcTemplate.query(String.format("SELECT numer_lotu, lotnisko_odlotu, lotnisko_odlotu_kod, " +
                        "data_odlotu, godzina_odlotu, lotnisko_przylotu, lotnisko_przylotu_kod, data_przylotu, " +
                        "godzina_przylotu, cena_biletu_1_klasa, cena_biletu_2_klasa from znajdzdanelotudlaklienta(" +
                "%s, %s, '%s', %s, %s);",
                filtr.toSQLFunctionParameters(),
                sortowanie,
                kierunekSortowania.toString(),
                liczbaRekordowNaStronie,
                numerStrony
                ), new LotKlientRowMapper());
    }

    public int znajdzLiczbeLotow(LotKlient.Filter filtr) {
        return jdbcTemplate.queryForObject(String.format("SELECT znajdzliczbelotowdlaklienta(%s)", filtr.toSQLFunctionParameters()), (rs, rowNum) -> rs.getInt(1));
    }

    public int znajdzLiczbeSklepow(Sklep.Filtr filtr) {
        return jdbcTemplate.queryForObject(String.format("SELECT znajdzLiczbeSklepowDlaKlienta(%s)", filtr.toSQLFunctionParameters()), Integer.class);
    }

    public List<Sklep> znajdzSklepy(Sklep.Filtr filtr,
                                    String sortowanie,
                                    KierunekSortowania kierunekSortowania,
                                    int liczbaRekordowNaStronie,
                                    int numerStrony) {
        return jdbcTemplate.query(String.format("SELECT * from znajdzsklepydlaklienta(" +
                        "%s, %s, '%s', %s, %s);",
                filtr.toSQLFunctionParameters(),
                sortowanie,
                kierunekSortowania.toString(),
                liczbaRekordowNaStronie,
                numerStrony
        ), new KlientSklepMapper());
    }

    public void dodajBilet(Bilet bilet) {
        jdbcTemplate.update("INSERT INTO bilet (lot_data_samolot_id, klasa_siedzenia, imie, drugie_imie, nazwisko, numer_paszportu, numer_dowodu_osobistego) VALUES (?,?,?,?,?,?,?)",
                bilet.getNumerLotu(), bilet.getKlasaSiedzenia(), bilet.getImie(), bilet.getDrugieImie(), bilet.getNazwisko(), bilet.getNumerPaszportu(), bilet.getNumerDowodu());
    }

    public LotKlient znajdzDaneLotu(int lotId) {
        return jdbcTemplate.queryForObject("SELECT numer_lotu, lotnisko_odlotu, lotnisko_odlotu_kod, data_odlotu, godzina_odlotu, " +
                        "lotnisko_przylotu, lotnisko_przylotu_kod, data_przylotu, godzina_przylotu , cena_biletu_1_klasa, cena_biletu_2_klasa " +
                        "FROM lot_klient where numer_lotu = " + lotId + " LIMIT 1",
                new LotKlientRowMapper());
    }

    public boolean sprawdzDostepnoscLotu(int klasaMiejsca, int lotId) {
        return jdbcTemplate.queryForObject("SELECT sprawdzdostepnosclotu from sprawdzDostepnoscLotu(%d, %d)".formatted(klasaMiejsca, lotId), Boolean.class);
    }

    public void ustawRoleUzytkownika() {
        jdbcTemplate.execute("SET ROLE app_user;");
    }
}

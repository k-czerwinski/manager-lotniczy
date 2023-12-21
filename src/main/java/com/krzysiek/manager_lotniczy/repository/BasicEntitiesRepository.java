package com.krzysiek.manager_lotniczy.repository;

import com.krzysiek.manager_lotniczy.mappers.LotniskoRowMapper;
import com.krzysiek.manager_lotniczy.mappers.RejsRowMapper;
import com.krzysiek.manager_lotniczy.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BasicEntitiesRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<KategoriaSklepu> znajdzWszystkieKategorieSklepow() {
        return jdbcTemplate.query("SELECT id, nazwa_kategorii from kategoria_sklepu",
                ((rs, rowNum) -> new KategoriaSklepu(rs.getInt("id"), rs.getString("nazwa_kategorii"))));
    }

    public List<Lotnisko> znajdzWszystkieLotniska() {
        return jdbcTemplate.query("SELECT \"IATA\", \"ICAO\", \"nazwa\", \"miasto\" from lotnisko", new LotniskoRowMapper());
    }

//    public List<Lotnisko> znajdzPosortowaneLotniska(Lotnisko.Kolumna kolumnaSortowania,
//                                                    KierunekSortowania kierunekSortowania,
//                                                    int liczbaRekordowNaStronie,
//                                                    int numerStrony) {
//        return jdbcTemplate.query("SELECT \"IATA\", \"ICAO\", \"nazwa\", \"miasto\" from lotnisko " +
//                "ORDER BY \"" + kolumnaSortowania.getColumnLabel() + "\" " + kierunekSortowania +
//                " LIMIT " + liczbaRekordowNaStronie + " OFFSET " + (numerStrony - 1) * liczbaRekordowNaStronie, new LotniskoRowMapper());
//    }
//
//    public Integer znajdzLiczbeLini() {
//        return jdbcTemplate.queryForObject("SELECT COUNT(*) from linia_lotnicza", Integer.class);
//    }
//
//    public List<LiniaLotnicza> znajdzPosortowaneLinie(LiniaLotnicza.Kolumna kolumnaSortowania,
//                                                 KierunekSortowania kierunekSortowania,
//                                                 int liczbaRekordowNaStronie,
//                                                 int numerStrony) {
//        return jdbcTemplate.query("SELECT \"id\", \"nazwa\" from linia_lotnicza " +
//                "ORDER BY \"" + kolumnaSortowania.queryLabel + "\" " + kierunekSortowania +
//                " LIMIT " + liczbaRekordowNaStronie + " OFFSET " + (numerStrony - 1) * liczbaRekordowNaStronie, ((rs, rowNum) -> new LiniaLotnicza(
//                        rs.getInt("id"),
//                        rs.getString("nazwa")
//        )));
//    }
//
//    public Integer znajdzLiczbeLotnisk() {
//        return jdbcTemplate.queryForObject("SELECT COUNT(*) from lotnisko", Integer.class);
//    }
//
//    public List<Rejs> znajdzPosortowaneRejsy(Rejs.Kolumna kolumnaSortowania,
//                                             KierunekSortowania kierunekSortowania,
//                                             int liczbaRekordowNaStronie,
//                                             int numerStrony) {
//        return jdbcTemplate.query("SELECT * from rejs_widok " +
//                "ORDER BY \"" + kolumnaSortowania.queryLabel + "\" " + kierunekSortowania +
//                " LIMIT " + liczbaRekordowNaStronie + " OFFSET " + (numerStrony - 1) * liczbaRekordowNaStronie, new RejsRowMapper());
//    }
//
//    public Integer znajdzLiczbeRejsow() {
//        return jdbcTemplate.queryForObject("SELECT COUNT(*) from rejs", Integer.class);
//    }

    public List<LiniaLotnicza> znajdzWszystkieLinieLotnicze() {
        return jdbcTemplate.query("SELECT \"id\", \"nazwa\" from linia_lotnicza", ((rs, rowNum) -> new LiniaLotnicza(
                rs.getInt("id"),
                rs.getString("nazwa")
        )));
    }
}

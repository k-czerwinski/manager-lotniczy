package com.krzysiek.manager_lotniczy.mappers;

import com.krzysiek.manager_lotniczy.model.LotKlient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class LotKlientRowMapper implements RowMapper<LotKlient> {
    @Override
    public LotKlient mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new LotKlient(
                rs.getInt("numer_lotu"),
                rs.getString("lotnisko_odlotu"),
                rs.getString("lotnisko_odlotu_kod"),
                rs.getDate("data_odlotu").toLocalDate(),
                rs.getTime("godzina_odlotu").toLocalTime(),
                rs.getString("lotnisko_przylotu"),
                rs.getString("lotnisko_przylotu_kod"),
                rs.getDate("data_przylotu").toLocalDate(),
                rs.getTime("godzina_przylotu").toLocalTime(),
                rs.getBigDecimal("cena_biletu_1_Klasa"),
                rs.getBigDecimal("cena_biletu_2_klasa")
        );
    }
}

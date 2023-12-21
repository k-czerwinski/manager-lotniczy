package com.krzysiek.manager_lotniczy.mappers;

import com.krzysiek.manager_lotniczy.model.LotAdmin;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LotAdminRowMapper implements RowMapper<LotAdmin> {
    @Override
    public LotAdmin mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new LotAdmin(
                resultSet.getString("lotnisko_odlotu_kod"),
                resultSet.getDate("data_odlotu").toLocalDate(),
                resultSet.getTime("godzina_odlotu").toLocalTime(),
                resultSet.getString("lotnisko_przylotu_kod"),
                resultSet.getDate("data_przylotu").toLocalDate(),
                resultSet.getTime("godzina_przylotu").toLocalTime(),
                resultSet.getBigDecimal("cena_biletu_klasa_1"),
                resultSet.getBigDecimal("cena_biletu_klasa_2"),
                resultSet.getString("nazwa_lini_lotniczej"),
                resultSet.getString("model_samolotu"),
                resultSet.getString("numer_samolotu"),
                resultSet.getInt("liczba_zalogi")
        );
    }
}

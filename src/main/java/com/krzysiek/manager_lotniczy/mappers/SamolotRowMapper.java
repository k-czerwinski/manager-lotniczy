package com.krzysiek.manager_lotniczy.mappers;

import com.krzysiek.manager_lotniczy.model.Samolot;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SamolotRowMapper implements RowMapper<Samolot> {
    @Override
    public Samolot mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new Samolot(
                resultSet.getString("numer"),
                resultSet.getString("model"),
                resultSet.getInt("liczba_pasazerow_klasa_1"),
                resultSet.getInt("liczba_pasazerow_klasa_2"),
                resultSet.getInt("liczba_zalogi"),
                resultSet.getString("nazwa_lini_lotniczej")
        );
    }
}

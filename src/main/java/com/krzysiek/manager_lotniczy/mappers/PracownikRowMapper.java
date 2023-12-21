package com.krzysiek.manager_lotniczy.mappers;

import com.krzysiek.manager_lotniczy.model.Pracownik;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PracownikRowMapper implements RowMapper<Pracownik> {
    @Override
    public Pracownik mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new Pracownik(
                resultSet.getInt("id"),
                resultSet.getString("imie"),
                resultSet.getString("drugie_imie"),
                resultSet.getString("nazwisko"),
                resultSet.getString("pracownik_lotniska"),
                resultSet.getString("pracownik_lini"),
                resultSet.getString("pracownik_sklepu")
        );
    }
}

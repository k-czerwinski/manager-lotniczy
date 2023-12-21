package com.krzysiek.manager_lotniczy.mappers;

import com.krzysiek.manager_lotniczy.model.Sklep;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KlientSklepMapper implements RowMapper<Sklep> {
    @Override
    public Sklep mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Sklep(
                rs.getString("nazwaSklepu"),
                rs.getString("nazwaKategorii"),
                rs.getString("lotnisko")
        );
    }
}

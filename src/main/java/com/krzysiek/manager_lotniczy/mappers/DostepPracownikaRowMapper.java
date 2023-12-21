package com.krzysiek.manager_lotniczy.mappers;


import com.krzysiek.manager_lotniczy.model.DostepPracownika;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DostepPracownikaRowMapper implements RowMapper<DostepPracownika> {
    @Override
    public DostepPracownika mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new DostepPracownika(
                resultSet.getString("lotnisko_ICAO"),
                resultSet.getInt("pracownik_id"),
                resultSet.getBoolean("dostep_strefa_kontroli_bezpieczenstwa"),
                resultSet.getBoolean("dostep_strefa_emigracyjna"),
                resultSet.getBoolean("dostep_plyta_lotniska"),
                resultSet.getBoolean("dostep_airside")
        );
    }
}

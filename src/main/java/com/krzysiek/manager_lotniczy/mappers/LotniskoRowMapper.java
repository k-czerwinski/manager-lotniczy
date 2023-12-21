package com.krzysiek.manager_lotniczy.mappers;

import com.krzysiek.manager_lotniczy.model.Lotnisko;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LotniskoRowMapper implements RowMapper<Lotnisko> {
    @Override
    public Lotnisko mapRow(ResultSet rs, int rowNum) throws SQLException {
        Lotnisko lotnisko = new Lotnisko();
        lotnisko.setIATA(rs.getString("IATA"));
        lotnisko.setICAO(rs.getString("ICAO"));
        lotnisko.setMiasto(rs.getString("miasto"));
        lotnisko.setNazwa(rs.getString("nazwa"));
        return lotnisko;
    }
}

package com.krzysiek.manager_lotniczy.mappers;

import com.krzysiek.manager_lotniczy.model.Rejs;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RejsRowMapper implements RowMapper<Rejs> {
    @Override
    public Rejs mapRow(ResultSet rs, int rowNum) throws SQLException {
        Rejs rejs = new Rejs();
        rejs.setNumerRejsu(rs.getString(Rejs.Kolumna.NUMER_REJSU.queryLabel));
        rejs.setGodzinaOdlotu(rs.getTime(Rejs.Kolumna.GODZINA_ODLOTU.queryLabel).toLocalTime());
        rejs.setGodzinaPrzylotu(rs.getTime(Rejs.Kolumna.GODZINA_PRZYLOTU.queryLabel).toLocalTime());
        rejs.setLiniaLotniczaId(rs.getInt("linia_lotnicza_id"));
        rejs.setLotniskoOdlotuICAO(rs.getString(Rejs.Kolumna.LOTNISKO_ODLOTU_ICAO.queryLabel));
        rejs.setLotniskoPrzylotuICAO(rs.getString(Rejs.Kolumna.LOTNISKO_PRZYLOTU_ICAO.queryLabel));
        rejs.setLiniaLotniczaNazwa(rs.getString(Rejs.Kolumna.LINIA_LOTNICZA_NAZWA.queryLabel));
        return rejs;
    }
}

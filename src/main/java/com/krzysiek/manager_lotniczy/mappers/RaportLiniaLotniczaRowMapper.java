package com.krzysiek.manager_lotniczy.mappers;

import com.krzysiek.manager_lotniczy.model.RaportLiniaLotnicza;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RaportLiniaLotniczaRowMapper implements RowMapper<RaportLiniaLotnicza> {
    @Override
    public RaportLiniaLotnicza mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new RaportLiniaLotnicza(
                resultSet.getInt("id"),
                resultSet.getString("nazwa"),
                resultSet.getInt("liczba_samolotow"),
                resultSet.getInt("mechanik_lotniczy_awionika"),
                resultSet.getInt("mechanik_lotniczy_silnik"),
                resultSet.getInt("obsluga_biletow"),
                resultSet.getInt("obsluga_klienta"),
                resultSet.getInt("personel_pokladowy"),
                resultSet.getInt("pilot_1oficer"),
                resultSet.getInt("pilot_kapitan"),
                resultSet.getInt("sprzedaz_biletow")
        );
    }
}

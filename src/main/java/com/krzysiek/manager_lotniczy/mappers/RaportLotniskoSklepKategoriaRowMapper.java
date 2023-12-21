package com.krzysiek.manager_lotniczy.mappers;

import com.krzysiek.manager_lotniczy.model.RaportLotniskoSklepKategoria;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RaportLotniskoSklepKategoriaRowMapper implements RowMapper<RaportLotniskoSklepKategoria> {
    @Override
    public RaportLotniskoSklepKategoria mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new RaportLotniskoSklepKategoria(
                resultSet.getString("ICAO"),
                resultSet.getString("nazwa_lotniska"),
                resultSet.getInt("odziez_podrozna"),
                resultSet.getInt("sklep_pamiatkowy"),
                resultSet.getInt("ksiegarnia_lotnicza"),
                resultSet.getInt("elektronika_podrozna"),
                resultSet.getInt("kawiarnia_lotnicza"),
                resultSet.getInt("restauracja_widok"),
                resultSet.getInt("sklep_gadzety"),
                resultSet.getInt("sklep_elektronika"),
                resultSet.getInt("sklep_alkohole"),
                resultSet.getInt("sklep_kosmetyki"),
                resultSet.getInt("sklep_akcesoria"),
                resultSet.getInt("sklep_prasa"),
                resultSet.getInt("sklep_bizuteria"),
                resultSet.getInt("sklep_dla_dzieci"),
                resultSet.getInt("sklep_obuwie")
        );
    }
}

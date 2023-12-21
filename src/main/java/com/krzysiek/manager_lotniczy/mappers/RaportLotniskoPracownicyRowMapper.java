package com.krzysiek.manager_lotniczy.mappers;

import com.krzysiek.manager_lotniczy.model.RaportLotniskoPracownicy;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RaportLotniskoPracownicyRowMapper implements RowMapper<RaportLotniskoPracownicy> {
    @Override
    public RaportLotniskoPracownicy mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new RaportLotniskoPracownicy(
                resultSet.getString("nazwa_lotniska"),
                resultSet.getInt("kierownik_sklepu"),
                resultSet.getInt("barman"),
                resultSet.getInt("kelner"),
                resultSet.getInt("kucharz"),
                resultSet.getInt("personel_sprzatajacy"),
                resultSet.getInt("sprzedawca"),
                resultSet.getInt("agent_obsługi_naziemnej_odprawa_biletowa"),
                resultSet.getInt("agent_obsługi_naziemnej_obsługa_bagażu"),
                resultSet.getInt("agent_obsługi_naziemnej_informacyjne"),
                resultSet.getInt("agent_obsługi_naziemnej_pomoc_dla_pasażerów"),
                resultSet.getInt("ochrona"),
                resultSet.getInt("specjalista_bezpieczeństwa"),
                resultSet.getInt("pracownik_biurowy"),
                resultSet.getInt("personel_administracyjny"),
                resultSet.getInt("kontroler_bezpieczeństwa"),
                resultSet.getInt("planista_lotów"),
                resultSet.getInt("agent_obsługi_naziemnej_zarządzanie_lotami")
        );
    }
}

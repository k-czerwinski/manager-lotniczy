package com.krzysiek.manager_lotniczy.model;

public record RaportLiniaLotnicza(
        int id,
        String nazwa,
        int liczbaSamolotow,
        int mechanikLotniczyAwionika,
        int mechanikLotniczySilnik,
        int obslugaBiletow,
        int obslugaKlienta,
        int personelPokladowy,
        int pilot1Oficer,
        int pilotKapitan,
        int sprzedazBiletow
) {
    public String toHtmlString() {
        return "<td>%s</td>".repeat(11).formatted(
                id,
                nazwa,
                liczbaSamolotow,
                mechanikLotniczyAwionika,
                mechanikLotniczySilnik,
                obslugaBiletow,
                obslugaKlienta,
                personelPokladowy,
                pilot1Oficer,
                pilotKapitan,
                sprzedazBiletow
        );
    }
}

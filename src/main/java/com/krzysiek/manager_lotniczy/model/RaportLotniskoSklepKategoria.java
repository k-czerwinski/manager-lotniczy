package com.krzysiek.manager_lotniczy.model;

public record RaportLotniskoSklepKategoria(
        String ICAO,
        String nazwaLotniska,
        int odziezPodrozna,
        int sklepPamiatkowy,
        int ksiegarniaLotnicza,
        int elektronikaPodrozna,
        int kawiarniaLotnicza,
        int restauracjaWidok,
        int sklepGadzety,
        int sklepElektronika,
        int sklepAlkohole,
        int sklepKosmetyki,
        int sklepAkcesoria,
        int sklepPrasa,
        int sklepBizuteria,
        int sklepDlaDzieci,
        int sklepObuwie
) {
    public String toHtmlString() {
        return "<td>%s</td>".repeat(17).formatted(
                ICAO,
                nazwaLotniska,
                odziezPodrozna,
                sklepPamiatkowy,
                ksiegarniaLotnicza,
                elektronikaPodrozna,
                kawiarniaLotnicza,
                restauracjaWidok,
                sklepGadzety,
                sklepElektronika,
                sklepAlkohole,
                sklepKosmetyki,
                sklepAkcesoria,
                sklepPrasa,
                sklepBizuteria,
                sklepDlaDzieci,
                sklepObuwie
        );
    }
}

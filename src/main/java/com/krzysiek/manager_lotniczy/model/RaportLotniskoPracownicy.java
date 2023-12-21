package com.krzysiek.manager_lotniczy.model;

public record RaportLotniskoPracownicy(
        String nazwaLotniska,
        int kierownikSklepu,
        int barman,
        int kelner,
        int kucharz,
        int personelSprzatajacy,
        int sprzedawca,
        int agentObslugiNaziemnejOdprawaBiletowa,
        int agentObslugiNaziemnejObslugaBagazu,
        int agentObslugiNaziemnejInformacyjne,
        int agentObslugiNaziemnejPomocDlaPasażerow,
        int ochrona,
        int specjalistaDsBezpieczenstwa,
        int pracownikBiurowy,
        int personelAdministracyjny,
        int kontrolerBezpieczenstwa,
        int planistaLotow,
        int agentObslugiNaziemnejZarzadzanieLotami
) {
    public String toHtmlString() {
        return "<td>%s</td>".repeat(18).formatted(
                nazwaLotniska,
                kierownikSklepu,
                barman,
                kelner,
                kucharz,
                personelSprzatajacy,
                sprzedawca,
                agentObslugiNaziemnejOdprawaBiletowa,
                agentObslugiNaziemnejObslugaBagazu,
                agentObslugiNaziemnejInformacyjne,
                agentObslugiNaziemnejPomocDlaPasażerow,
                ochrona,
                specjalistaDsBezpieczenstwa,
                pracownikBiurowy,
                personelAdministracyjny,
                kontrolerBezpieczenstwa,
                planistaLotow,
                agentObslugiNaziemnejZarzadzanieLotami
        );
    }
}

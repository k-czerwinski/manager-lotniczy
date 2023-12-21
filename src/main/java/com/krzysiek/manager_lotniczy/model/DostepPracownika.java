package com.krzysiek.manager_lotniczy.model;

public class DostepPracownika {
    private String lotniskoICAO;
    private int pracownikId;
    private boolean strefaKontroliBezpieczenstwa;
    private boolean strefaEmigracyjna;
    private boolean plytaLotniska;
    private boolean airSide;

    public DostepPracownika() {
    }

    public DostepPracownika(int pracownikId) {
        this.pracownikId = pracownikId;
    }

    public DostepPracownika(String lotniskoICAO, int pracownikId, boolean strefaKontroliBezpieczenstwa, boolean strefaEmigracyjna, boolean plytaLotniska, boolean airSide) {
        this.lotniskoICAO = lotniskoICAO;
        this.pracownikId = pracownikId;
        this.strefaKontroliBezpieczenstwa = strefaKontroliBezpieczenstwa;
        this.strefaEmigracyjna = strefaEmigracyjna;
        this.plytaLotniska = plytaLotniska;
        this.airSide = airSide;
    }

    public String getLotniskoICAO() {
        return lotniskoICAO;
    }

    public void setLotniskoICAO(String lotniskoICAO) {
        this.lotniskoICAO = lotniskoICAO;
    }

    public int getPracownikId() {
        return pracownikId;
    }

    public void setPracownikId(int pracownikId) {
        this.pracownikId = pracownikId;
    }

    public boolean isStrefaKontroliBezpieczenstwa() {
        return strefaKontroliBezpieczenstwa;
    }

    public void setStrefaKontroliBezpieczenstwa(boolean strefaKontroliBezpieczenstwa) {
        this.strefaKontroliBezpieczenstwa = strefaKontroliBezpieczenstwa;
    }

    public boolean isStrefaEmigracyjna() {
        return strefaEmigracyjna;
    }

    public void setStrefaEmigracyjna(boolean strefaEmigracyjna) {
        this.strefaEmigracyjna = strefaEmigracyjna;
    }

    public boolean isPlytaLotniska() {
        return plytaLotniska;
    }

    public void setPlytaLotniska(boolean plytaLotniska) {
        this.plytaLotniska = plytaLotniska;
    }

    public boolean isAirSide() {
        return airSide;
    }

    public void setAirSide(boolean airSide) {
        this.airSide = airSide;
    }
}

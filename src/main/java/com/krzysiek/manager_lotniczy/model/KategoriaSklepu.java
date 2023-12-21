package com.krzysiek.manager_lotniczy.model;

public record KategoriaSklepu(Integer id, String nazwa){
    public static final KategoriaSklepu WSZYSTKIE_KATEGORIE = new KategoriaSklepu(null, "Wszystkie kategorie");
    @Override
    public String toString() {
        return nazwa;
    }
}
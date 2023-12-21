package com.krzysiek.manager_lotniczy.model;

public record Sklep(String nazwa, String kategoria, String lotnisko) {
    public String toHTMLRow() {
        return "<td>" + nazwa + "</td>" +
                "<td>" + kategoria + "</td>" +
                "<td>" + lotnisko + "</td>";
    }

    public static final Sklep.Filtr FILTR = new Sklep.Filtr();

    public enum Kolumna {
        NAZWA_SKLEPU("'\"nazwa_sklepu\"'", "nazwa", "Nazwa sklepu"),
        NAZWA_KATEGORII("'\"nazwa_kategorii\"'", "kategoria", "Kategoria"),
        LOTNISKO("'ICAO'", "lotnisko", "Lotnisko");

        public final String queryLabel;
        public final String htmlId;
        public final String columnLabel;
        private Kolumna(String queryLabel, String htmlId, String columnLabel) {

            this.queryLabel= queryLabel;
            this.htmlId = htmlId;
            this.columnLabel = columnLabel;
        }

        public String getQueryLabel() {
            return queryLabel;
        }

        public String getHtmlId() {
            return htmlId;
        }

        public String getColumnLabel() {
            return columnLabel;
        }
    }
    public static class Filtr {
        private String lotniskoICAO = "";
        private Integer kategoriaId = null;

        public String getLotniskoICAO() {
            return lotniskoICAO;
        }

        public void setLotniskoICAO(String lotniskoICAO) {
            this.lotniskoICAO = lotniskoICAO;
        }

        public Integer getKategoriaId() {
            return kategoriaId;
        }

        public void setKategoriaId(Integer kategoriaId) {
            this.kategoriaId = kategoriaId;
        }

        public String toSQLFunctionParameters() {
            return "'" + lotniskoICAO + "'," + kategoriaId;
        }
    }
}

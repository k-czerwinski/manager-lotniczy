package com.krzysiek.manager_lotniczy.model;

public class LiniaLotnicza {
    Integer id;
    String nazwa;

    public String toHTMLRow() {
        return String.format("<td>%s</td><td>%s</td>", id, nazwa);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public LiniaLotnicza() {
    }

    public LiniaLotnicza(Integer id, String nazwa) {
        this.id = id;
        this.nazwa = nazwa;
    }

    public enum Kolumna{
        ID("ID", "linia-lotnicza-id", "id"),
        NAZWA("Nazwa", "linia-lotnicza-nazwa", "nazwa");

        public final String columnLabel;
        public final String htmlId;
        public final String queryLabel;

        Kolumna(String columnLabel, String htmlId, String queryLabel) {
            this.columnLabel = columnLabel;
            this.htmlId = htmlId;
            this.queryLabel = queryLabel;
        }

        public String getColumnLabel() {
            return columnLabel;
        }

        public String getHtmlId() {
            return htmlId;
        }

        public String getQueryLabel() {
            return queryLabel;
        }
    }
}

package com.ecsv.run.domain;

import java.util.Arrays;

public class City extends AbstractDomain{

    private Long ibge_id;
    private String uf;
    private String name;
    private Boolean capital;
    private Double lon;
    private Double lat;
    private String no_accents;
    private String alternative_names;
    private String microregion;
    private String mesoregion;

    public City(final Long ibge_id, final String uf, final String name, final Boolean capital,
                final Double lon, final Double lat, final String no_accents, final String alternative_names,
                final String microregion, final String mesoregion) {
        this.ibge_id = ibge_id;
        this.uf = uf;
        this.name = name;
        this.capital = capital;
        this.lon = lon;
        this.lat = lat;
        this.no_accents = no_accents;
        this.alternative_names = alternative_names;
        this.microregion = microregion;
        this.mesoregion = mesoregion;
    }

    @Override
    public String toString() {
        return "[" + ibge_id +
                "," + uf +
                "," + name +
                "," + capital +
                "," + lon +
                "," + lat +
                "," + no_accents +
                "," + alternative_names +
                "," + microregion +
                "," + mesoregion + "]";
    }

    public enum Properties {

        HEADER(new String[]{"ibge_id", "uf", "name", "capital", "lon", "lat", "no_accents", "alternative_names", "microregion", "mesoregion" });

        private final String[] headers;

        Properties(final String[] headers) {
            this.headers = headers;
        }

        public void checkProperty(final String property) {
            for (String p : headers) {
                if (p.equalsIgnoreCase(property)) {
                    return;
                }
            }
            throw new IllegalArgumentException(property + " is invalid");
        }

        @Override
        public String toString() {
            return Arrays.toString(headers);
        }
    }
}

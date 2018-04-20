package io.gary;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by pugang on 2018/4/20.
 */
public class GeoInfo {
    private String city;
    private String country;
    private String query;

    @JsonView(GeoInfo.class)
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public GeoInfo() {
        this.city = "no";
        this.country = "no";
        this.query = "no";
    }

    @JsonView(GeoInfo.class)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @JsonView(GeoInfo.class)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

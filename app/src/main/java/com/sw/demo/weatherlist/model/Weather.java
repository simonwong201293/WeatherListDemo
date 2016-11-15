package com.sw.demo.weatherlist.model;

/**
 * Created by SimonWong on 15/11/2016.
 */

public class Weather {


    public City city;
    public String cod;
    public Float message;
    public Integer cnt;
    public java.util.List<com.sw.demo.weatherlist.model.Weather.List> list = new java.util.ArrayList<com.sw.demo.weatherlist.model.Weather.List>();
    public class List {
        public Integer dt;
        public Main main;
        public java.util.List<Weather2> weather = new java.util.ArrayList<Weather2>();
        public Clouds clouds;
        public Wind wind;
        public Snow snow;
        public Sys_ sys;
        public String dtTxt;
        public Rain rain;
    }
    public class Main {
        public Double temp;
        public Float tempMin;
        public Float tempMax;
        public Float pressure;
        public Float seaLevel;
        public Float grndLevel;
        public Integer humidity;
        public Integer tempKf;
    }
    public class Rain {
        public Float _3h;
    }
    public class Snow {
    }
    public class Sys {
        public Integer population;
    }
    public class Sys_ {
        public String pod;
    }
    public class Weather2 {
        public Integer id;
        public String main;
        public String description;
        public String icon;
    }
    public class Wind {
        public Float speed;
        public Integer deg;
    }
    public class Coord {
        public Float lon;
        public Float lat;
    }
    public class Clouds {
        public Integer all;
    }
    public class City {
        public Integer id;
        public String name;
        public Coord coord;
        public String country;
        public Integer population;
        public Sys sys;
    }
}

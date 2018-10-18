package ca.bcit.ass1.zhang_asai;

import java.util.ArrayList;
import java.util.List;

public class Country {
    private String flag;

    private String name;

    private String capital;

    private String region;

    private int population;

    private int area;

    private List<String> borders;

    public void setFlag(String flag){
        this.flag = flag;
    }
    public String getFlag(){
        return this.flag;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setCapital(String capital){
        this.capital = capital;
    }
    public String getCapital(){
        return this.capital;
    }
    public void setRegion(String region){
        this.region = region;
    }
    public String getRegion(){
        return this.region;
    }
    public void setPopulation(int population){
        this.population = population;
    }
    public int getPopulation(){
        return this.population;
    }
    public void setArea(int area){
        this.area = area;
    }
    public int getArea(){
        return this.area;
    }
    public void setBorders(List<String> borders){
        this.borders = borders;
    }
    public List<String> getBorders(){
        return this.borders;
    }
}


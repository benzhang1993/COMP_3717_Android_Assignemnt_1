package ca.bcit.ass1.zhang_asai;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Country implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.flag);
        dest.writeString(this.name);
        dest.writeString(this.capital);
        dest.writeString(this.region);
        dest.writeInt(this.population);
        dest.writeInt(this.area);
        dest.writeStringList(this.borders);
    }

    public Country() {
    }

    protected Country(Parcel in) {
        this.flag = in.readString();
        this.name = in.readString();
        this.capital = in.readString();
        this.region = in.readString();
        this.population = in.readInt();
        this.area = in.readInt();
        this.borders = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Country> CREATOR = new Parcelable.Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel source) {
            return new Country(source);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };
}


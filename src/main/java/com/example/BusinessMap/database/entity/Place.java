package com.example.BusinessMap.database.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Places")
public class Place {
    @Id
    private String id;
    private String name;
    private String coordinate;
    private BisenessType bisenessType;
    private int rating;
    @Indexed(direction = IndexDirection.ASCENDING)
    private int price;

    protected Place(){
    }

    public Place(final String name, final String coordinate, final BisenessType bisenessType, final int rating, final int price){
        //this.id = id;
        this.name = name;
        this.coordinate = coordinate;
        this.bisenessType = bisenessType;
        this.rating = rating;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public BisenessType getBisenessType() {
        return bisenessType;
    }

    public void setBisenessType(BisenessType bisenessType) {
        this.bisenessType = bisenessType;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Places{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", coordinate='" + coordinate + '\'' +
                ", bisenessType='" + bisenessType + '\'' +
                ", rating='" + rating + '\'' +
                ", price=" + price +
                '}';
    }
}

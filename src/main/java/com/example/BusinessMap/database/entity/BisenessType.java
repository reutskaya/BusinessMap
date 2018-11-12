package com.example.BusinessMap.database.entity;

public class BisenessType {
    private String name;

    protected BisenessType(){
    }

    public BisenessType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

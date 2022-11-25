package com.example.envitechtest;


import java.util.ArrayList;

public class legends {

    public int Id;
    public ArrayList<Tags>  tags = new ArrayList<>();


    public legends() {
    }

    public legends(int id, ArrayList<Tags> tags) {
        Id = id;
        this.tags = tags;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public ArrayList<Tags> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tags> tags) {
        this.tags = tags;
    }


}

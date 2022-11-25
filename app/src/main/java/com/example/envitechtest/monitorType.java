package com.example.envitechtest;

public class monitorType {

    public int Id;
    public String Name;
    public int LegendId;
    public String description;

    public monitorType() {
    }

    public monitorType(int id, String name, int legendId, String description) {
        Id = id;
        Name = name;
        LegendId = legendId;
        this.description = description;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getLegendId() {
        return LegendId;
    }

    public void setLegendId(int legendId) {
        LegendId = legendId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

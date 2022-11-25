package com.example.envitechtest.Objects;

public class monitor {

    public int Id;
    public String Name;
    public String Desc;
    public int MonitorTypeId;

    public monitor() {
    }

    public monitor(int id, String name, String desc, int monitorTypeId) {
        Id = id;
        Name = name;
        Desc = desc;
        MonitorTypeId = monitorTypeId;
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

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public int getMonitorTypeId() {
        return MonitorTypeId;
    }

    public void setMonitorTypeId(int monitorTypeId) {
        MonitorTypeId = monitorTypeId;
    }
}

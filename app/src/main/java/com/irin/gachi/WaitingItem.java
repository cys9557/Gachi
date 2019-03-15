package com.irin.gachi;

public class WaitingItem {

    String title;
    String location;
    String age_min;
    String age_max;
    String makeRoomTime;

    public WaitingItem() {
    }

    public WaitingItem(String makeRoomTime) {
        this.makeRoomTime = makeRoomTime;
    }

    public WaitingItem(String title, String location, String age_min, String age_max, String makeRoomTime) {
        this.title = title;
        this.location = location;
        this.age_min = age_min;
        this.age_max = age_max;
        this.makeRoomTime = makeRoomTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAge_min() {
        return age_min;
    }

    public void setAge_min(String age_min) {
        this.age_min = age_min;
    }

    public String getAge_max() {
        return age_max;
    }

    public void setAge_max(String age_max) {
        this.age_max = age_max;
    }

    public String getMakeRoomTime() {
        return makeRoomTime;
    }

    public void setMakeRoomTime(String makeRoomTime) {
        this.makeRoomTime = makeRoomTime;
    }
}

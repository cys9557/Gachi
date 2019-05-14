package com.irin.gachi;

public class ShareItem {

    String title;
    String nickname;
    String profileimage;
    String text;
    String picture;
    String reply;
    String time;
    int view;

    public ShareItem() {
    }

    public ShareItem(String title, String nickname, String profileimage, String text, String picture, String reply, String time, int view) {
        this.title = title;
        this.nickname = nickname;
        this.profileimage = profileimage;
        this.text = text;
        this.picture = picture;
        this.reply = reply;
        this.time = time;
        this.view = view;

    }

    public String getProfileimage() {
        return profileimage;
    }

    public void setProfileimage(String profileimage) {
        this.profileimage = profileimage;
    }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }
}

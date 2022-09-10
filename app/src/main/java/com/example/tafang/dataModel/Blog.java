package com.example.tafang.dataModel;

public class Blog {
    int bid;
    String btitle;
    int uid;
    String btime;
    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getBtitle() {
        return btitle;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getBtime() {
        return btime;
    }

    public void setBtime(String btime) {
        this.btime = btime;
    }

    @Override
    public String toString() {
        return "{" +
                "bid=" + bid +
                ", btitle='" + btitle + '\'' +
                ", uid=" + uid +
                ", btime='" + btime + '\'' +
                '}';
    }
}

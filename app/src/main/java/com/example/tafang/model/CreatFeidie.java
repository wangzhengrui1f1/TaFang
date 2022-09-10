package com.example.tafang.model;
//建造飞碟属性
public class CreatFeidie {
    int cfdx;
    int cfdy;
    int livetime;//存在时间-建造时间
    int islive;//是否存在

    public int getIslive() {
        return islive;
    }

    public void setIslive(int islive) {
        this.islive = islive;
    }

    public int getCfdx() {
        return cfdx;
    }

    public void setCfdx(int cfdx) {
        this.cfdx = cfdx;
    }

    public int getCfdy() {
        return cfdy;
    }

    public void setCfdy(int cfdy) {
        this.cfdy = cfdy;
    }

    public int getLivetime() {
        return livetime;
    }

    public void setLivetime(int livetime) {
        this.livetime = livetime;
    }
}

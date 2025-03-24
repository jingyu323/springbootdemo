package com.htkj.station.receiver.comon;


public class JsonObject {
    private String imgName;

    private String position;

    private String rotate;

    private boolean isHorz;

    private boolean isVert;

    private String width;

    private String height;

    public String getWidth() {
        return this.width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return this.height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getRotate() {
        return this.rotate;
    }

    public void setRotate(String rotate) {
        this.rotate = rotate;
    }

    public String getImgName() {
        return this.imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isHorz() {
        return this.isHorz;
    }

    public void setHorz(boolean horz) {
        this.isHorz = horz;
    }

    public boolean isVert() {
        return this.isVert;
    }

    public void setVert(boolean vert) {
        this.isVert = vert;
    }

    public String toString() {
        return "JsonObject{imgName='" + this.imgName + '\'' + ", position='" + this.position + '\'' + ", rotate='" + this.rotate + '\'' + ", isHorz=" + this.isHorz + ", isVert=" + this.isVert + '}';
    }
}


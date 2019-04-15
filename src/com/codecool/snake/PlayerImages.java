package com.codecool.snake;

public class PlayerImages {
    private String bodyImage;
    private String headImage;
    private String laserImage;

    public PlayerImages(String bodyImage, String headImage, String laserImage) {
        this.bodyImage = bodyImage;
        this.headImage = headImage;
        this.laserImage = laserImage;
    }

    public String getBodyImage() {
        return bodyImage;
    }

    public String getHeadImage() {
        return headImage;
    }

    public String getLaserImage() {
        return laserImage;
    }
}

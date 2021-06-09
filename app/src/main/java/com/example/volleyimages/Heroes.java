package com.example.volleyimages;

public class Heroes {
    private String urlImage;
    private String name;

    public Heroes(){}

    public Heroes(String urlImage, String name) {
        this.urlImage = urlImage;
        this.name = name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

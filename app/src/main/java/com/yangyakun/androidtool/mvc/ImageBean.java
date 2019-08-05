package com.yangyakun.androidtool.mvc;

import android.graphics.Bitmap;

public class ImageBean {


    private String imagePath;
    private Bitmap bitmap;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}

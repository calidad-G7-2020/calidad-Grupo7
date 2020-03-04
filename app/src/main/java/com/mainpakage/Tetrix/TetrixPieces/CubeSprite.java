package com.mainpakage.Tetrix.TetrixPieces;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

public class CubeSprite {
    private int x;
    private int y;
    private int ySpeed;
    private Bitmap bmp;
    private View view;


    public CubeSprite(Bitmap bmp, View view) {
        this.x = 0;
        this.y = 0;
        this.ySpeed = 0;
        this.bmp = bmp;
        this.view = view;
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLength(){ return bmp.getWidth();}


    public int getySpeed() {
        return ySpeed;
    }

    public void update(){
        y=y+ySpeed;
    }

    public void onDraw(Canvas canvas){
        canvas.drawBitmap(bmp,x,y,null);
    }

}

package com.mainpakage.tetrix.TetrixPieces;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

public class tNTPowerUp implements PowerUp{

    private CubeSprite[] cubes;
    private final int xIni;
    private final int yIni;
    private final int spriteLength;
    private final int interpieceSpace;
    private boolean used;

    public tNTPowerUp(Bitmap bmp, View view){
        CubeSprite aux = new CubeSprite(bmp, view);
        xIni=aux.getLength()*4;
        yIni=0;
        used=false;
        spriteLength=bmp.getWidth();
        interpieceSpace=0;
        cubes = new CubeSprite[4];
        cubes[0]= new CubeSprite(bmp,view);

        cubes[0].setX(xIni);
        cubes[0].setY(yIni);

    }

    public tNTPowerUp(Bitmap bmp, View view, int xini, int yini){
        xIni=xini;
        yIni=yini;
        used=false;
        spriteLength=bmp.getWidth();
        interpieceSpace=0;
        cubes = new CubeSprite[4];
        cubes[0]= new CubeSprite(bmp,view);


        cubes[0].setX(xIni);
        cubes[0].setY(yIni);
    }

    @Override
    public void rotate90Right() {
    }

    @Override
    public void changeYSpeed(int speed) {
        if(cubes[0]!=null)
            cubes[0].setySpeed(speed);

    }

    @Override
    public boolean removeCube(int y) {
        boolean aux=false;
        for(int i=0;i<4;i++){
            if(cubes[i]!=null&&cubes[i].getY()==y){
                cubes[i]=null;
                aux=true;
            }
        }
        return aux;
    }

    @Override
    public void onDraw(Canvas canvas) {
        if(cubes[0]!=null)
            cubes[0].onDraw(canvas);
    }

    @Override
    public CubeSprite[] getSprites() {
        return cubes;
    }

    @Override
    public int getInterSpace() {
        return this.interpieceSpace;
    }

    @Override
    public void update() {
            if(cubes[0]!=null)
                cubes[0].update();
    }

    public TetrixPiece copyRight(Bitmap bmp,View view){
        tNTPowerUp nuevo = new tNTPowerUp(bmp, view);
        CubeSprite[] c =nuevo.getSprites();
        c[0].setX(cubes[0].getX()+spriteLength);
        c[0].setY(cubes[0].getY());
        return nuevo;
    }

    public void moveRight() {
            cubes[0].setX(cubes[0].getX()+spriteLength);

    }
    public TetrixPiece copyLeft(Bitmap bmp,View view){
        tNTPowerUp nuevo = new tNTPowerUp(bmp, view);
        CubeSprite[] c =nuevo.getSprites();
        c[0].setX(cubes[0].getX()-spriteLength);
        c[0].setY(cubes[0].getY());
        return nuevo;
    }

    public TetrixPiece copyDown(Bitmap bmp,View view){
        tNTPowerUp nuevo = new tNTPowerUp(bmp, view);
        CubeSprite[] c =nuevo.getSprites();
        c[0].setX(cubes[0].getX());
        c[0].setY(cubes[0].getY()+spriteLength);
        return nuevo;
    }

    public TetrixPiece copyRotate(Bitmap bmp,View view){
        tNTPowerUp nuevo = new tNTPowerUp(bmp, view);
        CubeSprite[] c =nuevo.getSprites();
        c[0].setX(cubes[0].getX());
        c[0].setY(cubes[0].getY());
        return nuevo;
    }

    public void moveLeft() {
            cubes[0].setX(cubes[0].getX()-spriteLength);
    }

    public void setBitmap(Bitmap bitmap){
            if(cubes[0]!=null)
                cubes[0].setBmp(bitmap);

    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed() {
        this.used = true;
    }

    @Override
    public int isPowerUp() {
        return 2;
    }
}

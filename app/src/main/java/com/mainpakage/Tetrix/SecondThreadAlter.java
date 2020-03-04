package com.mainpakage.Tetrix;

import com.mainpakage.Tetrix.TetrixPieces.CubeSprite;

public class SecondThreadAlter extends Thread{
    private CustomView cv;
    public boolean running;
    private int cont;
    private int contP;
    private int bottom;
    private int gameSpeed;
    private int contPowerUp;
    public int powerUpSpeed;
    private int powerUpcreation = 500;

    public int getGameSpeed() {
        return gameSpeed;
    }

    public void setGameSpeed(int gameSpeed) {
        this.gameSpeed = gameSpeed;
    }

    public SecondThreadAlter(CustomView customView){
        cv=customView;
        this.running=true;
        this.cont=0;
        this.bottom=1600;
        gameSpeed=7;
        contPowerUp = 0;
        powerUpSpeed = 3;
        contP = 0;
        }

    @Override
    public void run(){
        while(running){
            cv.randomPiece(cv.bmp);
            boolean stop=false;
            while(!stop){
                if((gameSpeed != 1) && cv.isSlowSpeed()){
                    this.setGameSpeed(14);
                }else if(this.getGameSpeed()==14){
                    this.setGameSpeed(7);
                }
                boolean stopauxP = false;
                cv.invalidate();
                try {
                    sleep(100);
                    cont++;
                    if (cv.getActivePowerUp() != null) {
                        contP++;
                    }
                    contPowerUp++;
                }catch(Exception e){}
                if(cont>=gameSpeed){
                    if ((cv.getActivePowerUp() == null) || (cv.getActivePowerUp() != null && cv.colisionActivePower()))
                        cv.getActivePiece().update();
                    cont=0;
                    CubeSprite[] caux=cv.getActivePiece().getSprites();
                    bottom=(cv.cheight-caux[0].getLength());
                }
                if (contPowerUp >= powerUpcreation) {
                    cv.randomActivePowerUp();
                    contPowerUp = 0;
                }
                if (!running) {
                    break;
                }
                if (cv.getActivePowerUp() != null) {
                    if (contP >= powerUpSpeed) {
                        if (cv.colisionPowerActive())
                            cv.getActivePowerUp().update();
                        contP = 0;
                    }
                    stopauxP = !cv.moverAbajoActiva(cv.getActivePowerUp());
                    CubeSprite[] aux2 = cv.getActivePowerUp().getSprites();
                    if(!stopauxP)
                        stopauxP = (bottom <= (aux2[0].getY() + aux2[0].getLength()));
                    if (stopauxP) {
                        cv.getActivePowerUp().changeYSpeed(0);
                        cv.linesUpdate(cv.getActivePowerUp());
                        if (running) {
                            cv.gameOver();
                        }
                        cv.resetPower();
                    }
                }
                int i=0;
                CubeSprite[] aux=cv.getActivePiece().getSprites();
                stop = !cv.moverAbajoActiva(cv.getActivePiece());
                while(!stop&&i<4){
                    stop = bottom <= (aux[i].getY()+aux[i].getLength());
                    i++;
                }
            }
            if (running) {
                cv.getActivePiece().changeYSpeed(0);
                cv.linesUpdate(cv.getActivePiece());
                cv.gameOver();
            }
        }
    }
}

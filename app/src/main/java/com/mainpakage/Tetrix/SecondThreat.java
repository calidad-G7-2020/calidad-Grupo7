package com.mainpakage.Tetrix;

import com.mainpakage.Tetrix.TetrixPieces.CubeSprite;

public class SecondThreat extends Thread {
    private CustomView cv;
    public boolean running;
    private int cont;
    private int contS;

    private int contDownLine;
    private int contSecondPiece;


    private int bottom;
    private int gameSpeed;
    private int trueGameSpeed;
    private final int downLineSpeed = 500;
    private int secondPieceSpeed;
    private final int secondPieceCreation = 300;
    public boolean secondBool;

    public int getTrueGameSpeed() {
        return trueGameSpeed;
    }

    public int getGameSpeed() {
        return gameSpeed;
    }

    public void setGameSpeed(int gameSpeed) {
        this.gameSpeed = gameSpeed;
    }

    public void setTrueGameSpeed(int trueGameSpeed) {
        this.trueGameSpeed = trueGameSpeed;
    }

    public int getSecondPieceSpeed() {
        return secondPieceSpeed;
    }

    public void setSecondPieceSpeed(int secondPieceSpeed) {
        this.secondPieceSpeed = secondPieceSpeed;
    }

    public SecondThreat(CustomView customView) {
        cv = customView;
        running = true;
        cont = 0;
        contS = 0;

        contDownLine = 0;
        contSecondPiece = 0;

        bottom = 1600;
        trueGameSpeed = 7;
        gameSpeed = trueGameSpeed;
        secondPieceSpeed = 3;
        secondBool = false;
    }

    @Override
    public void run() {
        while (running) {
            cv.randomPiece(cv.bmp);
            boolean stop = false;
            while (!stop) {
                cv.invalidate();

                boolean stopaux = false;
                try {
                    sleep(100);
                    cont++;
                    if (cv.getSecondPiece() != null) {
                        contS++;
                    }

                    contDownLine++;
                    contSecondPiece++;

                } catch (Exception e) {
                }
                if (contSecondPiece >= secondPieceCreation) {
                    cv.randomSecondPiece(cv.bmp);
                    contSecondPiece = 0;
                }

                if (contDownLine >= downLineSpeed) {
                    cv.downTop();
                    cv.gameOver();
                    contDownLine = 0;
                }
                if (!running) {
                    break;
                }
                if (cv.getSecondPiece() != null) {
                    if (contS >= secondPieceSpeed) {
                        if ((cv.getActivePiece() == null) || ((cv.getActivePiece() != null) && (cv.colisionActive())))
                            cv.getSecondPiece().update();
                        contS = 0;
                    }
                    stopaux = !cv.moverAbajoActiva(cv.getSecondPiece());
                    int j = 0;
                    CubeSprite[] aux2 = cv.getSecondPiece().getSprites();
                    while (!stopaux && j < 4) {
                        stopaux = bottom <= (aux2[j].getY() + aux2[j].getLength());
                        j++;
                    }
                    if (stopaux) {
                        cv.getSecondPiece().changeYSpeed(0);
                        cv.linesUpdate(cv.getSecondPiece());
                        if (running) {
                            cv.gameOver();
                        }
                        cv.resetSecondPiece();
                    }
                }

                if (cont >= gameSpeed) {
                    if ((cv.getSecondPiece() == null) || (cv.getSecondPiece() != null && cv.colisionSecond()))
                        cv.getActivePiece().update();
                    cont = 0;
                    CubeSprite[] caux = cv.getActivePiece().getSprites();
                    bottom = (cv.cheight - caux[0].getLength());
                }

                int i = 0;
                CubeSprite[] aux = cv.getActivePiece().getSprites();
                stop = !cv.moverAbajoActiva(cv.getActivePiece());
                while (!stop && i < 4) {
                    stop = bottom <= (aux[i].getY() + aux[i].getLength());
                    i++;
                }
                if (stop && (gameSpeed != trueGameSpeed)) {
                    if (secondBool) {
                        gameSpeed = 3;
                        stop = false;
                        cv.getActivePiece().changeYSpeed(0);
                        cv.linesUpdate(cv.getActivePiece());
                        if (running) {
                            cv.gameOver();
                        }
                        cv.switchPiece();
                        cv.resetSecondPiece();
                    }
                }
            }
            if (running) {
                cv.invalidate();
                cv.getActivePiece().changeYSpeed(0);
                cv.linesUpdate(cv.getActivePiece());
                cv.gameOver();
            }
        }
    }
}

package com.mainpakage.tetrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


import com.mainpakage.tetrix.tetrixpieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

public class CustomView extends View {

    Bitmap bmp;
    Bitmap powerBmp1 = BitmapFactory.decodeResource(getResources(), R.drawable.x2);
    Bitmap powerBmp2 = BitmapFactory.decodeResource(getResources(), R.drawable.slowtime);
    Bitmap powerBmp3 = BitmapFactory.decodeResource(getResources(), R.drawable.bomb);
    int score;
    private SecondThreat st;
    private SecondThreadAlter sta;
    List<TetrixPiece> piezas;
    private int nextPiece;
    private TetrixPiece activePiece;
    private TetrixPiece secondPiece;
    private PowerUp activePowerUp;
    List<PowerUp> powerUps;
    private int[] linesInfo;
    MainActivity ma;
    int cwidth;
    int cheight;
    int top; //Línea superior
    Paint paint1;
    Paint paint2;
    private final int cubelength;
    int random;
    boolean enableRandom;
    int gameMode;
    int numLines;
    Random r = new Random();

    public SecondThreat getSt() {
        return st;
    }
    public SecondThreadAlter getSta() {
        return sta;
    }

    public void setSt(SecondThreat st) {
        this.st = st;
    }

    public void setSta(SecondThreadAlter sta) {
        this.sta = sta;
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        piezas = new ArrayList<>();
        powerUps = new ArrayList<>();

        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.cubespritey);

        score = 0;
        linesInfo = new int[50];  //20 is the number of available lines (matrix height)
        nextPiece = 0;
        cwidth = 0;
        cheight = 0;
        CubeSprite caux = new CubeSprite(bmp, this);
        cubelength = caux.getLength();
        top = cubelength;
        paint1 = new Paint();
        paint1.setARGB(255, 255, 0, 0);
        paint1.setStrokeWidth(10);
        paint2 = new Paint();
        paint2.setARGB(255, 255, 255, 0);
        paint2.setStrokeWidth(8);
        secondPiece = null;
        enableRandom = true;
    }

    public TetrixPiece getActivePiece() {
        return activePiece;
    }

    public TetrixPiece getSecondPiece() {
        return secondPiece;
    }

    public void resetSecondPiece() {
        secondPiece = null;
        if (st.getGameSpeed() != st.getTrueGameSpeed()) {
            switchSpeed();
        }
    }


    public void setMa(MainActivity mainActivity, int gameMode) {
        ma = mainActivity;
        this.gameMode = gameMode;
        if (gameMode == 0) {
            setSt(new SecondThreat(this));
            setSta(null);
        } else {
            setSt(null);
            setSta(new SecondThreadAlter(this));
        }
        int palette = ma.palette;
        setCubeSpriteColor(palette);
    }

    public int updateScore() {
        int aux = 1;
        for (PowerUp p : powerUps) {
            if (p.isPowerUp() == 1) {
                x2PowerUp paux = (x2PowerUp) p;
                if (paux.isAlive())
                    aux = aux * 2;
            }
        }
        return (30 * aux);
    }

    public void updateScore(int lines) {
        int scoreaux = 0;
        for (int i = 0; i < lines; i++) {
            scoreaux = scoreaux + updateScore();
        }
        score += scoreaux * lines;
        ma.updateScore("" + score);
    }

    public boolean isSlowSpeed() {
        for (PowerUp p : powerUps) {
            if (p.isPowerUp() == 3) {
                slowPowerUp paux = (slowPowerUp) p;
                if (paux.isAlive())
                    return true;
            }
        }
        return false;
    }

    public void randomPiece(Bitmap bmp) {
        randomPiece(bmp, nextPiece);


        int palette;
        if (enableRandom) {
            palette = (r.nextInt(3));
            if (ma.thm == 1) {
                palette += 3;
            }
        } else {
            palette = random;
        }
        setCubeSpriteColor(palette);

        nextPiece = (r.nextInt(7));
    }

    public void randomPiece(Bitmap bmp, int piece) {
        switch (piece) {
            case 0:
                activePiece = new CubePiece(bmp, this, cubelength * 3, top - cubelength);
                break;
            case 1:
                activePiece = new LinePiece(bmp, this, cubelength * 3, top - cubelength);
                break;
            case 2:
                activePiece = new SPiece(bmp, this, cubelength * 3, top - cubelength);
                break;
            case 3:
                activePiece = new TPiece(bmp, this, cubelength * 3, top - cubelength);
                break;
            case 4:
                activePiece = new ZPiece(bmp, this, cubelength * 3, top - cubelength);
                break;
            case 5:
                activePiece = new JPiece(bmp, this, cubelength * 3, top - cubelength);
                break;
            case 6:
                activePiece = new LPiece(bmp, this, cubelength * 3, top - cubelength);
                break;
            default:
                activePiece = null;
        }
        if (activePiece != null) activePiece.changeYSpeed(bmp.getWidth());
    }

    public void randomSecondPiece(Bitmap bmp) {
        int aux = (r.nextInt(7));
        randomSecondPiece(bmp, aux);
    }

    public void randomSecondPiece(Bitmap bmp, int piece) {
        switch (piece) {
            case 0:
                secondPiece = new CubePiece(bmp, this, cubelength * 3 + 6 * cubelength, top - cubelength);
                break;
            case 1:
                secondPiece = new LinePiece(bmp, this, cubelength * 3 + 4 * cubelength, top - cubelength);
                break;
            case 2:
                secondPiece = new SPiece(bmp, this, cubelength * 3 + 6 * cubelength, top - cubelength);
                break;
            case 3:
                secondPiece = new TPiece(bmp, this, cubelength * 3 + 6 * cubelength, top - cubelength);
                break;
            case 4:
                secondPiece = new ZPiece(bmp, this, cubelength * 3 + 6 * cubelength, top - cubelength);
                break;
            case 5:
                secondPiece = new JPiece(bmp, this, cubelength * 3 + 6 * cubelength, top - cubelength);
                break;
            case 6:
                secondPiece = new LPiece(bmp, this, cubelength * 3 + 6 * cubelength, top - cubelength);
                break;
            default:
                secondPiece = null;
        }
        if (secondPiece != null) secondPiece.changeYSpeed(bmp.getWidth());
    }

    public void randomActivePowerUp() {
        int aux = (r.nextInt(3));
        randomActivePowerUp(aux);
    }

    public void randomActivePowerUp(int piece) {
        switch (piece) {
            case 0:
                activePowerUp = new x2PowerUp(powerBmp1, this, cubelength * 2, top - cubelength);
                break;
            case 1:
                activePowerUp = new slowPowerUp(powerBmp2, this, cubelength * 2, top - cubelength);
                break;
            case 2:
                activePowerUp = new tntPowerUp(powerBmp3, this, cubelength * 2, top - cubelength);
                break;
            default:
                activePowerUp = null;
        }
        if (activePowerUp != null) activePowerUp.changeYSpeed(bmp.getWidth());
        powerUps.add(activePowerUp);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.cwidth = w;
        this.cheight = h;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ma.printNextPiece(nextPiece);
        int xmax = 0;
        while (xmax < cwidth) {
            xmax = xmax + cubelength;
        }
        int ymax = 0;
        while (ymax < cheight) {
            ymax = ymax + cubelength;
        }
        int xpos = 0;
        do {
            canvas.drawLine(xpos, 0, xpos, ymax - cubelength, paint2);
            xpos = xpos + cubelength;
        } while (xpos <= xmax);
        int ypos = 0;
        do {
            canvas.drawLine(0, ypos, xmax - cubelength, ypos, paint2);
            ypos = ypos + cubelength;
        } while (ypos < ymax);
        for (TetrixPiece tp : piezas) {
            tp.onDraw(canvas);
        }

        for (PowerUp pu : powerUps) {
            pu.onDraw(canvas);
        }
        canvas.drawLine(0, top - cubelength, cwidth, top - cubelength, paint1);
        activePiece.onDraw(canvas);
        if (secondPiece != null) {
            secondPiece.onDraw(canvas);
        }
    }


    public boolean isCollisionPiece(TetrixPiece a, TetrixPiece b) {
        CubeSprite[] cubosa = a.getSprites();
        CubeSprite[] cubosb = b.getSprites();
        boolean aux = false;
        int i = 0;
        while (i <= 3 && !aux) {
            int j = 0;
            while (j <= 3 && !aux) {
                if (cubosa[i] != null && cubosb[j] != null) {
                    aux = isCollisionCube(cubosa[i], cubosb[j]);
                }
                j++;
            }
            i++;
        }
        return aux;
    }


    private boolean isCollisionCube(CubeSprite cubeSprite1, CubeSprite cubeSprite2) {
        return (cubeSprite1.getX() == cubeSprite2.getX() && cubeSprite1.getY() == cubeSprite2.getY());
    }


    public void moverIzqDerGirar(TetrixPiece pieza, int coor){
        TetrixPiece nueva;
        switch (coor){
            case 1:
                nueva = pieza.copyLeft(bmp, this);
                break;
            case 2:
                nueva = pieza.copyRight(bmp, this);
                break;
            case 3:
                nueva = pieza.copyRotate(bmp, this);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + coor);
        }
        boolean nochocan = true;
        for (TetrixPiece ptablero : piezas) {
            if (ptablero != pieza)
                nochocan = nochocan && (!isCollisionPiece(nueva, ptablero));
        }
        if (secondPiece != null)
            nochocan = nochocan && (!isCollisionPiece(nueva, secondPiece));
        boolean nofuera = true;
        CubeSprite[] cube = activePiece.getSprites();
        for (CubeSprite c : nueva.getSprites()) {
            nofuera = nofuera && ((c.getX() >= 0) && (c.getX() <= cwidth - cube[0].getLength()));
        }
        if(nochocan && nofuera){
            switch (coor){
                case 1:
                    activePiece.moveLeft();
                    break;
                case 2:
                    activePiece.moveRight();
                    break;
                case 3:
                    activePiece.rotate90Right();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + coor);
            }
        }
    }

    public boolean moverAbajoActiva(TetrixPiece pieza) {
        TetrixPiece nueva = pieza.copyDown(bmp, this);
        boolean nochocan = true;
        for (TetrixPiece ptablero : piezas) {
            if (ptablero != pieza)
                nochocan = nochocan && (!isCollisionPiece(nueva, ptablero));
        }
        return nochocan;
    }

    public boolean colisionSecond() {
        TetrixPiece nueva = activePiece.copyDown(bmp, this);
        boolean nochocan = true;
        if (secondPiece != null)
            nochocan = (!isCollisionPiece(nueva, secondPiece));
        return nochocan;
    }

    public boolean colisionActive() {
        TetrixPiece nueva = secondPiece.copyDown(bmp, this);
        boolean nochocan = true;
        if (activePiece != null)
            nochocan = (!isCollisionPiece(nueva, activePiece));
        return nochocan;
    }

    public boolean colisionPowerActive() {
        TetrixPiece nueva = activePowerUp.copyDown(bmp, this);
        boolean nochocan = true;
        if (activePiece != null)
            nochocan = (!isCollisionPiece(nueva, activePiece));
        return nochocan;
    }

    public boolean colisionActivePower() {
        TetrixPiece nueva = activePiece.copyDown(bmp, this);
        boolean nochocan = true;
        if (activePiece != null)
            nochocan = (!isCollisionPiece(nueva, activePowerUp));
        return nochocan;
    }

    public void resetPower() {
        activePowerUp = null;
    }

    public void switchPiece() {
        if (secondPiece != null) {
            getSt().secondBool = !getSt().secondBool;
            TetrixPiece aux = activePiece;
            activePiece = secondPiece;
            secondPiece = aux;
            switchSpeed();
        }
    }

    public PowerUp getActivePowerUp() {
        return activePowerUp;
    }

    public void switchSpeed() {
        int aux2 = getSt().getGameSpeed();
        getSt().setGameSpeed(getSt().getSecondPieceSpeed());
        getSt().setSecondPieceSpeed(aux2);
    }

    public void updateSprite(CubeSprite[] cubos, int i) {
        if (i < 4) {
            if (cubos[i] != null) {
                int cy = cubos[i].getY() / cubos[i].getLength();
                linesInfo[cy]++;  //Esta línea tiene un nuevo sprite.
            }
            i++;
            updateSprite(cubos, i);
        }
    }

    public void deleteSprite(int[] linesInfo, int aux, int aux2, int i, int j, TetrixPiece piece) {
        if (i < aux) {
            if (linesInfo[i] == aux2) {
                deleteLine(j, cubelength, piece.getInterSpace());
            }
            i++;
            deleteSprite(linesInfo, aux, aux2, i, j, piece);
        }
    }

    public void oneLineMethod(Bitmap oldBmp){
        enableRandom = false;
        random = (r.nextInt(3));
        if(ma.thm == 1) random += 3;
        auxSetCubeSprite(random);
        for (TetrixPiece p : piezas) {
            if (p.isPowerUp() == 0)
                p.setBitmap(bmp);
        }
        bmp = oldBmp;
        setCubeSpriteColor(random);
        this.invalidate();
    }

    public void moreThanOneLineMethod(Bitmap oldBmp){
        enableRandom = true;
        for (TetrixPiece p : piezas) {
            int palette = (r.nextInt(3));
            if (ma.thm == 1) {
                palette += 3;
            }
            auxSetCubeSprite(palette);
            if (p.isPowerUp() == 0)
                p.setBitmap(bmp);
        }
        bmp = oldBmp;
    }

    public void linesUpdate(TetrixPiece piece) {//coordinates of the last piece set
        piezas.add(piece);
        CubeSprite[] cubos = piece.getSprites();

        int i = 0;
        updateSprite(cubos, i);

        CubeSprite[] cube = activePiece.getSprites();
        int aux = (cheight / cube[0].getLength() + 1);
        int aux2 = (cwidth / cube[0].getLength());
        numLines = 0;

        deleteSprite(linesInfo, aux, aux2, 0, 0, piece);

        if(numLines>=1){
            Bitmap oldBmp = bmp;
            if(numLines==1){
                oneLineMethod(oldBmp);
            }else{
                moreThanOneLineMethod(oldBmp);
            }
        }

        updateScore(numLines);
    }

    private void deleteLine(int linea, int spriteLength, int interSpace) {   //eliminar la línea completa y bajar las piezas
        numLines++;
        linesInfo[linea] = 0;             //refinar si es necesario
        int spriteSpace = (spriteLength + interSpace);  //te situas en la altura deseada para borrar horizontalmente
        int y = spriteSpace * linea;

        for (TetrixPiece p : piezas) {
            if (p.removeCube(y)) {
                if (p.isPowerUp() == 1) {
                    x2PowerUp paux = (x2PowerUp) p;
                    paux.start();
                } else if (p.isPowerUp() == 2) {
                    tntPowerUp paux = (tntPowerUp) p;
                    if (!paux.isUsed()) {
                        paux.setUsed();
                        deleteLine(linea - 1, spriteLength, interSpace);
                    }
                } else if (p.isPowerUp() == 3) {
                    slowPowerUp paux = (slowPowerUp) p;
                    paux.start();
                }
            }
        }

        drop(y, spriteSpace);

        for (int i = linea; i > 0; i--) {        //checkear por bugs mañana
            linesInfo[i] = linesInfo[i - 1];
        }
        linesInfo[0] = 0;
    }

    private void drop(int y, int spriteSpace) {
        for (TetrixPiece p : piezas) {
            CubeSprite[] cubos = p.getSprites();
            for (int i = 0; i < 4; i++) {
                if (cubos[i] != null && cubos[i].getY() < y) {
                    cubos[i].setY(cubos[i].getY() + spriteSpace);
                }
            }
        }
    }

    public void downTop() {
        top = top + cubelength * 2;
    }
    public void gameModeRefactor(CubeSprite[] cubes) throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            if (cubes[i] != null && cubes[i].getY() <= top) {
                if (gameMode == 0)
                    getSt().running = false;
                else
                    getSta().running = false;
                this.invalidate();
                sleep(1000);
                ma.changeGameOver();
                break;
            }
        }
    }
    public void gameOver() throws InterruptedException {
        for (TetrixPiece pieza : piezas) {
            CubeSprite[] cubos = pieza.getSprites();
            if ((pieza != activePiece) && (pieza != secondPiece) && (pieza != activePowerUp)) {
                gameModeRefactor(cubos);
            }
        }
    }

    public void fastFall() {
        if (gameMode == 0)
            getSt().setGameSpeed(1);
        else
            getSta().setGameSpeed(1);
    }

    public void resetFall() {
        if (gameMode == 0)
            getSt().setGameSpeed(7);
        else
            getSta().setGameSpeed(7);
    }

    private void setCubeSpriteColor(int palette) {
        ma.selectPalette(palette);
        auxSetCubeSprite(palette);
    }

    private void painting(Integer typeCube) {
        bmp = BitmapFactory.decodeResource(getResources(), typeCube);
    }

    public void auxSetCubeSprite(int palette) {
        switch (palette) {
            case 0:
            case 3:
                painting(R.drawable.cubespritey);
                break;
            case 1:
                painting(R.drawable.cubespriteb);
                break;
            case 2:
                painting(R.drawable.cubespritep);
                break;
            case 4:
                painting(R.drawable.cubespriteo);
                break;
            case 5:
                painting(R.drawable.cubespriteg);
                break;
            default:
                break;
        }
    }

    public boolean isSecondThreadRunnig() {
        if (gameMode == 0)
            return getSt().running;
        else
            return getSta().running;
    }
}

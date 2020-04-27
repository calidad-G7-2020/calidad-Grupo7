package com.example.tetris;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;
import static androidx.core.content.ContextCompat.startActivity;
import pl.droidsonroids.gif.GifImageView;


public class Juego extends View implements View.OnClickListener {

    private ImageButton botonDcha, botonBajar, botonIzda, botonRotar, snap;
    private TextView puntuacion, nivel;
    private MainActivity mainActivity;
    private Tablero tablero;
    private ArrayList<Pieza> listaPiezas;
    private Random random = new Random();
    private static int puntos = 0;
    public int nivelActual = 0;
    private int nivelvar = 1;
    private Timer timer = new Timer();
    private Timer crono = new Timer();
    private List<Integer> filasPorBorrar;
    private int timerPeriod = 1000;
    private VentanaNext ventana;
    private int contadorRomper = 0;
    private int restoContador;
    private int restoSnap = 0;
    private int puntosSnap = 100;
    private int alturaVariable;
    private int modo;
    private Pieza troll;
    private int restoPieza;
    private int chasquido = 0;
    private AudioService as;
    private AudioService newas;
    int cronometro = 0;

    public Juego(Context context, Tablero tablero, VentanaNext ventana, int modo, AudioService as) {
        super(context);
        this.mainActivity = (MainActivity) context;
        this.tablero = tablero;
        this.ventana = ventana;
        this.modo = modo;
        this.as = as;
        this.listaPiezas = tablero.getListaPiezas();
        botonRotar = mainActivity.getBotonRotar();
        botonDcha = mainActivity.getBotonDcha();
        botonBajar = mainActivity.getBotonBajar();
        botonIzda = mainActivity.getBotonIzda();
        snap = mainActivity.getSnap();
        puntuacion = mainActivity.getPuntos();
        nivel = mainActivity.getNivel();

        puntuacion.append(" 0");
        nivel.append(" 0");

        botonDcha.setOnClickListener(this);
        botonBajar.setOnClickListener(this);
        botonIzda.setOnClickListener(this);
        botonRotar.setOnClickListener(this);
        snap.setOnClickListener(this);

        Cronometro();
        if (modo == 0) {
            loopClasico();
        } else {
            gameLoop();
        }
    }

    public void Cronometro() {
        crono.schedule(new TimerTask() {
            @Override
            public void run() {
                mainActivity.runOnUiThread(new TimerTask() {

                    @Override
                    public void run() {
                     cronometro++;
                    }
                });
            }
        }, 1000, timerPeriod);

    }
    public void loopClasico() {
        ventana.runVentanaNext(listaPiezas.get(1));
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                mainActivity.runOnUiThread(new TimerTask() {

                    @Override
                    public void run() {
                        tablero.ponerPieza(tablero.getPieza());
                        if (!tablero.puedeMoverse(tablero.getPieza(), 0, 1, false) && tablero.getPieza().getAltura() == 0) {
                            timer.cancel();
                            if(newas!=null)newas.pause();
                            mainActivity.gameOver(puntos, modo);
                        } else {
                            if (tablero.puedeMoverse(tablero.getPieza(), 0, 1, false)) {
                                tablero.moverPiezas(tablero.getPieza(), 'a');
                                timer.cancel();
                                timer = new Timer();
                                loopClasico();
                            } else {
                                filasPorBorrar = tablero.detectarFilas(null);
                                tablero.borrarPieza();
                                setPuntos(filasPorBorrar.size() * 30);

                                puntuacion.setText("" + puntos);
                                setNivel();
                                nivel.setText("" + nivelvar);
                                if(getNivel() > nivelActual){
                                    nivelActual = getNivel();
                                    timerPeriod = timerPeriod - (getNivel());
                                }
                                cambiarColorLinea(filasPorBorrar.size());
                                tablero.ponerPieza(tablero.getPieza());
                                tablero.generarPieza(0);
                                ventana.runVentanaNext(listaPiezas.get(1));
                                ventana.invalidate();
                                if(puntos>=200){
                                    tablero.changeTab(tablero.tab);
                                }
                            }
                            invalidate();
                            if(cronometro % 20 == 0){
                                cambiarCancion20s();
                            }
                        }
                    }
                });
            }
        }, 1000, timerPeriod);
    }

    public void gameLoop() {
        ventana.runVentanaNext(listaPiezas.get(1));
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mainActivity.runOnUiThread(new TimerTask() {

                    @Override
                    public void run() {
                        tablero.ponerPieza(tablero.getPieza());
                        checkComerTablero();
                        tablero.comerTablero(alturaVariable);
                        if (!tablero.puedeMoverse(tablero.getPieza(), 0, 1, false) && tablero.getPieza().getAltura() - 2 <= alturaVariable) {
                            timer.cancel();
                            mainActivity.gameOver(puntos, modo);
                        } else {
                            checkContador();
                            if (tablero.puedeMoverse(tablero.getPieza(), 0, 1, false)) {
                                tablero.moverPiezas(tablero.getPieza(), 'a');
                                if ((tablero.puedeMoverse(troll, 0, 1, false))) {
                                    tablero.moverPiezas(troll, 'a');
                                } else {
                                    troll = null;
                                }
                                checkComerTablero();
                                timer.cancel();
                                timer = new Timer();
                                gameLoop();
                            } else {
                                filasPorBorrar = tablero.detectarFilas(troll);
                                tablero.borrarPieza();
                                setPuntos(filasPorBorrar.size() * 30);
                                if(puntos>puntosSnap){
                                    snap.setVisibility(View.VISIBLE);
                                    chasquido++;
                                    puntosSnap+=100;
                                }

                                puntuacion.setText("" + puntos);
                                setNivel();
                                nivel.setText("" + nivelvar);
                                if(getNivel() > nivelActual){
                                    nivelActual = getNivel();
                                    timerPeriod = timerPeriod - (getNivel());}
                                cambiarColorLinea(filasPorBorrar.size());
                                checkSiguienteCont();
                                checkComerTablero();
                                ventana.runVentanaNext(listaPiezas.get(1));
                                ventana.invalidate();
                            }
                            invalidate();
                            if(cronometro % 20 == 0){
                                cambiarCancion20s();
                            }
                        }
                    }
                });
            }
        }, 1000, timerPeriod);
    }
    public void checkContador(){
        contadorRomper++;
        restoContador = contadorRomper % 50;
        restoPieza = contadorRomper % 30;
        if (restoContador == 0) {
            alturaVariable += 2;
        }

        if (restoPieza == 0) {
            piezaTroll(alturaVariable);
        }
    }
    public void piezaTroll(int altura) {
        int n = (int) (Math.random() * 2);
        if (n == 1) {
            troll = new Pieza(10, altura);
        } else if (n == 0) {
            troll = new Pieza(9, altura);
        }
        tablero.ponerPieza(troll);
    }

    public void checkComerTablero() {
        if (restoContador == 0) {
            while ((tablero.getPieza().getAltura() < alturaVariable) && (troll.getAltura() < alturaVariable)) {
                tablero.moverPiezas(tablero.getPieza(), 'a');
                tablero.moverPiezas(troll, 'a');
            }
        }
    }

    public void cambiarCancion20s(){
        int n = (int) (Math.random() * 5);
        as.pause();
        if(newas!=null) newas.pause();
        newas = new AudioService();
        switch (n){
            case 0:
                newas.start(mainActivity,R.raw.tetrisoriginal);
                break;
            case 1:
                newas.start(mainActivity,R.raw.acdcbackinblack);
                break;
            case 2:
                newas.start(mainActivity,R.raw.inmigrant);
                break;
            case 3:
                newas.start(mainActivity,R.raw.thunderstruck);
                break;
            case 4:
                newas.start(mainActivity,R.raw.cumbiaavengers);
                break;
        }
    }

    public void checkSiguienteCont() {
        if ((contadorRomper + 1) % 10 == 0) {
            tablero.generarPieza(alturaVariable + 2);
        } else {
            tablero.generarPieza(alturaVariable+1);
        }
    }

    public void cambiarColorLinea(int i) {
        if (i != 0) {
            if (i == 1) {
                tablero.CambiarColores1Linea();
            } else {
                tablero.CambiarColoresMultiLinea();
            }
            filasPorBorrar.clear();
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Pintamos el tablero back
        Paint pincel = new Paint();

        for (int x = 0; x < tablero.getAnchoTablero(); x++) {
            for (int y = 0; y < tablero.getAlturaTablero(); y++) {

                int color = tablero.parseaColor(x, y);
                pincel.setColor(color);
                canvas.drawRect(x * getMeasuredWidth() / 10, y * getMeasuredHeight() / 20, x * getMeasuredWidth() + getMeasuredWidth() / 10,
                        y * getMeasuredHeight() + getMeasuredHeight() / 20, pincel);
            }
        }

        //Pintamos el tablero front
        Paint pBorde = new Paint();
        pBorde.setStyle(Paint.Style.STROKE);
        pBorde.setColor(Color.BLACK);
        pBorde.setStrokeWidth(2);
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 20; y++) {
                canvas.drawLine((x + 1) * getMeasuredWidth() / tablero.getAnchoTablero(), 0, (x + 1) * getMeasuredWidth() / tablero.getAnchoTablero(), getMeasuredHeight(), pBorde);
                canvas.drawLine(0, (y + 1) * getMeasuredHeight() / tablero.getAlturaTablero(), getMeasuredWidth(), (y + 1) * getMeasuredHeight() / tablero.getAlturaTablero(), pBorde);
            }
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.botonDcha:
                tablero.moverPiezas(tablero.getPieza(), 'd');
                invalidate();
                break;
            case R.id.botonBajar:
                tablero.moverPiezas(tablero.getPieza(), 'a');
                tablero.moverPiezas(troll, 'a');
                setPuntos(1);
                puntuacion.setText("" + puntos);
                invalidate();
                break;
            case R.id.botonIzda:
                tablero.moverPiezas(tablero.getPieza(), 'i');
                invalidate();
                break;
            case R.id.botonRotar:
                Pieza p = tablero.getPieza();
                if (p.idColor != 1) {
                    tablero.borrarPieza(p);
                    tablero.comprobarRotar(p);
                    tablero.ponerPieza(p);
                }
                invalidate();
                break;
            case R.id.snap:
                if(chasquido>0){
                    Toast toast = new Toast(mainActivity.getApplicationContext());
                    GifImageView view = new GifImageView(mainActivity.getApplicationContext());
                    view.setImageResource(R.drawable.thanos);
                    toast.setGravity(Gravity.FILL, 0, 0);
                    toast.setView(view);
                    toast.show();
                    tablero.limpiarTablero();
                    alturaVariable=0;
                    chasquido--;
                    if(chasquido<=0){
                        snap.setVisibility(View.GONE);
                    }
                }
        }
    }


    public AudioService getNewAS(){return newas;}

    public  void setPuntos(int nuevosPuntos) { puntos = puntos + nuevosPuntos; }

    public static int getPuntos() {
        return puntos;
    }

    public static void reiniciarPuntos() {
        puntos = 0;
    }

    public int getNivel() {
        return this.nivelvar;
    }

    public void setNivel() {

        if(puntos>=100) {
            this.nivelvar = 1;
        }

        if(puntos>=200) {
            this.nivelvar = 2;
        }

        if(puntos>=300) {
            this.nivelvar = 3;
        }

        if(puntos>=400) {
            this.nivelvar = 4;
        }

        if(puntos>=500) {
            this.nivelvar = 5;
        }

        if(puntos>=600) {
            this.nivelvar = 6;
        }

        if(puntos>=700) {
            this.nivelvar = 7;
        }

        if(puntos>=800) {
            this.nivelvar = 8;
        }

        if(puntos>=900) {
            this.nivelvar = 9;
        }

        if(puntos>=1000) {
            this.nivelvar = 10;
        }
    }
}



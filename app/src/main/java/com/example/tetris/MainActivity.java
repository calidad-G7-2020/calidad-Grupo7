package com.example.tetris;

import androidx.annotation.RawRes;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageButton botonDcha, botonBajar, botonIzda, botonRotar;
    private TextView puntosTextView, nivelTextView;
    private Activity myActivity;
    private Juego juego;
    private Tablero tablero = new Tablero();
    private Tablero ventana = new Tablero();
    private Button menu;
    private MediaPlayer mediaPlayer;
    AudioService as;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        as = new AudioService();
        as.start(this, R.raw.acdcbackinblack);

        Bundle b = this.getIntent().getExtras();
        int modo = b.getInt("MODO");

        Juego.reiniciarPuntos();

        botonDcha = (ImageButton) findViewById(R.id.botonDcha);
        botonIzda = (ImageButton) findViewById(R.id.botonIzda);
        botonBajar = (ImageButton) findViewById(R.id.botonBajar);
        botonRotar = (ImageButton) findViewById(R.id.botonRotar);
        puntosTextView = (TextView) findViewById(R.id.puntosText);
        nivelTextView = (TextView) findViewById(R.id.nivelText);
        Pieza p = new Pieza(0, 0);

        VentanaNext siguientePieza = new VentanaNext(this, ventana, p);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(400, 400);
        siguientePieza.setLayoutParams(params1);
        params1.topMargin = 50;
        RelativeLayout relativeNext = (RelativeLayout) findViewById(R.id.ventanaSig);
        relativeNext.setBackgroundColor(findViewById(R.id.layoutprincipal).getSolidColor());
        relativeNext.setHorizontalGravity(1);
        relativeNext.addView(siguientePieza);

        final Juego juego = new Juego(this, tablero, siguientePieza, modo, as);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        juego.setLayoutParams(params);
        RelativeLayout relativeTetris = (RelativeLayout) findViewById(R.id.layoutTablero);
        juego.setBackgroundColor(Color.LTGRAY);
        relativeTetris.addView(juego);

        menu = (Button)findViewById(R.id.buttonAjustes);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                as.pause();
                if(juego.getNewAS()!=null) juego.getNewAS().pause();
                Intent intent = new Intent(MainActivity.this, Inicio.class);
                startActivity(intent);
            }
        });
        Button reiniciar = (Button) findViewById(R.id.restart);
        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Juego.getPuntos()>250){
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Debes tener mas de 250 puntos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Para huir pulsa MENU", Toast.LENGTH_SHORT).show();
    }

    public ImageButton getBotonDcha() {
        return findViewById(R.id.botonDcha);
    }

    public ImageButton getBotonBajar() {
        return findViewById(R.id.botonBajar);
    }

    public ImageButton getBotonIzda() {
        return findViewById(R.id.botonIzda);
    }

    public ImageButton getBotonRotar() {
        return findViewById(R.id.botonRotar);
    }

    public ImageButton getSnap() {
        return findViewById(R.id.snap);
    }

    public TextView getPuntos() {
        return puntosTextView;
    }

    public TextView getNivel() {
        return nivelTextView;
    }

    public void gameOver(int p, int m) {
        as.pause();
        Intent intentGameOver = new Intent(this, Exit.class);
        intentGameOver.putExtra("puntuacionFinal", p);
        intentGameOver.putExtra("Modo", m);
        startActivity(intentGameOver);
    }

    public AudioService getAudio(){
        return as;
    }



}

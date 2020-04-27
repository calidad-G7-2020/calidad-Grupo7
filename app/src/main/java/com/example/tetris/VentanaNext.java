package com.example.tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class VentanaNext extends View {
    private MainActivity mainActivity;
    private Tablero ventana;

    public VentanaNext(Context context, Tablero ventana, Pieza p) {
        super(context);
        this.mainActivity = (MainActivity) context;
        this.ventana = ventana;
    }

    public void runVentanaNext(Pieza p) {
        limpiarVentana();
        Pieza aux = new Pieza(p.id, 0);
        getPieza(aux);
        ventana.ponerPieza(aux);
    }

    public void limpiarVentana() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                ventana.tab[i][j] = 0;
            }
        }
    }

    public Pieza getPieza(Pieza aux) {

        switch (aux.id) { // Cuadrado
            case 1:
                aux.x1 = 1;
                aux.y1 = 1;
                aux.x2 = 2;
                aux.y2 = 1;
                aux.x3 = 1;
                aux.y3 = 2;
                aux.x4 = 2;
                aux.y4 = 2;


                aux.idColor = Tablero.getColorCuadrado();
                aux.pos = 0;
                break;

            case 2:    // Z auxieza
                aux.x1 = 0;
                aux.y1 = 1;
                aux.x2 = 1;
                aux.y2 = 1;
                aux.x3 = 1;
                aux.y3 = 2;
                aux.x4 = 2;
                aux.y4 = 2;

                aux.idColor = Tablero.getColorZPieza();
                aux.pos = 0;
                break;

            case 3: // I auxieza
                aux.x1 = 1;
                aux.y1 = 0;
                aux.x2 = 1;
                aux.y2 = 1;
                aux.x3 = 1;
                aux.y3 = 2;
                aux.x4 = 1;
                aux.y4 = 3;

                aux.idColor = Tablero.getColorIPieza();
                aux.pos = 0;
                break;

            case 4: // T auxieza
                aux.x1 = 1;
                aux.y1 = 1;
                aux.x2 = 0;
                aux.y2 = 2;
                aux.x3 = 1;
                aux.y3 = 2;
                aux.x4 = 2;
                aux.y4 = 2;

                aux.idColor = Tablero.getColorTPieza();
                aux.pos = 0;
                break;

            case 5: // S auxieza
                aux.x1 = 1;
                aux.y1 = 1;
                aux.x2 = 2;
                aux.y2 = 1;
                aux.x3 = 0;
                aux.y3 = 2;
                aux.x4 = 1;
                aux.y4 = 2;

                aux.idColor = Tablero.getColorSPieza();
                aux.pos = 0;
                break;

            case 6:  // J auxieza

                aux.x1 = 1;
                aux.y1 = 1;
                aux.x2 = 1;
                aux.y2 = 2;
                aux.x3 = 1;
                aux.y3 = 3;
                aux.x4 = 2;
                aux.y4 = 3;

                aux.idColor = Tablero.getColorLPieza();
                aux.pos = 0;
                break;
            case 7:  // L auxieza
                aux.x1 = 2;
                aux.y1 = 1;
                aux.x2 = 2;
                aux.y2 = 2;
                aux.x3 = 1;
                aux.y3 = 3;
                aux.x4 = 2;
                aux.y4 = 3;

                aux.idColor = Tablero.getColorJPieza();
                aux.pos = 0;
                break;
        }
        return aux;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint pincel = new Paint();
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {

                int color = ventana.parseaColor(x, y);
                if (color == Color.parseColor("#001c21")) {
                    color = Color.parseColor("#423F3F");
                }
                pincel.setColor(color);
                canvas.drawRect(x * getMeasuredWidth() / 4, y * getMeasuredHeight() / 4, x * getMeasuredWidth() + getMeasuredWidth() / 4,
                        y * getMeasuredHeight() + getMeasuredHeight() / 4, pincel);
            }
        }


    }
}
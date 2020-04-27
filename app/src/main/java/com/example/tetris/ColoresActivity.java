package com.example.tetris;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ColoresActivity extends AppCompatActivity {

    private Spinner sCuadrado;
    private Spinner sZ;
    private Spinner sI;
    private Spinner sT;
    private Spinner sS;
    private Spinner sL;
    private Spinner sJ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colores);

        sCuadrado = (Spinner) findViewById(R.id.spinnerCuadrado);
        sZ = (Spinner) findViewById(R.id.spinnerZPieza);
        sI = (Spinner) findViewById(R.id.spinnerIPieza);
        sT = (Spinner) findViewById(R.id.spinnerTPieza);
        sS = (Spinner) findViewById(R.id.spinnerSPieza);
        sL = (Spinner) findViewById(R.id.spinnerLPieza);
        sJ = (Spinner) findViewById(R.id.spinnerJPieza);

        String[] opciones = {"Red", "Pink", "Light Blue", "Dark Blue", "Green", "Orange", "Yellow"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);

        sCuadrado.setAdapter(adapter);
        sZ.setAdapter(adapter);
        sI.setAdapter(adapter);
        sT.setAdapter(adapter);
        sS.setAdapter(adapter);
        sL.setAdapter(adapter);
        sJ.setAdapter(adapter);
    }

    public void SetColor(View view) {//id:4 = verde, id:6 = amarillo, id:5 = naranja, id: 2 = azul claro, id:1 = rosa, id:3 = azul oscuro, id:7 = rojo
        // Cuadrado

        String seleccionC = sCuadrado.getSelectedItem().toString();

        if (seleccionC.equals("Red")) {
            Tablero.setColorCuadrado(7);
            // codigo set cuadrado a rojo ID (integer) IDColor (integer)
        }
        if (seleccionC.equals("Pink")) {
            Tablero.setColorCuadrado(1); }
        if (seleccionC.equals("Light Blue")) {
            Tablero.setColorCuadrado(2); }
        if (seleccionC.equals("Dark Blue")) {
            Tablero.setColorCuadrado(3); }
        if (seleccionC.equals("Green")) {
            Tablero.setColorCuadrado(4); }
        if (seleccionC.equals("Orange")) {
            Tablero.setColorCuadrado(5); }
        if (seleccionC.equals("Yellow")) {
            Tablero.setColorCuadrado(6); }

        // Z Pieza
        String seleccionZ = sZ.getSelectedItem().toString();
        if (seleccionZ.equals("Red")) {
            // codigo set Z a rojo
            Tablero.setColorZPieza(7);
        }
        if (seleccionZ.equals("Pink")) {
            Tablero.setColorZPieza(1); }
        if (seleccionZ.equals("Light Blue")) {
            Tablero.setColorZPieza(2); }
        if (seleccionZ.equals("Dark Blue")) {
            Tablero.setColorZPieza(3); }
        if (seleccionZ.equals("Green")) {
            Tablero.setColorZPieza(4); }
        if (seleccionZ.equals("Orange")) {
            Tablero.setColorZPieza(5); }
        if (seleccionZ.equals("Yellow")) {
            Tablero.setColorZPieza(6); }

        // I Pieza
        String seleccionI = sI.getSelectedItem().toString();
        if (seleccionI.equals("Red")) {
            // codigo set I a rojo
            Tablero.setColorIPieza(7);
        }
        if (seleccionI.equals("Pink")) {
            Tablero.setColorIPieza(1); }
        if (seleccionI.equals("Light Blue")) {
            Tablero.setColorIPieza(2); }
        if (seleccionI.equals("Dark Blue")) {
            Tablero.setColorIPieza(3); }
        if (seleccionI.equals("Green")) {
            Tablero.setColorIPieza(4); }
        if (seleccionI.equals("Orange")) {
            Tablero.setColorIPieza(5); }
        if (seleccionI.equals("Yellow")) {
            Tablero.setColorIPieza(6); }

        // T Pieza
        String seleccionT = sT.getSelectedItem().toString();
        if (seleccionT.equals("Red")) {
            // codigo set T a rojo
            Tablero.setColorTPieza(7);
        }
        if (seleccionT.equals("Pink")) {
            Tablero.setColorTPieza(1); }
        if (seleccionT.equals("Light Blue")) {
            Tablero.setColorTPieza(2); }
        if (seleccionT.equals("Dark Blue")) {
            Tablero.setColorTPieza(3); }
        if (seleccionT.equals("Green")) {
            Tablero.setColorTPieza(4); }
        if (seleccionT.equals("Orange")) {
            Tablero.setColorTPieza(5); }
        if (seleccionT.equals("Yellow")) {
            Tablero.setColorTPieza(6); }

        // S Pieza
        String seleccionS = sS.getSelectedItem().toString();
        if (seleccionS.equals("Red")) {
            // codigo set S a rojo
            Tablero.setColorSPieza(7);
        }
        if (seleccionS.equals("Pink")) {
            Tablero.setColorSPieza(1); }
        if (seleccionS.equals("Light Blue")) {
            Tablero.setColorSPieza(2); }
        if (seleccionS.equals("Dark Blue")) {
            Tablero.setColorSPieza(3); }
        if (seleccionS.equals("Green")) {
            Tablero.setColorSPieza(4); }
        if (seleccionS.equals("Orange")) {
            Tablero.setColorSPieza(5); }
        if (seleccionS.equals("Yellow")) {
            Tablero.setColorSPieza(6); }

        // L Pieza
        String seleccionL = sL.getSelectedItem().toString();
        if (seleccionL.equals("Red")) {
            // codigo set L a rojo
            Tablero.setColorLPieza(7);
        }
        if (seleccionL.equals("Pink")) {
            Tablero.setColorLPieza(1); }
        if (seleccionL.equals("Light Blue")) {
            Tablero.setColorLPieza(2); }
        if (seleccionL.equals("Dark Blue")) {
            Tablero.setColorLPieza(3); }
        if (seleccionL.equals("Green")) {
            Tablero.setColorLPieza(4); }
        if (seleccionL.equals("Orange")) {
            Tablero.setColorLPieza(5); }
        if (seleccionL.equals("Yellow")) {
            Tablero.setColorLPieza(6); }

        // J Pieza
        String seleccionJ = sJ.getSelectedItem().toString();
        if (seleccionJ.equals("Red")) {
            // codigo set cuadrado a rojo
            Tablero.setColorJPieza(7);
        }
        if (seleccionJ.equals("Pink")) {
            Tablero.setColorJPieza(1); }
        if (seleccionJ.equals("Light Blue")) {
            Tablero.setColorJPieza(2); }
        if (seleccionJ.equals("Dark Blue")) {
            Tablero.setColorJPieza(3); }
        if (seleccionJ.equals("Green")) {
            Tablero.setColorJPieza(4); }
        if (seleccionJ.equals("Orange")) {
            Tablero.setColorJPieza(5); }
        if (seleccionJ.equals("Yellow")) {
            Tablero.setColorJPieza(6); }


    }

}

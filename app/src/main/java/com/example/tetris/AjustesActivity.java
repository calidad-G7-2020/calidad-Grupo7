package com.example.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AjustesActivity extends AppCompatActivity {

    Button colores;
    Button instrucciones;
    Button creditos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);


        colores = (Button)findViewById(R.id.button_colores);
        colores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(AjustesActivity.this, ColoresActivity.class);
                startActivity(intent1);
            }
        });

        instrucciones = (Button)findViewById(R.id.button_instrucciones);
        instrucciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(AjustesActivity.this,InstruccionesActivity.class);
                startActivity(intent2);
            }
        });

        creditos = (Button)findViewById(R.id.button_creditos);
        creditos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(AjustesActivity.this, CreditosActivity.class);
                startActivity(intent3);
            }
        });
    }
}

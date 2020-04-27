package com.example.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Inicio extends AppCompatActivity {

    Button modoClasico;
    Button muerteSubita;
    ImageView settings;
    AudioService asIni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        asIni = new AudioService();

        asIni.start(this,R.raw.tetrisoriginal);


        modoClasico = (Button)findViewById(R.id.clasico);
        modoClasico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Inicio.this,MainActivity.class);
                intent.putExtra("MODO",0);

                startActivity(intent);
                asIni.pause();
            }
        });

        muerteSubita = (Button) findViewById(R.id.subita);
        muerteSubita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Inicio.this,MainActivity.class);
                intent2.putExtra("MODO", 1);
                startActivity(intent2);
                asIni.pause();
            }
        });

        settings = (ImageView)findViewById(R.id.buttonAjustes);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(Inicio.this,AjustesActivity.class);
                startActivity(intent3);
            }
        });


    }
    @Override
    public void onBackPressed() {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        asIni.pause();
        startActivity(homeIntent);
    }
}

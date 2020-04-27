package com.example.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;

public class Rankings extends AppCompatActivity {
    private TextView textoRanking1,textoRanking2,textoRanking3,textoRanking4,textoRanking5,textoRanking6,mostrarPunt1,mostrarPunt2,mostrarPunt3,mostrarPunt4,mostrarPunt5,mostrarPunt6;
    private TextView num_1,num_2,num_3,num_4,num_5,num_6;
    private ImageView imageAvatar1,imageAvatar2,imageAvatar3,imageAvatar4,imageAvatar5,imageAvatar6;
    private SQLiteDatabase BaseDeDatos;
    private AdminSQLiteOpenHelper BBDD;
    private int modo;
    private String tipoBBDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle p = this.getIntent().getExtras();
        modo = p.getInt("modo");
        BBDD = new AdminSQLiteOpenHelper(this, "RankingJugadores", null, 1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rankings);

        Button restEstadisticas =  findViewById(R.id.restablecerEstadisticas);
        Button menu= findViewById(R.id.goMenu);

        num_1 = findViewById(R.id.primero);
        num_2 = findViewById(R.id.segundo);
        num_3 = findViewById(R.id.tercero);
        num_4 = findViewById(R.id.cuarto);
        num_5 = findViewById(R.id.quinto);
        num_6 = findViewById(R.id.sexto);

        textoRanking1 = findViewById(R.id.mostrarRanking1);
        textoRanking2 = findViewById(R.id.mostrarRanking2);
        textoRanking3 = findViewById(R.id.mostrarRanking3);
        textoRanking4 = findViewById(R.id.mostrarRanking4);
        textoRanking5 = findViewById(R.id.mostrarRanking5);
        textoRanking6 = findViewById(R.id.mostrarRanking6);

        mostrarPunt1= findViewById(R.id.textpunt1);
        mostrarPunt2= findViewById(R.id.textpunt2);
        mostrarPunt3= findViewById(R.id.textpunt3);
        mostrarPunt4= findViewById(R.id.textpunt4);
        mostrarPunt5= findViewById(R.id.textpunt5);
        mostrarPunt6= findViewById(R.id.textpunt6);

        imageAvatar1 = findViewById(R.id.imageJ1);
        imageAvatar2 = findViewById(R.id.imageJ2);
        imageAvatar3 = findViewById(R.id.imageJ3);
        imageAvatar4 = findViewById(R.id.imageJ4);
        imageAvatar5 = findViewById(R.id.imageJ5);
        imageAvatar6 = findViewById(R.id.imageJ6);

        mostrarTop5();

        restEstadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restablecerEstadiaticas(v);
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMenu = new Intent(Rankings.this, Inicio.class);
                startActivity(intentMenu);
            }
        });

    }
    public void mostrarTop5 (){
        BaseDeDatos = BBDD.getWritableDatabase();
        String columnas[] = new String[]{"nombre","puntuacion","foto"};//,"puntuacion"
        String j1="",j2="",j3="",j4="",j5="",j6="",p1="",p2="",p3="",p4="",p5="",p6="";
        Bitmap bitmap1 = null,bitmap2 = null,bitmap3 = null,bitmap4 = null,bitmap5 = null,bitmap6 = null;

        //********************** AMBAS FUNCIONAN
        //-----1 forma
        if(modo==0){
            tipoBBDD = "rankingNormal";
        }else{
            tipoBBDD = "rankingHard";
        }
        Cursor fila1 = BaseDeDatos.rawQuery("select * from "+tipoBBDD+"  order by puntuacion DESC",null);

        //-----2 forma
        //Cursor fila2 = BaseDeDatos.query("rankingNormal", columnas, null, null, null, null, "puntuacion"+" DESC");
        if(fila1.moveToFirst()){
            //mostrarRanking.setText(fila1.getString(0)+fila1.getString(1));

            j1 = fila1.getString(0);
            p1 = fila1.getString(1);
            byte[] blob1 = fila1.getBlob(2);
            if(blob1!=null) {
                ByteArrayInputStream bais1 = new ByteArrayInputStream(blob1);
                bitmap1 = BitmapFactory.decodeStream(bais1);
            }
            imageAvatar1.setVisibility(View.VISIBLE);
            num_1.setVisibility(View.VISIBLE);
            if (fila1.moveToNext()) {
                j2 = fila1.getString(0);
                p2 = fila1.getString(1);
                byte[] blob2 = fila1.getBlob(2);
                if(blob2!=null) {
                    ByteArrayInputStream bais2 = new ByteArrayInputStream(blob2);
                    bitmap2 = BitmapFactory.decodeStream(bais2);
                }
                imageAvatar2.setVisibility(View.VISIBLE);
                num_2.setVisibility(View.VISIBLE);
            }
            if (fila1.moveToNext()) {
                j3 = fila1.getString(0);
                p3 = fila1.getString(1);
                byte[] blob3 = fila1.getBlob(2);
                if(blob3!=null) {
                    ByteArrayInputStream bais3 = new ByteArrayInputStream(blob3);
                    bitmap3 = BitmapFactory.decodeStream(bais3);
                }
                imageAvatar3.setVisibility(View.VISIBLE);
                num_3.setVisibility(View.VISIBLE);
            }
            if (fila1.moveToNext()) {
                j4 = fila1.getString(0);
                p4 = fila1.getString(1);
                byte[] blob4 = fila1.getBlob(2);
                if(blob4!=null) {
                    ByteArrayInputStream bais4 = new ByteArrayInputStream(blob4);
                    bitmap4 = BitmapFactory.decodeStream(bais4);
                }
                imageAvatar4.setVisibility(View.VISIBLE);
                num_4.setVisibility(View.VISIBLE);
            }
            if (fila1.moveToNext()) {
                j5 = fila1.getString(0);
                p5 = fila1.getString(1);
                byte[] blob5 = fila1.getBlob(2);
                if(blob5!=null) {
                    ByteArrayInputStream bais5 = new ByteArrayInputStream(blob5);
                    bitmap5 = BitmapFactory.decodeStream(bais5);
                }
                imageAvatar5.setVisibility(View.VISIBLE);
                num_5.setVisibility(View.VISIBLE);
            }
            if (fila1.moveToNext()) {
                j6 = fila1.getString(0);
                p6= fila1.getString(1);
                byte[] blob6 = fila1.getBlob(2);
                if(blob6!=null) {
                    ByteArrayInputStream bais6 = new ByteArrayInputStream(blob6);
                    bitmap6 = BitmapFactory.decodeStream(bais6);
                }
                imageAvatar6.setVisibility(View.VISIBLE);
                num_6.setVisibility(View.VISIBLE);
            }


        }else {

            j1="";
            j2="";
            j3="";
            j4="";
            j5="";
            j6="";
            p1="";
            p2="";
            p3="";
            p4="";
            p5="";
            p6="";
        }

        textoRanking1.setText(j1);
        textoRanking2.setText(j2);
        textoRanking3.setText(j3);
        textoRanking4.setText(j4);
        textoRanking5.setText(j5);
        textoRanking6.setText(j6);

        mostrarPunt1.setText(p1);
        mostrarPunt2.setText(p2);
        mostrarPunt3.setText(p3);
        mostrarPunt4.setText(p4);
        mostrarPunt5.setText(p5);
        mostrarPunt6.setText(p6);

        if(bitmap1!=null)
        imageAvatar1.setImageBitmap(bitmap1);
        if(bitmap2!=null)
        imageAvatar2.setImageBitmap(bitmap2);
        if(bitmap3!=null)
        imageAvatar3.setImageBitmap(bitmap3);
        if(bitmap4!=null)
        imageAvatar4.setImageBitmap(bitmap4);
        if(bitmap5!=null)
        imageAvatar5.setImageBitmap(bitmap5);
        if(bitmap6!=null)
        imageAvatar6.setImageBitmap(bitmap6);

        BaseDeDatos.close();
    }

    public void restablecerEstadiaticas (View view){
        BaseDeDatos = BBDD.getWritableDatabase();

        if(modo==0){
            tipoBBDD = "rankingNormal";
        }else{
            tipoBBDD = "rankingHard";
        }
        BaseDeDatos.execSQL("DELETE FROM "+tipoBBDD);
        Toast.makeText(this, "Estadisticas restablecidas", Toast.LENGTH_SHORT).show();
        BaseDeDatos.close();

        imageAvatar1.setVisibility(View.INVISIBLE);
        num_1.setVisibility(View.INVISIBLE);
        imageAvatar2.setVisibility(View.INVISIBLE);
        num_2.setVisibility(View.INVISIBLE);
        imageAvatar3.setVisibility(View.INVISIBLE);
        num_3.setVisibility(View.INVISIBLE);
        imageAvatar4.setVisibility(View.INVISIBLE);
        num_4.setVisibility(View.INVISIBLE);
        imageAvatar5.setVisibility(View.INVISIBLE);
        num_5.setVisibility(View.INVISIBLE);
        imageAvatar6.setVisibility(View.INVISIBLE);
        num_6.setVisibility(View.INVISIBLE);


        mostrarTop5();
    }

}

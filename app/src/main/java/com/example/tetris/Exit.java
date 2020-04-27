package com.example.tetris;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Exit extends AppCompatActivity {
    private EditText et_nombre;
    //private Button registrar;

    private SQLiteDatabase BaseDeDatos;
    private AdminSQLiteOpenHelper BBDD;
    private boolean registrado;
    private int puntosFinal;
    private int modo;
    private String tipoBBDD;
    private TextView textPuntActual;
    private ImageView img;
    private Bitmap imageBitmap;
    private byte[] blob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle p = this.getIntent().getExtras();
        puntosFinal = p.getInt("puntuacionFinal");
        modo = p.getInt("Modo");

        BBDD = new AdminSQLiteOpenHelper(this, "RankingJugadores", null, 1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);
        Button again = findViewById(R.id.Again);
        Button registrar = findViewById(R.id.registrar_puntuacion);
        Button rankings = findViewById(R.id.ShowRankings);

        //TextView nombre = findViewById(R.id.nombre_jugador);
        registrado=false;

        et_nombre = (EditText)findViewById(R.id.nombre_jugador);
        textPuntActual= findViewById(R.id.text_puntuacionActual);
        textPuntActual.setText(Integer.toString(puntosFinal));

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fin();
                Intent intent = new Intent(Exit.this, Inicio.class);
                startActivity(intent);

            }
        });
        rankings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRankings = new Intent(Exit.this, Rankings.class);
                intentRankings.putExtra("modo",modo);
                startActivity(intentRankings);
            }
        });
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Registrar(view);
            }
        });

        img = (ImageView)findViewById(R.id. NewPhoto);

        if (ContextCompat.checkSelfPermission(Exit.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Exit.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Exit.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
        }

    }
    void fin(){
        this.finish();
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Para volver a jugar pulsa MENU", Toast.LENGTH_SHORT).show();
    }
    public void Registrar(View view){
        //abrir la base de datos modo escritura y lectura
        BaseDeDatos = BBDD.getWritableDatabase();
        et_nombre = (EditText)findViewById(R.id.nombre_jugador);
        String nombre = et_nombre.getText().toString();
        if(imageBitmap!=null) {
            guardarImagen(imageBitmap);
        }


        if (!nombre.isEmpty() & !registrado){
            //permite almacenar las columnas del registro en pares clave-valor
            ContentValues registro = new ContentValues();
            //Añade los pares
            registro.put("nombre", nombre);
            registro.put("puntuacion", puntosFinal);
            if(blob!=null) {
                registro.put("foto", blob);
            }


                //insertar valores en la tabla ranking
                if(modo==0){
                    BaseDeDatos.insert("rankingNormal", null, registro);
                }else{
                    BaseDeDatos.insert("rankingHard", null, registro);
            }
            BaseDeDatos.close();

            et_nombre.setText("");
            blob = null;
            registrado=true;


        }else{
            if (nombre.isEmpty()) {
                Toast.makeText(this, "Debes rellenar el nombre", Toast.LENGTH_SHORT).show();
            }
            if (registrado){
                Toast.makeText(this, "Ya te has registrado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    String currentPhotoPath;
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "Backup_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    static final int REQUEST_TAKE_PHOTO = 1;
    public void tomarFoto(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI.toString());
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            img.setImageBitmap(imageBitmap);
        }
    }
    public void guardarImagen(Bitmap bitmap){
        // tamaño del baos depende del tamaño de tus imagenes en promedio
        ByteArrayOutputStream baos = new ByteArrayOutputStream(20480);
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 , baos);
        blob = baos.toByteArray();
        // aqui tenemos el byte[] con el imagen comprimido, ahora lo guardemos en SQLite

    }
}
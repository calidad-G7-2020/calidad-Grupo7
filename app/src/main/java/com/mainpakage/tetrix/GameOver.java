package com.mainpakage.tetrix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class GameOver extends AppCompatActivity {
    private EditText pn;
    private Button ok;
    private List<PlayerData> ranking;
    private TextView playerName, gameOverText, rankingText,scoreText;
    private ListView listRanking;
    private ImageView picCam;
    private Bundle bAux;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private List<String> adaptedArray;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        bAux = getIntent().getExtras();
        adaptedArray = new ArrayList<>();
        scoreText = (TextView) findViewById(R.id.Score);
        scoreText.setText("Score: "+ bAux.getString("Score"));
        playerName = (TextView) findViewById(R.id.playerName);
        picCam = (ImageView) findViewById(R.id.picCam);
        listRanking = (ListView) findViewById(R.id.listRanking);
        gameOverText = (TextView) findViewById(R.id.gameOver);
        rankingText = (TextView) findViewById(R.id.rankingText);

        int gameMode = bAux.getInt("GameMode");
        if (gameMode == 0)
            preferences = getSharedPreferences("RankingDefinitive", Context.MODE_PRIVATE);
        else
            preferences = getSharedPreferences("RankingPowerDefinitive", Context.MODE_PRIVATE);
        Set<String> scores = preferences.getStringSet("Scores", null);
        editor = preferences.edit();
        ranking = new ArrayList<>();
        if (scores == null) {
            scores = new HashSet<>();
        }
        if (!scores.isEmpty()) {
            for (String s : scores) {
                String[] aux = s.split("/");
                PlayerData pd = new PlayerData(aux[0], Integer.parseInt(aux[1]));
                ranking.add(pd);
            }
        }

        if(gameMode != -1) {
            dispatchTakePictureIntent();
            pn = (EditText) findViewById(R.id.playerName);
        }else{
            viewRanking(scores);
        }
    }

    public void onClickOk(View v) {
        if(pn.getText().toString().contains("/")){
            Toast toast1 = Toast.makeText(getApplicationContext(), "No se puede introducir / en el nombre", Toast.LENGTH_SHORT);
            toast1.setGravity(Gravity.CENTER,0,0);
            toast1.show();
        }else {
            PlayerData pd = new PlayerData(pn.getText().toString(), Integer.parseInt(bAux.getString("Score")));
            ranking.add(pd);
            Collections.sort(ranking);
            Set<String> scores = new HashSet<>();
            for (PlayerData p : ranking) {
                scores.add(p.toString());
            }

            editor.putStringSet("Scores", scores);
            editor.commit();
            adaptedArray.clear();
            for (int r = 0; (r < ranking.size()) && (r < 10); r++)
                adaptedArray.add(ranking.get(r).toFormatString());

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, adaptedArray);
            listRanking.setAdapter(arrayAdapter);

            gameOverText.setVisibility(View.INVISIBLE);
            playerName.setVisibility(View.INVISIBLE);
            picCam.setVisibility(View.INVISIBLE);
            v.setVisibility(View.INVISIBLE);
            scoreText.setVisibility(View.INVISIBLE);
            rankingText.setVisibility(View.VISIBLE);
            listRanking.setVisibility(View.VISIBLE);
        }
    }

    public void onClickOkReturn(View v){
        Intent intent = new Intent (v.getContext(), StartMenu.class);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onBackPressed(){

    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView picCam=(ImageView)findViewById(R.id.picCam);
            picCam.setImageBitmap(imageBitmap);
        }
    }

    private void viewRanking(Set<String> scores){

        final Button butCl = (Button) findViewById(R.id.setName);
        butCl.setVisibility(View.GONE);
        gameOverText.setVisibility(View.GONE);
        playerName.setVisibility(View.GONE);
        picCam.setVisibility(View.GONE);
        scoreText.setVisibility(View.GONE);
        rankingText.setVisibility(View.VISIBLE);
        listRanking.setVisibility(View.VISIBLE);

        Collections.sort(ranking);

        for (PlayerData p : ranking) {
            scores.add(p.toString());
        }
        editor.putStringSet("Scores", scores);
        editor.commit();
        adaptedArray.clear();
        for (int r = 0; (r < ranking.size()) && (r < 10); r++)
            adaptedArray.add(ranking.get(r).toFormatString());

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, adaptedArray);
        listRanking.setAdapter(arrayAdapter);

    }

}

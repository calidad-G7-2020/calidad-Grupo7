package com.mainpakage.Tetrix;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StartMenu extends AppCompatActivity {
    int thm;
    int gameMode;
    boolean themeSelected;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
        final Button butCl = (Button) findViewById(R.id.butClassic);
        final Button butSp = (Button) findViewById(R.id.butSpooky);
        final Button butTut = (Button) findViewById(R.id.butTutorial);

        butCl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) { //mantengo pulsado
                    if(!themeSelected){
                        butCl.setBackgroundResource(R.drawable.classicpressed);
                    }
                    else{
                        butCl.setBackgroundResource(R.drawable.challenge);
                    }

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {    //suelto
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    if(!themeSelected) {
                        thm = 0;
                        themeSelected=true;
                        butCl.setBackgroundResource(R.drawable.challenge);
                        butSp.setBackgroundResource(R.drawable.train);

                    }else{
                        gameMode=1;
                        intent.putExtra("theme", thm);
                        intent.putExtra("GameMode", gameMode);
                        startActivityForResult(intent, 0);
                    }
                    return true;
                }
                return false;
            }

            /*@Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), MainActivity.class);
                thm=0;
                intent.putExtra("theme", thm);
                startActivityForResult(intent, 0);
            }*/
        });

        butSp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(!themeSelected){
                        butSp.setBackgroundResource(R.drawable.choosespookypressed);
                    }
                    else{
                        butSp.setBackgroundResource(R.drawable.train);
                    }
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    if(!themeSelected){
                        butSp.setBackgroundResource(R.drawable.choosespooky);
                        thm = 1;
                        themeSelected=true;
                        butCl.setBackgroundResource(R.drawable.challenge);
                        butSp.setBackgroundResource(R.drawable.train);
                    }
                    else{
                        gameMode=0;
                        intent.putExtra("theme", thm);
                        intent.putExtra("GameMode", gameMode);
                        startActivityForResult(intent, 0);
                    }


                    return true;
                }
                return false;
            }
        });

        butTut.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    butTut.setBackgroundResource(R.drawable.tutorialpressed);
                    return true;
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    Intent intent = new Intent (v.getContext(), Tutorial.class);
                    startActivityForResult(intent, 0);
                    butTut.setBackgroundResource(R.drawable.tutorial);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed(){

    }

}
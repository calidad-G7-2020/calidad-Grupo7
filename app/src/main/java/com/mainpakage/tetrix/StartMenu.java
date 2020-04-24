package com.mainpakage.tetrix;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class StartMenu extends AppCompatActivity {
    int thm;
    int gameMode;
    boolean themeSelected;

    private List<String> backGroundList;
    private int backGroundNumber = 0;
    private ImageView backGroundShow;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
        final Button butCl = (Button) findViewById(R.id.butClassic);
        final Button butSp = (Button) findViewById(R.id.butSpooky);
        final Button butTut = (Button) findViewById(R.id.butTutorial);

        this.backGroundNumber =  getIntent().getIntExtra("paleta",0);

        this.backGroundShow = findViewById(R.id.colorView);
        this.backGroundShow.setImageResource(R.drawable.bgcl6);

        this.iniciarbackGroundList();

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
                        themeSelected=true;
                        butCl.setBackgroundResource(R.drawable.challenge);
                        butSp.setBackgroundResource(R.drawable.train);

                    }else{
                        gameMode=1;
                        thm = backGroundNumber;
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
                        themeSelected=true;
                        butCl.setBackgroundResource(R.drawable.challenge);
                        butSp.setBackgroundResource(R.drawable.train);
                    }
                    else{
                        gameMode=0;
                        thm=(backGroundNumber==0)?1:backGroundNumber;
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
    
    private void iniciarbackGroundList(){
        this.backGroundList = new ArrayList<>();
        this.backGroundList.add("0");
        this.backGroundList.add("1");
        this.backGroundList.add("2");
        this.backGroundList.add("3");
        this.backGroundList.add("4");

    }

    public void cambiarImagen(){
        switch (this.backGroundNumber){
            case 0:
                this.backGroundShow.setImageResource(R.drawable.bgcl6);
                break;
            case 1:
                this.backGroundShow.setImageResource(R.drawable.bgsp0);
                break;
            case 2:
                this.backGroundShow.setImageResource(R.drawable.bgcl61);
                break;
            case 3:
                this.backGroundShow.setImageResource(R.drawable.bgcl62);
                break;
            case 4:
                this.backGroundShow.setImageResource(R.drawable.bgcl63);
                break;
            default:
                this.backGroundShow.setImageResource(R.drawable.bgcl6);
        }
    }

    public void previous(View view){
        if(this.backGroundNumber>0){
            this.backGroundNumber--;
        }else{
            this.backGroundNumber = this.backGroundList.size();
        }

        this.cambiarImagen();
    }

    public void next(View view){
        if(this.backGroundNumber < backGroundList.size()){
            this.backGroundNumber++;
        }else{
            this.backGroundNumber = 0;
        }
        this.cambiarImagen();
    }

    @Override
    public void onBackPressed(){

    }

}
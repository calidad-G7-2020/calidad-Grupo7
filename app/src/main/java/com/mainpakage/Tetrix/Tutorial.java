package com.mainpakage.Tetrix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Tutorial extends AppCompatActivity {

    int mensaje = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        final TextView texto = (TextView) findViewById(R.id.textoBienvenida);
        final ImageButton estrella = (ImageButton) findViewById(R.id.estrella);
        estrella.setBackgroundResource(R.drawable.advancebut);
        final ImageView xScore = (ImageView) findViewById(R.id.xScore);
        final ImageView xNext = (ImageView) findViewById(R.id.xNext);
        final ImageView xSwitch = (ImageView) findViewById(R.id.xSwitch);
        final ImageView xRotate = (ImageView) findViewById(R.id.xRotate);
        final ImageView xLeft= (ImageView) findViewById(R.id.xLeft);
        final ImageView xRight = (ImageView) findViewById(R.id.xRight);
        final ImageView xDown = (ImageView) findViewById(R.id.xDown);
        final ImageView xPower = (ImageView) findViewById(R.id.xPowers);
        final ImageView cubo1 = (ImageView) findViewById(R.id.cubo1);
        final ImageView cubo2 = (ImageView) findViewById(R.id.cubo2);
        final ImageView cubo3 = (ImageView) findViewById(R.id.cubo3);


        //Escondemos todo lo que no queremos que aparezca
        xScore.setVisibility(View.INVISIBLE);
        xNext.setVisibility(View.INVISIBLE);
        xSwitch.setVisibility(View.INVISIBLE);
        xRotate.setVisibility(View.INVISIBLE);
        xLeft.setVisibility(View.INVISIBLE);
        xRight.setVisibility(View.INVISIBLE);
        xDown.setVisibility(View.INVISIBLE);
        xPower.setVisibility(View.INVISIBLE);
        cubo1.setVisibility((View.INVISIBLE));
        cubo2.setVisibility((View.INVISIBLE));
        cubo3.setVisibility((View.INVISIBLE));




        estrella.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    estrella.setBackgroundResource(R.drawable.advancepressed);

                    return true;
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    estrella.setBackgroundResource(R.drawable.advancebut);

                    switch (mensaje) {
                        case 1: {
                            texto.setText("You can see your current Score. It increases 30 points each time you make a line. Advice: use Power-Ups");
                            xScore.setVisibility(View.VISIBLE);
                        }
                        break;
                        case 2: {
                            texto.setText("Here, you can see the next Piece");
                            xScore.setVisibility(View.INVISIBLE);
                            xNext.setVisibility(View.VISIBLE);

                        }
                        break;
                        case 3: {
                            texto.setText("Pressing these buttons the pieces will move towards the chosen direction");
                            xNext.setVisibility(View.INVISIBLE);
                            xLeft.setVisibility(View.VISIBLE);
                            xRight.setVisibility(View.VISIBLE);
                        }
                        break;
                        case 4: {
                            texto.setText("Press to turn the piece");
                            xLeft.setVisibility(View.INVISIBLE);
                            xRight.setVisibility(View.INVISIBLE);
                            xRotate.setVisibility(View.VISIBLE);
                        }
                        break;
                        case 5: {
                            texto.setText("Fall Faster!");
                            xRotate.setVisibility(View.INVISIBLE);
                            xDown.setVisibility(View.VISIBLE);
                        }
                        break;
                        case 6: {
                            texto.setText("Each 30 seconds a new piece will appear randomly while having the other one still on the air.\n" + "Press to switch between pieces.");
                            xDown.setVisibility(View.INVISIBLE);
                            xSwitch.setVisibility(View.VISIBLE);
                        }
                        break;
                        case 7:{
                            xSwitch.setVisibility(View.INVISIBLE);
                            texto.setText("In challenge mode, Power-Ups will appear randomly");
                            xPower.setVisibility(View.VISIBLE);
                            cubo1.setVisibility((View.VISIBLE));
                            cubo2.setVisibility((View.VISIBLE));
                            cubo3.setVisibility((View.VISIBLE));

                        }
                        break;
                        case 8: {
                            xPower.setVisibility(View.INVISIBLE);
                            cubo1.setVisibility((View.INVISIBLE));
                            cubo2.setVisibility((View.INVISIBLE));
                            cubo3.setVisibility((View.INVISIBLE));
                            texto.setText("All this knowledge...\n" + "Use it wisely");
                        }
                        break;
                        default:{
                            Intent intent = new Intent (v.getContext(), StartMenu.class);
                            startActivityForResult(intent, 0);
                        }
                        break;
                    }
                    mensaje++;
                    return true;
                }
                return false;
            }
        });





    }
}


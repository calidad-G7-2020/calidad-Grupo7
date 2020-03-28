package com.mainpakage.tetrix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class QuickReboot extends AppCompatActivity {
    private Bundle bAux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_reboot);
        bAux = getIntent().getExtras();

        final Button yesBut = (Button) findViewById(R.id.yesBut);
        final ImageButton noBut = (ImageButton) findViewById(R.id.noBut);
        yesBut.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    //yesBut.setBackgroundResource(R.drawable.classicpressed);
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //yesBut.setBackgroundResource(R.drawable.classic);
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    intent.putExtra("theme",bAux.getInt("theme"));
                    //intent.putExtra("theme", thm);

                    startActivityForResult(intent, 0);
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

        noBut.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    //noBut.setBackgroundResource(R.drawable.choosespookypressed);
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    Intent intent = new Intent (v.getContext(), GameOver.class);
                    intent.putExtra("Score", bAux.getString("Score"));
                    intent.putExtra("GameMode",bAux.getInt("GameMode"));
                    startActivityForResult(intent, 0);
                    return true;
                }
                return false;
            }
        });
    }
}


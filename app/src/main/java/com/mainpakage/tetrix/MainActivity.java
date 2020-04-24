package com.mainpakage.tetrix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    TextView sc;
    CustomView customView;
    ImageView iv;
    MediaPlayer tetrixMusic;
    Bitmap  bmpPiece;
    Bitmap  bmpPiece1;
    Bitmap  bmpPiece2;
    Bitmap  bmpPiece3;
    Bitmap  bmpPiece4;
    Bitmap  bmpPiece5;
    Bitmap  bmpPiece6;
    int thm;
    int palette;
    int gameMode;
    Random randNum  = new Random();
    static final String THEME ="theme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bAux = getIntent().getExtras();
        gameMode = bAux.getInt("GameMode");
        palette=(randNum.nextInt()); //  //For each theme there are 3 models of pieces
        thm=bAux.getInt(THEME);

        //Music initializing
        tetrixMusic = MediaPlayer.create(this,R.raw.remix);
        tetrixMusic.start();
        tetrixMusic.setLooping(true);


        ConstraintLayout back=(ConstraintLayout)findViewById(R.id.layout);      //set background
        ImageView scoreBack=(ImageView)findViewById(R.id.scorebackground);
        ImageView nP=(ImageView)findViewById(R.id.npbackground);

        /*Typeface golden=Typeface.createFromAsset(getAssets(), "goldenhills.ttf");
        numScore.setTypeface(golden);*/

        final ImageButton turn = (ImageButton)findViewById(R.id.girar);     //Set Button turn
        final Button right=(Button)findViewById(R.id.flechader);          //Set button right
        final Button left=(Button)findViewById(R.id.flechaizq);          //Set button left
        final Button down=(Button)findViewById(R.id.flechabajo);          //Set button down
        final Button swi=(Button)findViewById(R.id.Switch);          //Set button switch

        if(thm==0){ //Classic Theme

            selectPalette(palette); //[0-2 (classic), 3-5 (spooky)]
            back.setBackgroundResource(R.drawable.bgcl6);
            scoreBack.setImageResource(R.drawable.scorecl);
            nP.setImageResource(R.drawable.nextcl);
        }

        else if(thm==1){    //Spooky theme
            palette+=3;
            selectPalette(palette);   //[0-2 (classic), 3-5 (spooky)]
            back.setBackgroundResource(R.drawable.bgsp0);
            scoreBack.setImageResource(R.drawable.scoresp);
            nP.setImageResource(R.drawable.nextsp);
        }

        else if(thm==2){    //Red theme
            selectPalette(palette); //[0-2 (classic), 3-5 (spooky)]
            back.setBackgroundResource(R.drawable.bgcl61);
            scoreBack.setImageResource(R.drawable.scorecl);
            nP.setImageResource(R.drawable.nextcl);
        }

        else if(thm==3){    //Blue theme
            selectPalette(palette); //[0-2 (classic), 3-5 (spooky)]
            back.setBackgroundResource(R.drawable.bgcl62);
            scoreBack.setImageResource(R.drawable.scorecl);
            nP.setImageResource(R.drawable.nextcl);
        }

        else if(thm==4){    //Brown theme
            selectPalette(palette); //[0-2 (classic), 3-5 (spooky)]
            back.setBackgroundResource(R.drawable.bgcl63);
            scoreBack.setImageResource(R.drawable.scorecl);
            nP.setImageResource(R.drawable.nextcl);
        }

        final int rotate;
        if(thm==0){rotate=R.drawable.rotateclassic;}
        else{rotate=R.drawable.rotatespoky;}
        final int rotatePres;
        if(thm==0){rotatePres=R.drawable.rotatepresclassic;}
        else{rotatePres=R.drawable.rotatepresspooky;}
        turn.setBackgroundResource(rotate);    //change on XML
        turn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    turn.setBackgroundResource(rotatePres);

                    return true;
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    customView.moverIzqDerGirar(customView.getActivePiece(),3);
                    turn.setBackgroundResource(rotate);

                    return true;
                }
                return false;
            }
        });

        final int r;
        if(thm==0){r=R.drawable.rightbutclassic;}
        else{r=R.drawable.rightbutspooky;}
        final int rPres;
        if(thm==0){rPres=R.drawable.rightpressclassic;}
        else{rPres=R.drawable.rightpressspoky;}
        right.setBackgroundResource(r);
        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    right.setBackgroundResource(rPres);
                    return true;
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    customView.moverIzqDerGirar(customView.getActivePiece(),2);
                    right.setBackgroundResource(r);
                    return true;
                }
                return false;
            }
        });

        final int l;
        if(thm==0){l=R.drawable.leftbutclassic;}
        else{l=R.drawable.leftbutspooky;}
        final int lPres;
        if(thm==0){lPres=R.drawable.leftpressclassic;}
        else{lPres=R.drawable.leftpresspoky;}
        left.setBackgroundResource(l);
        left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    left.setBackgroundResource(lPres);
                    return true;
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    customView.moverIzqDerGirar(customView.getActivePiece(),1);
                    left.setBackgroundResource(l);
                    return true;
                }
                return false;
            }
        });

        final int d;
        if(thm==0){d=R.drawable.downbutclassic;}
        else{d=R.drawable.downbutspooky;}
        final int dPres;
        if(thm==0){dPres=R.drawable.downpresclassic;}
        else{dPres=R.drawable.downpressspoky;}
        down.setBackgroundResource(d);
        down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    customView.fastFall();
                    down.setBackgroundResource(dPres);
                    return true;
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    customView.resetFall();
                    down.setBackgroundResource(d);
                    return true;
                }
                return false;
            }
        });

        final int sw;
        if(thm==0){sw=R.drawable.switchbutclassic;}
        else{sw=R.drawable.switchbutspooky;}
        final int swPres;
        if(thm==0){swPres=R.drawable.switchpressedclassic;}
        else{swPres=R.drawable.switchpressedspooky;}
        swi.setBackgroundResource(sw);
        swi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    swi.setBackgroundResource(swPres);
                    return true;
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    customView.switchPiece();
                    swi.setBackgroundResource(sw);
                    return true;
                }
                return false;
            }
        });

        customView=(CustomView) findViewById(R.id.CustomView);
        customView.setMa(this,gameMode);
        sc = (TextView) findViewById(R.id.valorPuntuacion);
        iv= (ImageView) findViewById(R.id.nextpiecefig);
        Intent intent = new Intent (customView.getContext(), MainActivity.class);
        intent.putExtra(THEME, thm);
        if(gameMode==0)
            customView.getSt().start();
        else{
            disableSwitch();
            customView.getSta().start();}
    }

    public void exitView (View view)

    {
        finish();
    }

    public void updateScore(String s){
        sc.setText(s);
    }


    public void changeGameOver(){
        if(!customView.isSecondThreadRunnig()){
            int scr=Integer.parseInt(sc.getText().toString());
            Intent intent;

            //Music stopping
            tetrixMusic.stop();
            tetrixMusic.setLooping(false);

            if(scr<250){
                intent = new Intent (customView.getContext(), GameOver.class);
            }
            else{
                intent = new Intent (customView.getContext(), QuickReboot.class);
                intent.putExtra(THEME, thm);
            }
            intent.putExtra("Score", sc.getText().toString());
            intent.putExtra("GameMode",gameMode);
            startActivityForResult(intent, 0);
            if(gameMode==0)
                customView.getSt().interrupt();
            else
                customView.getSta().interrupt();
        }
    }

    public void printNextPiece(int p){
        switch(p) {
            case 0:
                iv.setImageBitmap(bmpPiece);
                break;
            case 1:
                iv.setImageBitmap(bmpPiece1);
                break;
            case 2:
                iv.setImageBitmap(bmpPiece2);
                break;
            case 3:
                iv.setImageBitmap(bmpPiece3);
                break;
            case 4:
                iv.setImageBitmap(bmpPiece4);
                break;
            case 5:
                iv.setImageBitmap(bmpPiece5);
                break;
            default:
                iv.setImageBitmap(bmpPiece6);
        }

    }

    public void selectPalette(int palette){
        switch (palette){   //change palette
            case 0: case 3: {//yellow
                bmpPiece = BitmapFactory.decodeResource(getResources(), R.drawable.cubey);
                bmpPiece1 = BitmapFactory.decodeResource(getResources(), R.drawable.liney);
                bmpPiece2 = BitmapFactory.decodeResource(getResources(), R.drawable.sy);
                bmpPiece3 = BitmapFactory.decodeResource(getResources(), R.drawable.ty);
                bmpPiece4 = BitmapFactory.decodeResource(getResources(), R.drawable.zy);
                bmpPiece5 = BitmapFactory.decodeResource(getResources(), R.drawable.jy);
                bmpPiece6 = BitmapFactory.decodeResource(getResources(), R.drawable.ly);
                break;}
            case 1:{ //blue
                bmpPiece = BitmapFactory.decodeResource(getResources(), R.drawable.cubeb);
                bmpPiece1 = BitmapFactory.decodeResource(getResources(), R.drawable.lineb);
                bmpPiece2 = BitmapFactory.decodeResource(getResources(), R.drawable.sb);
                bmpPiece3 = BitmapFactory.decodeResource(getResources(), R.drawable.tb);
                bmpPiece4 = BitmapFactory.decodeResource(getResources(), R.drawable.zb);
                bmpPiece5 = BitmapFactory.decodeResource(getResources(), R.drawable.jb);
                bmpPiece6 = BitmapFactory.decodeResource(getResources(), R.drawable.lb);
                break;}
            case 2:{ //pink
                bmpPiece = BitmapFactory.decodeResource(getResources(), R.drawable.cubep);
                bmpPiece1 = BitmapFactory.decodeResource(getResources(), R.drawable.linep);
                bmpPiece2 = BitmapFactory.decodeResource(getResources(), R.drawable.sp);
                bmpPiece3 = BitmapFactory.decodeResource(getResources(), R.drawable.tp);
                bmpPiece4 = BitmapFactory.decodeResource(getResources(), R.drawable.zp);
                bmpPiece5 = BitmapFactory.decodeResource(getResources(), R.drawable.jp);
                bmpPiece6 = BitmapFactory.decodeResource(getResources(), R.drawable.lp);
                break;}
            case 4: {//orange
                bmpPiece = BitmapFactory.decodeResource(getResources(), R.drawable.cubeo);
                bmpPiece1 = BitmapFactory.decodeResource(getResources(), R.drawable.lineo);
                bmpPiece2 = BitmapFactory.decodeResource(getResources(), R.drawable.so);
                bmpPiece3 = BitmapFactory.decodeResource(getResources(), R.drawable.to);
                bmpPiece4 = BitmapFactory.decodeResource(getResources(), R.drawable.zo);
                bmpPiece5 = BitmapFactory.decodeResource(getResources(), R.drawable.jo);
                bmpPiece6 = BitmapFactory.decodeResource(getResources(), R.drawable.lo);
                break;}
            case 5: {//green
                bmpPiece = BitmapFactory.decodeResource(getResources(), R.drawable.cubeg);
                bmpPiece1 = BitmapFactory.decodeResource(getResources(), R.drawable.lineg);
                bmpPiece2 = BitmapFactory.decodeResource(getResources(), R.drawable.sg);
                bmpPiece3 = BitmapFactory.decodeResource(getResources(), R.drawable.tg);
                bmpPiece4 = BitmapFactory.decodeResource(getResources(), R.drawable.zg);
                bmpPiece5 = BitmapFactory.decodeResource(getResources(), R.drawable.jg);
                bmpPiece6 = BitmapFactory.decodeResource(getResources(), R.drawable.lg);
                break;
            }
            default:
                break;
        }
    }
    protected void enableSwitch(){
        Button sw=(Button)findViewById(R.id.Switch);
        sw.setVisibility(View.VISIBLE);
    }
    protected void disableSwitch(){
        Button sw=(Button)findViewById(R.id.Switch);
        sw.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed(){

    }

    @Override
    public void onPause(){
        super.onPause();
        ActivityManager activityManager = (ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.moveTaskToFront(getTaskId(), 0);
    }
}

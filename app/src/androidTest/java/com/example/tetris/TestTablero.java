package com.example.tetris;

import android.view.View;

import androidx.test.filters.MediumTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

@MediumTest
@RunWith(JUnit4.class)
public class TestTablero {

    int [][] tab = new int[10][20];
    Tablero tablero = new Tablero();

    @Before
    public void init(){
        for(int i = 0; i < 20;i++){
            for(int j = 0; j < 10;j++){
                if(i > 18){
                    if(j % 2==0){
                        tab[j][i] = 2;
                    }else{
                        tab[j][i] = 0;
                    }
                }else {
                    tab[j][i] = 0;
                }
            }
        }
    }
    @Test
    public void TestChangeTab(){
        int [][] tabAux = new int[10][20];
        for(int i =0;i<20;i++){
            for(int j=0;j<10;j++){
                tabAux[j][i]=tab[j][i];
            }
        }
        for(int i =0;i<20;i++){
            for(int j=0;j<10;j++){
                if(tab[j][i]!=0 ||tab[j][i]!=8){
                    assertEquals(tab[j][i],tabAux[j][i]);
                }
            }
        }

        tablero.changeTab(tab);
        for(int i =0;i<20;i++){
            for(int j=0;j<10;j++){
                if(tab[j][i]!=0 ||tab[j][i]!=8){
                    assertNotEquals(tab[j][i],tabAux[j][i]);
                }
            }
        }
    }
}
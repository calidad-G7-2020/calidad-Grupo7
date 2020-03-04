package com.mainpakage.Tetrix;

public class PlayerData implements Comparable{
    private String playerName;
    private int finalScore;

    public PlayerData(String pn, int fs) {
        this.playerName = pn;
        this.finalScore = fs;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public int getFinalScore() {
        return this.finalScore;
    }

    public void setPlayerName(String pn) {
        this.playerName = pn;
    }

    public void setFinalScore(int fs) {
        this.finalScore = fs;
    }

    public String toString() {
        return this.playerName+ "/"+this.finalScore;
    }

    public String toFormatString() { return this.playerName+ "     "+this.finalScore; }

    @Override
    public int compareTo(Object o) {
        PlayerData aux = (PlayerData) o;
        if(this.finalScore<aux.finalScore){
            return 1;
        }else if(this.finalScore==aux.finalScore){
            return 0;
        }else{
            return -1;
        }
    }
}
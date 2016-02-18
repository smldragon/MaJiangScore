package com.oosbt.majiang.model;

/**
 * Created by Frank on 2016/2/11.
 * 每牌的结果和其他与牌有关的信息
 */
public class Pai {


    private int roundNo; //这牌的序号。第1牌的序号是1，第2牌的序号是2.。。。。
    private Position dealer; //庄家位置
    private Position fangchong; //放冲的位置
    private PositionScores scores; //这盘每个位置的得分

    public int getRoundNo() {
        return roundNo;
    }

    public void setRoundNo(int roundNo) {
        this.roundNo = roundNo;
    }

    public Position getDealer() {
        return dealer;
    }

    public void setDealer(Position dealer) {
        this.dealer = dealer;
    }

    public Position getFangchong() {
        return fangchong;
    }

    public void setFangchong(Position fangchong) {
        this.fangchong = fangchong;
    }

    public synchronized void addScore(Position pos, double score) {
        if ( scores == null) {
            scores = new PositionScores();
        }

        scores.addScore(pos,score);
    }

    public double getScore(Position pos) {
        double score = scores.getScore(pos);
        return score;
    }
}

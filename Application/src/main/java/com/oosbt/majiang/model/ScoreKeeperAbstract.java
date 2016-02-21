package com.oosbt.majiang.model;

import android.util.Pair;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Frank on 2016/2/11.
 */
abstract public class ScoreKeeperAbstract implements ScoreKeeper {

    protected final List<Position> allROPositions = Position.getROPositionList();
    private final List<Pai> pais = new ArrayList<>();

    /**
     *
     * @param pos
     * @return这个位置的总积分
     */
    @Override
    public double getNetPoints(Position pos) {

        Double rtn = 0.0d;
        for(int i=0;i<pais.size();i++) {
            Pai pai = pais.get(i);
            rtn = rtn + pai.getScore(pos);
        }
        return rtn;
    }

    @Override
    public void reset() {
        pais.clear();
    }

    /**
     * 一方赢牌，三方输牌，这种场景没有放冲一说。
     * @param winningPos
     * @param winningPoints: 每个输方要输的钱。
     */
    public void score(Position winningPos, double winningPoints) {

        Pai pai = addNewPai();
        pai.addScore(winningPos,winningPoints*3); //赢方赢三方
        for(Position losingPos: allROPositions) {
            if ( losingPos.equals(winningPos)) {
                continue;
            }
            pai.addScore(losingPos,winningPoints*(-1.0d)); //输方输的钱
        }
    }

    @Override
    public void score(List<Pair<Position,Double>> posScoreList) {

        Pai pai = addNewPai();
        for(Pair<Position,Double> posScore: posScoreList) {
            Position pos = posScore.first;
            double points = posScore.second;
            pai.addScore(pos, points);
        }
    }


    private Pai addNewPai() {
        Pai pai = new Pai();
        pai.setRoundNo(pais.size() + 1);
        addPai(pai);
        return pai;
    }

    public void addPai(Pai pai) {
        pais.add(pai);
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("牌局      东       南        西       北").append("\n");

        DecimalFormat doubleNumFormat = new DecimalFormat(" 000.##;-000.##");

        final String spacing = "     ";
        for(int i=0;i<pais.size();i++) {
            Pai pai = pais.get(i);
            buf.append(String.format("%03d", pai.getRoundNo())).append(spacing);
            buf.append(doubleNumFormat.format(pai.getScore(Position.East))).append(spacing);
            buf.append(doubleNumFormat.format(pai.getScore(Position.South))).append(spacing);
            buf.append(doubleNumFormat.format(pai.getScore(Position.West))).append(spacing);
            buf.append(doubleNumFormat.format(pai.getScore(Position.North))).append(spacing);
            buf.append("\n");
        }
        buf.append("=======================================").append("\n");
        buf.append("总计     ");
        buf.append(doubleNumFormat.format(this.getNetPoints(Position.East))).append(spacing);
        buf.append(doubleNumFormat.format(this.getNetPoints(Position.South))).append(spacing);
        buf.append(doubleNumFormat.format(this.getNetPoints(Position.West))).append(spacing);
        buf.append(doubleNumFormat.format(this.getNetPoints(Position.North))).append(spacing);
        return buf.toString();
    }

}

package com.oosbt.majiang.model;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Frank on 2016/2/11.
 * 记录每个位置的分数，并提供运算
 */
public class PositionScores {


    private Map<Position, Double> scores;

    public synchronized void addScore(Position pos, double score) {
        if ( scores == null) {
            scores = new ConcurrentHashMap<>();
        }

        double oldScore = getScore(pos);
        double newScore = score + oldScore;
        scores.put(pos,newScore);
    }

    public double getScore(Position pos) {
        Double oldScore = scores.get(pos);
        if ( oldScore == null) {
            return 0.0d;
        } else {
            return oldScore;
        }
    }

    public PositionScores add(PositionScores anotherPositionScore) {
        if ( anotherPositionScore !=  null && anotherPositionScore.scores != null) {
            Set<Position> positionSet = anotherPositionScore.scores.keySet();
            Iterator<Position> ite = positionSet.iterator();
            while ( ite.hasNext() ) {
                Position pos = ite.next();
                double score = anotherPositionScore.getScore(pos);
                this.addScore(pos,score);
            }
        }
        return this;
    }

}

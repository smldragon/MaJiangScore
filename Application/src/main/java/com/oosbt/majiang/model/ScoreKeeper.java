package com.oosbt.majiang.model;

import android.util.Pair;

import java.util.List;

/**
 * Created by Frank on 2016/2/9.
 * 保存积分
 */
public interface ScoreKeeper {

    /**
     * 每个位置的得分
     */
    void score(List<Pair<Position,Double>> posScoreList);

    /**
     *
     * @param pos
     * @return这个位置的净积分（也就是输赢多少）
     */
    double getNetPoints(Position pos);
    void reset();
}

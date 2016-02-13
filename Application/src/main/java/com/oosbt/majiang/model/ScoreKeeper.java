package com.oosbt.majiang.model;

/**
 * Created by Frank on 2016/2/9.
 * 保存积分
 */
public interface ScoreKeeper {

    /**
     * 输钱三方每方分别要输的钱，赢方的赢的钱是三方输的钱的总和。
     * losingPosX：输方位置
     * losingAmountX: 输方losingPosx输的钱（为正数）
     */
    void score(Position losingPos1, double losingPoint1, Position losingPos2, double losingPoint2,
               Position losingPos3, double losingPoint3);
    /**
     * 记录位置pos的得分
     * @param points： 赢方赢每一输方的钱
     */
    void score(Position pos, double points);
    /**
     *
     * @param pos
     * @return这个位置的净积分（也就是输赢多少）
     */
    double getNetPoints(Position pos);
    void reset();
}

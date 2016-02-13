package com.oosbt.majiang.model.impl;

import com.oosbt.majiang.model.ScoreKeeperAbstract;

/**
 * Created by Frank on 2016/2/9.
 * 保存积分
 */
public class ScoreKeeperFuZhouImpl extends ScoreKeeperAbstract {

    private ScoreKeeperFuZhouImpl() {

    }

    public static ScoreKeeperFuZhouImpl getTheInstance() {
        return LazyInitHolder.Instance;
    }

    ///////////////////////////////////////////////////
    private static class LazyInitHolder {
        public static final ScoreKeeperFuZhouImpl Instance = new ScoreKeeperFuZhouImpl();
    }
}

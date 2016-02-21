package com.oosbt.majiang.model;

import com.oosbt.majiang.model.impl.ScoreKeeperFuZhouImpl;

/**
 * Created by Frank on 2016/2/20.
 */
public class ScoreKeeperFactory {

    public static ScoreKeeper getInstance() {
        return ScoreKeeperFuZhouImpl.getTheInstance();
    }
}

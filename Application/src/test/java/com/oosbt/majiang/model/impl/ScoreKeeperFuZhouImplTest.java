package com.oosbt.majiang.model.impl;

import com.oosbt.majiang.model.Position;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Frank on 2016/2/11.
 */
public class ScoreKeeperFuZhouImplTest {

    private ScoreKeeperFuZhouImpl testInstance;
    @Before
    public void setUp() throws Exception {
        testInstance = ScoreKeeperFuZhouImpl.getTheInstance();
        testInstance.reset();
    }

    @After
    public void tearDown() throws Exception {

    }
    @Test
    public void testReset() throws Exception {

        final double testScore = 100d;
        testInstance.score(Position.East, testScore);

        double netPoints = testInstance.getNetPoints(Position.East);
        assertEquals(testScore*3.0d, netPoints, 0.01d);

        testInstance.reset();
        netPoints = testInstance.getNetPoints(Position.East);
        assertEquals(netPoints, 0.0d, 0.01d);

    }
    @Test
    public void testScore() throws Exception {
        final double testScore = 100d;
        testInstance.score(Position.East, testScore);

        testInstance.score(Position.East, testScore);

        testInstance.score(Position.West, testScore);

        testInstance.score(Position.South, testScore);

        testInstance.score(Position.North, testScore);

        double eastNet = testInstance.getNetPoints(Position.East);
        assertEquals(testScore*3, eastNet, 0.01d);

        double westNet = testInstance.getNetPoints(Position.West);
        assertEquals(testScore*(-1.0d), westNet, 0.01d);

        System.out.println(testInstance.toString());
    }
}
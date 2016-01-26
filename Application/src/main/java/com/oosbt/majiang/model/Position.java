package com.oosbt.majiang.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Frank on 2016/1/24.
 */
public enum Position {

    East("playerEast"),
    West("playerWest"),
    South("playerSouth"),
    North("playerNorth");

    private static final Map<String, Position> lookupMap = new ConcurrentHashMap<>();
    private final String handler;
    private Player player;

    Position(String handler) {
        this.handler = handler;
    }

    public static Position findPosition(String handler) {
        if (lookupMap.size() == 0) {
            synchronized (lookupMap) {
                if (lookupMap.size() == 0) {
                    Position[] consts = Position.values();
                    for (Position pos : consts) {
                        lookupMap.put(pos.getHandler().toLowerCase(), pos);
                    }
                }
            }
        }
        return lookupMap.get(handler.toLowerCase());
    }

    /**
     * @return a new list position order to make it readonly
     */
    public static List<Position> getPositionArrangementOrder() {
        List<Position> posArrangeOrder = new ArrayList<>();
        posArrangeOrder.add(East);
        posArrangeOrder.add(South);
        posArrangeOrder.add(West);
        posArrangeOrder.add(North);
        return posArrangeOrder;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getHandler() {
        return handler;
    }
}

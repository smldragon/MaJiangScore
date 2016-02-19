package com.oosbt.majiang.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Frank on 2016/1/24.
 */
public enum Position {

    East("playerEast"),
    West("playerWest"),
    South("playerSouth"),
    North("playerNorth");

    private static final List<Position> lookup = new Vector<>();
    private final String handler;
    private Player player;

    Position(String handler) {
        this.handler = handler;
    }

    /**
     * 如果handler包含位置的名称，就返回该位置。如给“PlayerEastButton”，就返回East
     * @param handler
     * @return
     */
    public static Position findPosition(String handler) {
        if (lookup.size() == 0) {
            synchronized (lookup) {
                if (lookup.size() == 0) {
                    Position[] consts = Position.values();
                    for (Position pos : consts) {
                        lookup.add(pos);
                    }
                }
            }
        }

        for (Position pos : lookup) {
            String posHandler = pos.getHandler().toLowerCase();
            if ( handler.toLowerCase().indexOf(posHandler) >=0) {
                return pos;
            }
        }
        return null;
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

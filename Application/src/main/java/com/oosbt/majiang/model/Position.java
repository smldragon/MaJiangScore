package com.oosbt.majiang.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * Created by Frank on 2016/1/24.
 */
public enum Position {

    East("playerEast","东"),
    West("playerWest","西"),
    South("playerSouth","南"),
    North("playerNorth","北");

    private static final List<Position> lookup = new Vector<>();
    private static List<Position> readOnlyPositionList;
    private final String handler;
    private final String positionName;
    private Player player;

    Position(String handler,String positionName) {

        this.handler = handler;
        this.positionName = positionName;
    }

    /**
     * 如果handler包含位置的名称，就返回该位置。如给“PlayerEastButton”，就返回East
     * @param handler
     * @return
     */
    public static Position findPosition(String handler) {

        init();

        for (Position pos : lookup) {
            String posHandler = pos.getHandler().toLowerCase();
            if ( handler.toLowerCase().indexOf(posHandler) >=0) {
                return pos;
            }
        }
        return null;
    }
    private static void init() {
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
    }
    /**
     * @return a new list position order to make it readonly
     */
    public synchronized static List<Position> getROPositionList() {
        if ( readOnlyPositionList == null) {
            List<Position> posArrangeOrder = new ArrayList<>(4);
            posArrangeOrder.add(East);
            posArrangeOrder.add(South);
            posArrangeOrder.add(West);
            posArrangeOrder.add(North);
            readOnlyPositionList = Collections.unmodifiableList(posArrangeOrder);
        }

        return readOnlyPositionList;
    }
    /**
     * @param pos1
     * @param pos2
     * @param pos3
     * @return返回pos1, pos2,pos3外的第四方的位置
     */
    public static Position getTheForthPosition(Position pos1, Position pos2, Position pos3) {

        //找到赢方并设置赢方分数
        List<Position> readOnlyPositionList = getROPositionList();
        for(Position pos: readOnlyPositionList) {
            if ( pos.equals(pos1) == false && pos.equals(pos2) == false &&
                    pos.equals(pos3) == false) {

                return pos;
            }
        }
        return null;
    }

    /**
     * @param posExclused
     * @return除posExclused之外的其他位置
     */
    public static List<Position> getPositionsExclused(Position posExclused) {
        List<Position> readOnlyPositionList = getROPositionList();
        List<Position> rtn = new ArrayList<>(readOnlyPositionList.size()-1);
        for(Position pos: readOnlyPositionList) {
            if ( pos.equals(posExclused) == false) {
                rtn.add(pos);
            }
        }
        return rtn;
    }

    public String getPositionName() {
        return positionName;
    }

    public Player getPlayer() {
        if ( player == null) {
            player = new Player();
            player.setLname(positionName);

        }
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getHandler() {
        return handler;
    }

}

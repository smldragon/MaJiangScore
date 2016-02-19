package com.oosbt.majiang.control;

import android.view.View;

import com.oosbt.majiang.model.Position;

/**
 * Created by Frank on 2016/2/18.
 * 点击位置图像时触发的事件。
 */
public class PositionListener implements View.OnClickListener {

    private final VoiceInputActivity activity;
    public PositionListener(VoiceInputActivity activity) {
        this.activity = activity;
    }
    @Override
    public void onClick(View v) {

        int resid = v.getId();
        String positionName = activity.getResources().getResourceEntryName(resid);
        Position pos = Position.findPosition(positionName);
        System.out.println("positionName="+positionName);
    }
}

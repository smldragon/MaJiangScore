package com.oosbt.majiang.control;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import com.oosbt.majiang.model.Position;

/**
 * Created by Frank on 2016/2/18.
 * 点击位置图像时触发的事件。
 */
public class PositionListener implements View.OnClickListener {

    private final Activity activity;
    public PositionListener(Activity activity) {
        this.activity = activity;
    }
    @Override
    public void onClick(View v) {

        int resid = v.getId();
        String positionName = activity.getResources().getResourceEntryName(resid);
        Position pos = Position.findPosition(positionName);
        //System.out.println("positionName="+positionName);

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        final View promptView = layoutInflater.inflate(R.layout.score_input, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
        alertDialogBuilder.setView(promptView);

        //final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("确认", new ScoreInputListener(activity, promptView));
        alertDialogBuilder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
}

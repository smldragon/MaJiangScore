package com.oosbt.majiang.control;

import android.app.Activity;
import android.content.DialogInterface;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import com.oosbt.majiang.model.Player;
import com.oosbt.majiang.model.Position;
import com.oosbt.majiang.model.ScoreKeeper;
import com.oosbt.majiang.model.ScoreKeeperFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Frank on 2016/2/20.
 */
public class ScoreInputListener implements DialogInterface.OnClickListener {

    private final Activity activity;
    private final View scoreInputView;
    public ScoreInputListener(Activity activity,View scoreInputView) {
        this.activity = activity;
        this.scoreInputView = scoreInputView;
    }
    @Override
    public void onClick(DialogInterface dialog, int which) {

        saveScores();

        updateScores();

    }

    /**
     * 保存得分
     */
    private void saveScores() {

        List<Position> allROPositions = Position.getROPositionList();
        List<Pair<Position,Double>> posScoreList = new ArrayList<>(allROPositions.size());
        for(Position pos: allROPositions) {
            String textFieldName = pos.getHandler()+"Score";
            int resID = activity.getResources().getIdentifier(textFieldName, "id", activity.getPackageName());
            TextView textView = ((TextView) scoreInputView.findViewById(resID));
            String score_text = textView.getText().toString();
            double score = Double.parseDouble(score_text);
            Pair<Position,Double> posScore = new Pair<>(pos,score);
            posScoreList.add(posScore);
        }

        ScoreKeeper scoreKeeper = ScoreKeeperFactory.getInstance();
        scoreKeeper.score(posScoreList);

    }

    /**
     * 更新每个位置的总分显示。
     */
    private void updateScores() {

        List<Position> allROPositions = Position.getROPositionList();
        ScoreKeeper scoreKeeper = ScoreKeeperFactory.getInstance();
        for(Position pos: allROPositions) {
            String textFieldName = pos.getHandler();
            int resID = activity.getResources().getIdentifier(textFieldName, "id", activity.getPackageName());
            TextView textView = ((TextView) activity.findViewById(resID));
            Player player = pos.getPlayer();
            double totalPoint = scoreKeeper.getNetPoints(pos);
            textView.setText(player.getLname() + player.getFname() + "(" +totalPoint+")");
        }
    }
}

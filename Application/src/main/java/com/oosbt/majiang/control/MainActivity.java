/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.oosbt.majiang.control;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.oosbt.majiang.model.SttResult;

/**
 * Provides the landing screen of this sample. There is nothing particularly interesting here. All
 * the codes related to the Direct Share feature are in {@link SampleChooserTargetService}.
 */
public class MainActivity extends VoiceInputActivity {

    private TextView east, west, south, north;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.stt:
                    stt();
                    break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        Resources r = this.getResources();
        String title = r.getString(R.string.appName) + r.getString(R.string.appVersion);
        this.setTitle(title);

        east = (TextView) findViewById(R.id.playerEast);
        south = (TextView) findViewById(R.id.playerSouth);
        west = (TextView) findViewById(R.id.playerWest);
        north = (TextView) findViewById(R.id.playerNorth);

        //setActionBar((Toolbar) findViewById(R.id.toolbar));
        findViewById(R.id.stt).setOnClickListener(mOnClickListener);
    }

    private void stt() {

        setPosition("playerEast");
        setPosition("playerWest");
        setPosition("playerSouth");
        setPosition("playerNorth");

    }

    private void setPosition(String name) {

        Resources r = this.getResources();
        int resID = getResources().getIdentifier(name, "id", getPackageName());
        TextView view = ((TextView) findViewById(resID));

        //   resID = getResources().getIdentifier(name, "string", getPackageName());
        //  String position = getString(resID);
        String position = "";
        boolean toContinue = true;
        while (toContinue) {

            SttResult sttResult = prepareRecgonizedText();
            String text;
            String err = sttResult.getErrMsg();
            if (err == null) {

                text = position + ":" + sttResult.getRecgonizedText();
                toContinue = false;
            } else {

                text = "错误:" + err + ".请重试！";
            }
            view.setText(text);
        }

    }
}

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

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Provides the landing screen of this sample. There is nothing particularly interesting here. All
 * the codes related to the Direct Share feature are in {@link SampleChooserTargetService}.
 */
public class MainActivity extends Activity {

    private EditText mEditBody;
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
        setActionBar((Toolbar) findViewById(R.id.toolbar));
        mEditBody = (EditText) findViewById(R.id.body);
        findViewById(R.id.stt).setOnClickListener(mOnClickListener);
    }

    private void stt() {

        Resources r = this.getResources();
        MJUtils.requestDangerousPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, r.getInteger(R.integer.REQUEST_CODE_PERMISSION_WRITE_EXTERNAL_STORAGE));
        MJUtils.requestDangerousPermission(this, android.Manifest.permission.RECORD_AUDIO, r.getInteger(R.integer.REQUEST_CODE_PERMISSION_RECORD_AUDIO));
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        String language = Locale.US.toString();
        String prompt = getString(R.string.speechPrompt);
        Log.e("------DEBUG", "language=" + language + " prompt=" + prompt);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, language);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, prompt);
        try {
            startActivityForResult(intent, r.getInteger(R.integer.REQUEST_CODE_SPEECH_INPUT));
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speechNotSupportedPrompt),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        Resources r = this.getResources();
        if (requestCode == r.getInteger(R.integer.REQUEST_CODE_SPEECH_INPUT)) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                for (int i = 0; i < matches.size(); i++) {
                    Log.i("STT SUCCESS", "i=" + i + " text=" + matches.get(i));
                }
                TextView view = (TextView) findViewById(R.id.playerEast);
                view.setText(matches.get(0));
            } else {
                Log.e("STT error", "谷歌返回语音翻译出错");
            }
        }
    }
}

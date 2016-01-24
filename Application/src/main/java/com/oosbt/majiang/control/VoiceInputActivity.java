package com.oosbt.majiang.control;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.speech.RecognizerIntent;
import android.util.Log;

import com.oosbt.majiang.model.SttResult;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Frank on 2016/1/21.
 */
public class VoiceInputActivity extends Activity {

    private static final Locale DefaultVoiceLanguage = Locale.US;
    private static boolean initPermission = false;
    private static int playerCount = 0;
    private Locale localLanguage;
    private SttResult sttResult;

    public VoiceInputActivity() {

    }

    public Locale getLocalLanguage() {
        if (localLanguage == null) {
            localLanguage = DefaultVoiceLanguage;
        }
        return localLanguage;
    }

    public void setLocalLanguage(Locale localLanguage) {
        this.localLanguage = localLanguage;
    }

    /**
     * @return
     */
    public SttResult prepareRecgonizedText() {

        sttResult = new DefaultSttResultImpl();
        sttResult.startRecgonization();
        return sttResult;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        Resources r = this.getResources();
        if (requestCode == r.getInteger(R.integer.REQUEST_CODE_SPEECH_INPUT)) {
            sttResult.processCallBack(resultCode, data);
        }
    }

    /////////////////////////////////////////////////////
    private class DefaultSttResultImpl implements SttResult {
        private final CountDownLatch latch = new CountDownLatch(1);
        private String errMsg = null;
        private String text = null;

        public DefaultSttResultImpl() {
        }

        @Override
        public void startRecgonization() {
            Resources r = VoiceInputActivity.this.getResources();
            initPermissions();

            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            String language = VoiceInputActivity.this.getLocalLanguage().toString();
            String prompt = getString(R.string.speechPrompt);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, language);
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, prompt);
/**
 try {
 startActivityForResult(intent, r.getInteger(R.integer.REQUEST_CODE_SPEECH_INPUT));
 } catch (ActivityNotFoundException a) {
 String errMsg = getString(R.string.speechNotSupportedPrompt);
 sttResult.setErrMsg(errMsg);
 Toast.makeText(getApplicationContext(),errMsg,Toast.LENGTH_SHORT).show();

 }
 */
            text = "麻油" + (playerCount++);
            latch.countDown();
        }

        @Override
        public void processCallBack(int resultCode, Intent data) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                for (int i = 0; i < matches.size(); i++) {
                    Log.i("STT", "i=" + i + " text=" + matches.get(i));
                }
                text = matches.get(0);
            } else {
                errMsg = "谷歌返回语音翻译出错";
                Log.e("STT", errMsg);
            }
            latch.countDown();
        }

        @Override
        public String getRecgonizedText() {
            waiting();
            return text;
        }

        @Override
        public void setRecgonizedText(String text) {
            this.text = text;
        }

        @Override
        public String getErrMsg() {

            waiting();
            return errMsg;
        }

        @Override
        public void setErrMsg(String errMsg) {
            this.errMsg = errMsg;
        }

        private void waiting() {
            try {
                latch.await();
            } catch (Exception e) {
                Log.e("SST", "Error in CountDownLatch.wait() ", e);
            }
        }

        private void initPermissions() {
            if (initPermission == false) {
                Resources r = VoiceInputActivity.this.getResources();
                MJUtils.requestDangerousPermission(VoiceInputActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, r.getInteger(R.integer.REQUEST_CODE_PERMISSION_WRITE_EXTERNAL_STORAGE));
                MJUtils.requestDangerousPermission(VoiceInputActivity.this, android.Manifest.permission.RECORD_AUDIO, r.getInteger(R.integer.REQUEST_CODE_PERMISSION_RECORD_AUDIO));
                MJUtils.requestDangerousPermission(VoiceInputActivity.this, android.Manifest.permission.ACCESS_NETWORK_STATE, r.getInteger(R.integer.REQUEST_CODE_SPEECH_INPUT));
                MJUtils.requestDangerousPermission(VoiceInputActivity.this, Manifest.permission.INTERNET, r.getInteger(R.integer.REQUEST_CODE_PERMISSION_INTERNET));
                initPermission = true;
            }
        }
    }
}

package com.oosbt.majiang.model;

import android.content.Intent;

/**
 * Created by Frank on 2016/1/23.
 * 语音识别（STT）的结果
 */
public interface SttResult {

    String getRecgonizedText();

    void setRecgonizedText(String text);

    String getErrMsg();

    void setErrMsg(String errMsg);

    void startRecgonization();

    void processCallBack(int returnCode, Intent data);
}

package com.thoughtworks.conference.model;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * @program: conference
 * @description: 会话对象
 **/
public class Talk implements Serializable {


    private int talkNumber;//会话序号
    private int       talkDuration;//会话时长
    private String    talkContent;//会话内容
    private String    startTalk;//开始会话时间
    private int       talkType = -1;//固定会话类型，默认为-1表示不固定：例如：Lunch,固定属于上午MORNING_TYPE;Networking Event,固定属于下午AFTERNOON_TYPE
    private LocalTime preStartTalk;//指定会话时间段的开始时间
    private LocalTime preEndTalk;//指定会话时间段的结束时间

    public Talk() {
    }

    public Talk(int talkNumber, int talkDuration, String talkContent, String startTalk, int talkType, LocalTime preStartTalk, LocalTime preEndTalk) {
        this.talkNumber = talkNumber;
        this.talkDuration = talkDuration;
        this.talkContent = talkContent;
        this.startTalk = startTalk;
        this.talkType = talkType;
        this.preStartTalk = preStartTalk;
        this.preEndTalk = preEndTalk;
    }


    public int getTalkNumber() {
        return talkNumber;
    }

    public void setTalkNumber(int talkNumber) {
        this.talkNumber = talkNumber;
    }

    public int getTalkDuration() {
        return talkDuration;
    }

    public void setTalkDuration(int talkDuration) {
        this.talkDuration = talkDuration;
    }

    public String getTalkContent() {
        return talkContent;
    }

    public void setTalkContent(String talkContent) {
        this.talkContent = talkContent;
    }

    public String getStartTalk() {
        return startTalk;
    }

    public void setStartTalk(String startTalk) {
        this.startTalk = startTalk;
    }

    public int getTalkType() {
        return talkType;
    }

    public void setTalkType(int talkType) {
        this.talkType = talkType;
    }

    public LocalTime getPreStartTalk() {
        return preStartTalk;
    }

    public void setPreStartTalk(LocalTime preStartTalk) {
        this.preStartTalk = preStartTalk;
    }

    public LocalTime getPreEndTalk() {
        return preEndTalk;
    }

    public void setPreEndTalk(LocalTime preEndTalk) {
        this.preEndTalk = preEndTalk;
    }
}

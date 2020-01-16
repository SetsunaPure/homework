package com.thoughtworks.conference.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

/**
 * @program: conference
 * @description: 会议计划
 **/
public class Conference implements Serializable {

    private static final long serialVersionUID = 4021718498564896654L;

    private int       conferenceDayNumber;//会议日期序号
    private int       conferenceType;//0:表示上午;1:表示下午
    private int        leftLimitMinute;//剩余限制时长
    private LocalTime  talkTimeStart;//作为添加talk节点开始时间的标记
    private LocalTime  conferenceStartTime;//作为添加会议开始时间的标记
    private LocalTime  conferenceEndTime;//作为添加会议结束时间的标记
    private List<Talk> talkList;//会话列表


    public int getConferenceDayNumber() {
        return conferenceDayNumber;
    }

    public void setConferenceDayNumber(int conferenceDayNumber) {
        this.conferenceDayNumber = conferenceDayNumber;
    }

    public int getConferenceType() {
        return conferenceType;
    }

    public void setConferenceType(int conferenceType) {
        this.conferenceType = conferenceType;
    }

    public int getLeftLimitMinute() {
        return leftLimitMinute;
    }

    public void setLeftLimitMinute(int leftLimitMinute) {
        this.leftLimitMinute = leftLimitMinute;
    }

    public LocalTime getTalkTimeStart() {
        return talkTimeStart;
    }

    public void setTalkTimeStart(LocalTime talkTimeStart) {
        this.talkTimeStart = talkTimeStart;
    }

    public LocalTime getConferenceStartTime() {
        return conferenceStartTime;
    }

    public void setConferenceStartTime(LocalTime conferenceStartTime) {
        this.conferenceStartTime = conferenceStartTime;
    }

    public LocalTime getConferenceEndTime() {
        return conferenceEndTime;
    }

    public void setConferenceEndTime(LocalTime conferenceEndTime) {
        this.conferenceEndTime = conferenceEndTime;
    }

    public List<Talk> getTalkList() {
        return talkList;
    }

    public void setTalkList(List<Talk> talkList) {
        this.talkList = talkList;
    }


    public Conference(int conferenceDayNumber, int conferenceType, int leftLimitMinute, LocalTime talkTimeStart, LocalTime conferenceStartTime, LocalTime conferenceEndTime, List<Talk> talkList) {
        this.conferenceDayNumber = conferenceDayNumber;
        this.conferenceType = conferenceType;
        this.leftLimitMinute = leftLimitMinute;
        this.talkTimeStart = talkTimeStart;
        this.conferenceStartTime = conferenceStartTime;
        this.conferenceEndTime = conferenceEndTime;
        this.talkList = talkList;
    }

}

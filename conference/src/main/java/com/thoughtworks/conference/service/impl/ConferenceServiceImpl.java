package com.thoughtworks.conference.service.impl;

import com.thoughtworks.conference.model.Conference;
import com.thoughtworks.conference.model.Talk;
import com.thoughtworks.conference.service.IConferenceService;
import com.thoughtworks.conference.util.DateLimitConst;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: conference
 * @description: 会议安排：将会话添加到会议中
 **/
public class ConferenceServiceImpl implements IConferenceService {

    @Override
    public int getConferenceType(int conferenceNumberInt){
        int conferenceType = 0;
        switch (conferenceNumberInt){
            case DateLimitConst.MORNING_TYPE:
                conferenceType = DateLimitConst.MORNING_TYPE;
                break;
            case DateLimitConst.AFTERNOON_TYPE:
                conferenceType = DateLimitConst.AFTERNOON_TYPE;
                break;
        }
        return conferenceType;
    }

    @Override
    public int getMaxValue(int conferenceType) {
        int maxValue = 0;
        switch (conferenceType){
            case DateLimitConst.MORNING_TYPE:
                maxValue = DateLimitConst.MORNING_MAX;
                break;
            case DateLimitConst.AFTERNOON_TYPE:
                maxValue = DateLimitConst.AFTERNOON_MAX;
                break;
        }
        return maxValue;
    }

    @Override
    public LocalTime getConferenceStartTime(int conferenceType) {
        LocalTime startTime = LocalTime.parse("00:00:00");
        switch (conferenceType){
            case DateLimitConst.MORNING_TYPE:
                startTime = LocalTime.parse(DateLimitConst.MORNING_START);
                break;
            case DateLimitConst.AFTERNOON_TYPE:
                startTime = LocalTime.parse(DateLimitConst.AFTERNOON_START);
                break;
        }
        return startTime;
    }

    @Override
    public LocalTime getConferenceEndTime(int conferenceType) {
        LocalTime endTime = LocalTime.parse("23:59:59");
        switch (conferenceType){
            case DateLimitConst.MORNING_TYPE:
                endTime = LocalTime.parse(DateLimitConst.MORNING_END);
                break;
            case DateLimitConst.AFTERNOON_TYPE:
                endTime = LocalTime.parse(DateLimitConst.AFTERNOON_END);
                break;
        }
        return endTime;
    }

    @Override
    public boolean isLeftMinuteTalk(Conference conference, Talk talk) {
        int leftLimitMinuteNext = conference.getLeftLimitMinute()-talk.getTalkDuration();
        if(leftLimitMinuteNext<0){
            return false;
        }
        return true;
    }

    @Override
    public int getLeftMinute(Conference conference, Talk talk) {
        int leftLimitMinuteNext = conference.getLeftLimitMinute()-talk.getTalkDuration();
        return leftLimitMinuteNext;
    }

    @Override
    public LocalTime getNextTalkTimeStart(Conference conference, Talk talk) {
        LocalTime talkTimeStart = conference.getTalkTimeStart();
        talkTimeStart = talkTimeStart.plusMinutes(talk.getTalkDuration());
        return talkTimeStart;
    }

    @Override
    public Conference addTalkNode(Conference conference, Talk talk) {
        List<Talk> talkList = conference.getTalkList();
        if(null==talkList){
            talkList = new ArrayList<>();
        }
        talkList.add(talk);
        return conference;
    }

    @Override
    public boolean isExistTimeQuantunByTalk(Conference conference, Talk talk){
        if(conference.getConferenceType()!=talk.getTalkType()){
            return false;
        }
        if(conference.getTalkTimeStart().isAfter(talk.getPreEndTalk())){
            return false;
        }
        if(conference.getConferenceStartTime().isAfter(talk.getPreStartTalk())){
            return false;
        }
        if(conference.getConferenceEndTime().isAfter(talk.getPreEndTalk())){
            return false;
        }
        return true;
    }

    @Override
    public List<Conference> getConferenceByFixTalk(List<Conference> conferences, Talk talk) {
        List<Conference> conferenceByFixTalk = conferences.stream().filter(conference -> isExistTimeQuantunByTalk(conference,talk)).collect(Collectors.toList());
        return conferenceByFixTalk;
    }

    @Override
    public Conference getConference(List<Conference> conferences, Talk talk) {
        List<Conference> cfList =conferences.stream().filter(item->this.isLeftMinuteTalk(item,talk)).collect(Collectors.toList());
        if(null!=cfList&&cfList.size()>0){
            return cfList.get(0);
        }
        return null;
    }

    @Override
    public boolean isExistConference(List<Conference> conferences, Talk talk) {
        Conference cf = getConference(conferences,talk);
        if(null==cf){
            return false;
        }
        return true;
    }

    @Override
    public List<Conference> addConference(List<Conference> conferences) {
        int curConferenceNumber =  conferences.size();
        double conferenceDayNumberDouble = curConferenceNumber/DateLimitConst.INTERVAL_NUM;
        int conferenceNumberInt = curConferenceNumber%DateLimitConst.INTERVAL_NUM;
        int conferenceDayNumber = (int)Math.ceil(conferenceDayNumberDouble)+1;

        int conferenceType = this.getConferenceType(conferenceNumberInt);
        int maxValue = this.getMaxValue(conferenceType);
        LocalTime startTime = this.getConferenceStartTime(conferenceType);
        LocalTime endTime = this.getConferenceEndTime(conferenceType);
        Conference conference = new Conference(conferenceDayNumber,conferenceType,maxValue,startTime,startTime,endTime,new ArrayList<Talk>());
        conferences.add(conference);
        return conferences;
    }

    @Override
    public void conferenceResultPrintout(List<Conference> conferences) {
        for(Conference conference:conferences){
            if(conference.getConferenceType()==0){
                System.out.println("Track "+conference.getConferenceDayNumber()+":");
            }
            List<Talk> talkList = conference.getTalkList();
            talkList.stream().forEach(item->{
                System.out.println(item.getStartTalk()+":"+item.getTalkContent());
            });
        }
    }

    @Override
    public List<String> conferenceResultFileout(List<Conference> conferences){
        List<String> lines = new ArrayList<>();
        conferences.stream().forEach(conference -> {
            if(conference.getConferenceType()==0){
                lines.add("Track "+conference.getConferenceDayNumber()+":");
            }
            List<Talk> talkList = conference.getTalkList();
            talkList.stream().forEach(item->{
                lines.add(item.getStartTalk()+":"+item.getTalkContent());
            });
        });
        return lines;
    }
}

package com.thoughtworks.conference.service.impl;

import com.thoughtworks.conference.exception.TrackException;
import com.thoughtworks.conference.model.Conference;
import com.thoughtworks.conference.model.Talk;
import com.thoughtworks.conference.service.ITalkService;
import com.thoughtworks.conference.util.Converts;
import com.thoughtworks.conference.util.DateLimitConst;
import com.thoughtworks.conference.util.ExceptionCodeConst;
import com.thoughtworks.conference.util.Sequence;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @program: conference
 * @description: 会话对象
 **/
public class TalkServiceImpl implements ITalkService {

    @Override
    public void checkTalkDuration(List<Talk> talks) {
        for(Talk talk : talks){

            int talkDuration = talk.getTalkDuration();

            //超出时间段,抛出错误提示
            if(talkDuration > DateLimitConst.MAXVALUE){
                throw new TrackException(talk.getTalkContent()+":"+ ExceptionCodeConst.DATELIMIT_ERROR+":"+DateLimitConst.MAXVALUE);
            }
            //时间段为负数,抛出错误提示
            if(talkDuration < 0){
                throw new TrackException(talk.getTalkContent()+":"+ ExceptionCodeConst.NEGATIVE_ERROR);
            }
        }
    }

    @Override
    public void addTalk(List<Talk> talkList,Talk talk) {
        talkList.add(talk);
    }

    @Override
    public List<Talk> transTalk(List<String> lines) {
        List<Talk> talks = new ArrayList<>();
        for(String talkContent:lines){
            Talk talk = new Talk();
            String minute = Converts.getNumbers(talkContent);
            int minuteInt = "".equals(minute.trim())?0:Integer.parseInt(minute);
            talk.setTalkDuration(minuteInt);
            talk.setTalkContent(talkContent);
            talks.add(talk);
        }
        return talks;
    }

    @Override
    public Talk updateFixTalk(Conference conference, Talk talk) {
        talk.setTalkNumber(Sequence.getNextSN());
        LocalTime localTime = conference.getTalkTimeStart();
        talk.setStartTalk(Converts.formatTime(localTime));
        Talk talkClone = new Talk(talk.getTalkNumber(),talk.getTalkDuration(),talk.getTalkContent(),talk.getStartTalk(),talk.getTalkType(),talk.getPreStartTalk(),talk.getPreEndTalk());
        return talkClone;
    }

    @Override
    public Talk updateTalk(Conference conference, Talk talk) {
        talk.setTalkNumber(Sequence.getNextSN());
        LocalTime localTime = conference.getTalkTimeStart();
        talk.setStartTalk(Converts.formatTime(localTime));
        talk.setPreStartTalk(conference.getConferenceStartTime());
        talk.setPreEndTalk(conference.getConferenceEndTime());
        return talk;
    }

    @Override
    public List<Talk> sortTalkDuration(List<Talk> talks) {
        talks.sort(Comparator.comparing(Talk::getTalkDuration).reversed());
        return talks;
    }
}

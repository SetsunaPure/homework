package com.thoughtworks.conference.service.impl;

import com.thoughtworks.conference.model.Conference;
import com.thoughtworks.conference.model.Talk;
import com.thoughtworks.conference.service.IConferenceService;
import com.thoughtworks.conference.service.ITalkService;
import com.thoughtworks.conference.service.ITrackStrategyService;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: conference
 * @description: 策略类：talk根据时间安排到conference中
 **/
public class TrackStrategyServiceImpl implements ITrackStrategyService {

    IConferenceService conferenceService = new ConferenceServiceImpl();
    ITalkService talkService = new TalkServiceImpl();

    @Override
    public void appendConference(Conference conference, Talk talk) {

        if(conference.getTalkTimeStart().isBefore(talk.getPreStartTalk())){
            conference.setTalkTimeStart(talk.getPreStartTalk());
        }

        if(conference.getTalkTimeStart().isAfter(talk.getPreStartTalk()) || conference.getTalkTimeStart().equals(talk.getPreStartTalk())){
            Talk talkPre = conference.getTalkList().get(conference.getTalkList().size()-1);
            if(talkPre.getTalkDuration()==0){
                conference.setTalkTimeStart(talk.getPreEndTalk());
            }
        }


        Talk talkRtn = talkService.updateFixTalk(conference,talk);
        conferenceService.addTalkNode(conference,talkRtn);
    }

    @Override
    public void updateConferenceAndTalk(Conference conference, Talk talk) {
        talkService.updateTalk(conference,talk);
        conferenceService.addTalkNode(conference,talk);

        LocalTime talkTimeStart = conferenceService.getNextTalkTimeStart(conference,talk);
        conference.setTalkTimeStart(talkTimeStart);//更新剩余开始时间

        int leftLimitMinute = conferenceService.getLeftMinute(conference,talk);
        conference.setLeftLimitMinute(leftLimitMinute);//更新剩余时间
    }

    @Override
    public List<Conference> updateConferenceByTalkZero(List<Conference> conferences, Talk talk) {
        Conference conference = conferences.get(conferences.size()-1);
        this.updateConferenceAndTalk(conference,talk);
        return conferences;
    }

    @Override
    public List<Conference> updateConferencesByTalkMoreZero(List<Conference> conferences, Talk talk) {
        Conference conference =conferenceService.getConference(conferences,talk);
        this.updateConferenceAndTalk(conference,talk);
        return conferences;
    }

    @Override
    public List<Conference> updateConferenceByFixTalk(List<Conference> conferences, Talk talk) {

        List<Conference> conferencesFix = conferenceService.getConferenceByFixTalk(conferences,talk);
        conferencesFix.stream().forEach(conferenceFix->{
            this.appendConference(conferenceFix,talk);
        });
        return conferences;
    }

    @Override
    public List<Conference> arrangeConference(List<Conference> conferences, List<Talk> talks) {
        if(conferences.size()==0){
            conferenceService.addConference(conferences);
        }

        //会话时间校验
        talkService.checkTalkDuration(talks);

        //会话按照类型分组
        List<Talk> talkMoreZeroList = talks.stream().filter(talk ->(talk.getTalkDuration()>0) && talk.getTalkType()==-1).collect(Collectors.toList());
        List<Talk> talkEqZeroList = talks.stream().filter(talk ->(talk.getTalkDuration()==0) && talk.getTalkType()==-1).collect(Collectors.toList());
        List<Talk> talkFixList = talks.stream().filter(talk ->(talk.getTalkType()!=-1)).collect(Collectors.toList());

        //安排设定时间段的会话
        talkMoreZeroList.stream().forEach(talkMoreZero->{
            boolean iec = conferenceService.isExistConference(conferences,talkMoreZero);
            if(!iec){
                conferenceService.addConference(conferences);
            }
            this.updateConferencesByTalkMoreZero(conferences,talkMoreZero);
        });

        //安排没有设定时间段的会话
        talkEqZeroList.stream().forEach(talkEqZero->{
            this.updateConferenceByTalkZero(conferences,talkEqZero);
        });

        //安排固定时间点或区间的会话
        talkFixList.stream().forEach(talkFix->{
            this.updateConferenceByFixTalk(conferences,talkFix);
        });
        return conferences;
    }


}

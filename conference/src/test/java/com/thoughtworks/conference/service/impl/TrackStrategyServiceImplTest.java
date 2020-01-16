package com.thoughtworks.conference.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.conference.model.Conference;
import com.thoughtworks.conference.model.Talk;
import com.thoughtworks.conference.service.ITrackStrategyService;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.*;

public class TrackStrategyServiceImplTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void updateConferenceAndTalk() {
        List<Talk> talkListInit = new ArrayList<>();
        Conference conferenceParam = new Conference(1,0,180, LocalTime.parse("09:00:00"),LocalTime.parse("09:00:00"),LocalTime.parse("12:00:00"),talkListInit);
        Talk talkParam = new Talk();
        talkParam.setTalkDuration(30);
        talkParam.setTalkContent("Lua for the Masses 30min");
        ITrackStrategyService trackStrategyService = new TrackStrategyServiceImpl();
        trackStrategyService.updateConferenceAndTalk(conferenceParam,talkParam);

        //assertThat(conferenceParam.getTalkList(), hasItem(talkParam));
        assertThat(conferenceParam.getLeftLimitMinute(),is(150));
        System.out.println(JSONObject.toJSONString(conferenceParam));
    }

}
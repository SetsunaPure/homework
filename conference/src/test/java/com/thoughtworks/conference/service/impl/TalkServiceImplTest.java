package com.thoughtworks.conference.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.conference.model.Conference;
import com.thoughtworks.conference.model.Talk;
import com.thoughtworks.conference.service.ITalkService;
import com.thoughtworks.conference.util.FileReadWrite;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TalkServiceImplTest {
    ITalkService talkService;
    List<String> lines;
    @Before
    public void setUp() throws Exception {
        talkService = new TalkServiceImpl();
        /*lines = new ArrayList<>();
        lines.add("Writing Fast Tests Against Enterprise Rails 60min");
        lines.add("Overdoing it in Python 45min");
        lines.add("Lua for the Masses 30min");
        lines.add("Ruby Errors from Mismatched Gem Versions 45min");
        lines.add("Common Ruby Errors 45min");
        lines.add("Rails for Python Developers lightning");
        lines.add("Communicating Over Distance 60min");
        lines.add("Accounting-Driven Development 45min");
        lines.add("Woah 30min");
        lines.add("Sit Down and Write 30min");
        lines.add("Pair Programming vs Noise 45min");
        lines.add("Rails Magic 60min");
        lines.add("Ruby on Rails: Why We Should Move On 60min");
        lines.add("Clojure Ate Scala (on my project) 45min");
        lines.add("Programming in the Boondocks of Seattle 30min");
        lines.add("Ruby vs. Clojure for Back-End Development 30min");
        lines.add("Ruby on Rails Legacy App Maintenance 60min");
        lines.add("A World Without HackerNews 30min");
        lines.add("User Interface CSS in Rails Apps 30min");*/
        lines = FileReadWrite.readTxtFile("test.txt"); //文件读取
    }


    @Test
    public void transTalk() {
        List<Talk> talkList = talkService.transTalk(lines);
        System.out.println(JSONArray.toJSONString(talkList));
    }


    @Test
    public void updateTalk() {
        Talk talkParam = new Talk();
        List<Talk> talkList = new ArrayList<Talk>();
        talkList.add(talkParam);
        Conference conferenceParam = new Conference(1,0,180, LocalTime.parse("09:00:00"),LocalTime.parse("09:00:00"),LocalTime.parse("12:00:00"),talkList);
        Talk talk = talkService.updateTalk(conferenceParam,talkParam);
        System.out.println(JSONObject.toJSONString(talk));
    }

    @Test
    public void sortTalkDuration() {
        List<Talk> talkList = talkService.transTalk(lines);
        talkService.sortTalkDuration(talkList);
        System.out.println(JSONArray.toJSONString(talkList));
    }
}
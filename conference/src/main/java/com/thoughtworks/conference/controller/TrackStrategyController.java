package com.thoughtworks.conference.controller;

import com.thoughtworks.conference.model.Conference;
import com.thoughtworks.conference.model.Talk;
import com.thoughtworks.conference.service.IConferenceService;
import com.thoughtworks.conference.service.ITalkService;
import com.thoughtworks.conference.service.ITrackStrategyService;
import com.thoughtworks.conference.service.impl.ConferenceServiceImpl;
import com.thoughtworks.conference.service.impl.TalkServiceImpl;
import com.thoughtworks.conference.service.impl.TrackStrategyServiceImpl;
import com.thoughtworks.conference.util.DateLimitConst;
import com.thoughtworks.conference.util.FileReadWrite;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: conference
 * @description: 追踪策略
 **/
public class TrackStrategyController {

    ITrackStrategyService  trackStrategyService = new TrackStrategyServiceImpl();
    IConferenceService conferenceService = new ConferenceServiceImpl();
    ITalkService talkService = new TalkServiceImpl();


    /**
     * 安排会话
     * 1.读取文件和添加特殊会话(Lunch, Networking Event)
     * 2.安排会议策略
     * 3.输出结果
     * @throws Exception
     */
    public void arrangeManageConference()  throws Exception {
        String fileName = "test.txt";
        List<Conference> conferences = new ArrayList<>();
        List<Talk> talkList =  this.getTransTalk(fileName);//文件内容转TalkList对象

        //talkService.sortTalkDuration(talkList);//排序按照会话时长大到小
        this.appendTalk(talkList);//添加两种特殊会话：Lunch,Networking Event

        trackStrategyService.arrangeConference(conferences,talkList);//安排会议

        this.outConferenceResult(conferences,fileName);//结果输出
    }

    /**
     * 读取文件
     * 文件内容转TalkList对象
     * @param fileName
     * @return
     * @throws IOException
     */
    private List<Talk> getTransTalk(String fileName) throws IOException {
        List<String> lines = FileReadWrite.readTxtFile(fileName); //文件读取
        List<Talk> talkList = talkService.transTalk(lines);//字符转换成会话对象
        return talkList;
    }

    /**
     * 添加两种特殊会话：Lunch,Networking Event
     * @param talkList
     */
    private void appendTalk(List<Talk> talkList){
        Talk talkLunch = new Talk(0,0,"Lunch",DateLimitConst.MORNING_END,0,LocalTime.parse(DateLimitConst.MORNING_END),LocalTime.parse(DateLimitConst.MORNING_END));
        talkList.add(talkLunch);//补充Lunch固定会话talk
        Talk talkNetworking = new Talk(0,0,"Networking Event",DateLimitConst.NETWORK_FIX,1,LocalTime.parse(DateLimitConst.NETWORK_FIX),LocalTime.parse(DateLimitConst.AFTERNOON_END));
        talkList.add(talkNetworking);//补充Networking Event固定会话talk
    }

    /**
     * 结果输出：打印控制台(方便查看)，输出到文本
     * @param conferences
     * @param fileName
     * @throws IOException
     */
    private void outConferenceResult(List<Conference> conferences,String fileName) throws IOException {
        conferenceService.conferenceResultPrintout(conferences);//打印会议
        List<String> linesOut = conferenceService.conferenceResultFileout(conferences);//对象转字符串
        FileReadWrite.writeTxtFile(linesOut,fileName); //文件写入输出
    }

}

package com.thoughtworks.conference.service;

import com.thoughtworks.conference.model.Conference;
import com.thoughtworks.conference.model.Talk;

import java.time.LocalTime;
import java.util.List;

/**
 * @program: conference
 * @description: 会议安排：将会话添加到会议中
 **/
public interface IConferenceService {

    /**
     * 获取会议类型
     * @param conferenceNumberInt
     * @return
     */
    public int getConferenceType(int conferenceNumberInt);

    /**
     * 根据会议类型：0:上午,1:下午；获取阶段会议时间最大值
     * eq:morning：180min;afternoon:240min;
     * @param conferenceType
     * @return
     */
    public int getMaxValue(int conferenceType);

    /**
     * 根据会议类型：0:上午,1:下午；获取阶段会议开始时间
     * @param conferenceType
     * @return
     */
    public LocalTime getConferenceStartTime(int conferenceType);

    /**
     * 根据会议类型：0:上午,1:下午；获取阶段会议结束时间
     * @param conferenceType
     * @return
     */
    public LocalTime getConferenceEndTime(int conferenceType);

    /**
     * 根据talk需要时长，判断是否有合适的会议conference
     * @param conference
     * @param talk
     * @return
     */
    public boolean isLeftMinuteTalk(Conference conference,Talk talk);

    /**
     * 获取conference剩余时间
     * @param conference
     * @param talk
     * @return
     */
    public int getLeftMinute(Conference conference,Talk talk);

    /**
     * 获取剩余会议开始时间
     * @param conference
     * @param talk
     * @return
     */
    public LocalTime getNextTalkTimeStart(Conference conference,Talk talk);


    /**
     * conference中的talkList添加talk
     * @param conference
     * @param talk
     * @return
     */
    public Conference addTalkNode(Conference conference,Talk talk);

    /**
     * 根据会话判断判断会议是否合适
     * @param conference
     * @param talk
     * @return
     */
    public boolean isExistTimeQuantunByTalk(Conference conference, Talk talk);

    /**
     * 获取固定会话的会议
     * @param conferences
     * @param talk
     * @return
     */
    public List<Conference> getConferenceByFixTalk(List<Conference> conferences, Talk talk);
    /**
     * 根据talk需要时长，获取合适的会议conference
     * @param conferences
     * @param talk
     * @return
     */
    public Conference getConference(List<Conference> conferences,Talk talk);

    /**
     * 根据talk需要时长，判断是否有足够剩余时间的会议Conference
     * @param conferences
     * @param talk
     * @return
     */
    public boolean isExistConference(List<Conference> conferences, Talk talk);



    /**
     * 添加会议到会议列表中去
     * @param conferences
     * @return
     */
    public List<Conference> addConference(List<Conference> conferences);

    /**
     * 打印输出会议列表
     * @param conferences
     */
    public void conferenceResultPrintout(List<Conference> conferences);

    /**
     * 对象转字符串
     * @param conferences
     * @return
     */
    public List<String> conferenceResultFileout(List<Conference> conferences);

}

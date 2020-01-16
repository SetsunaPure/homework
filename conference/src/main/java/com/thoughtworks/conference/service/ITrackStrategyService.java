package com.thoughtworks.conference.service;

import com.thoughtworks.conference.model.Conference;
import com.thoughtworks.conference.model.Talk;

import java.util.List;

/**
 * @program: conference
 * @description: 策略类：talk根据时间安排到conference中
 **/
public interface ITrackStrategyService {

    /**
     * 将特殊会话添加到conference中去
     * @param conference
     * @param talk
     */
    public void appendConference(Conference conference, Talk talk);

    /**
     * 1.conference添加talk
     * 2.更新conference的剩余时间
     * 3.更新conference的剩余开始时间
     * @param conference
     * @param talk
     */
    public void updateConferenceAndTalk(Conference conference,Talk talk);
    /**
     * 1.conference添加无会话时间的talk
     * eg:Rails for Python Developers lightning
     * 2.更新conference的剩余时间
     * 3.更新conference的剩余开始时间
     * @param conferences
     * @param talk
     * @return
     */
    public List<Conference> updateConferenceByTalkZero(List<Conference> conferences,Talk talk);

    /**
     * 1.conference添加会话时间大于0的talk
     * 2.更新conference的剩余时间
     * 3.更新conference的剩余开始时间
     * @param conferences
     * @param talk
     * @return
     */
    public List<Conference> updateConferencesByTalkMoreZero(List<Conference> conferences,Talk talk);

    /**
     * 会议conference中添加固定时间段的会话talk
     * @param conferences
     * @param talk
     * @return
     */
    public List<Conference> updateConferenceByFixTalk(List<Conference> conferences,Talk talk);

    /**
     * 将会话talk合理安排到会议conference每个时间点中去
     * @param conferences:会议列表
     * @param talks:绘画列表
     * @return
     */
    public List<Conference> arrangeConference(List<Conference> conferences, List<Talk> talks);
}

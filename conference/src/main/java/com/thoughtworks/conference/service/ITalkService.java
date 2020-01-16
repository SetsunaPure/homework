package com.thoughtworks.conference.service;

import com.thoughtworks.conference.model.Conference;
import com.thoughtworks.conference.model.Talk;

import java.util.List;


/**
 * @program: conference
 * @description: 会话对象
 **/
public interface ITalkService {

    /**
     * 会话列表值校验
     * @param talks
     */
    public void checkTalkDuration(List<Talk> talks);
    /**
     * 会话列表中添加会话
     * @param talkList
     * @param talk
     */
    public void addTalk(List<Talk> talkList,Talk talk);
    /**
     * 将文件转成会话对象
     * @param lines
     * @return
     */
    public List<Talk> transTalk(List<String> lines);

    /**
     * 设置固定会话开始时间
     * @param conference
     * @param talk
     * @return
     */
    public Talk updateFixTalk(Conference conference, Talk talk);

    /**
     * 设置会话开始时间
     * @param conference
     * @param talk
     * @return
     */
    public Talk updateTalk(Conference conference, Talk talk);

    /**
     * 会话按照talkDuration大到小倒排序
     * @param talks
     */
    public List<Talk> sortTalkDuration(List<Talk> talks);
}

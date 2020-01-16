package com.thoughtworks.conference.util;

public class Sequence {

    private static int sn = 0;

    /**
     * 获取自增长序列
     * static防止重复获取
     * @return
     */
    public static int getNextSN() {
        return ++sn;
    }

    /**
     * 获取当前序列
     * @return
     */
    public int getCurrentSN() {
        return sn;
    }
}

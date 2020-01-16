package com.thoughtworks.conference.service.impl;

import com.thoughtworks.conference.service.IConferenceService;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ConferenceServiceImplTest {

    IConferenceService conferenceService;
    @Before
    public void setUp() throws Exception {
        conferenceService = new ConferenceServiceImpl();
    }

    @Test
    public void getConferenceType() {
        int conferenceType = conferenceService.getConferenceType(1);
        assertEquals(1,conferenceType);
    }


    @Test
    public void getConferenceStartTime() {
        LocalTime startTimeResult = conferenceService.getConferenceStartTime(1);
        assertThat(startTimeResult,is(LocalTime.parse("13:00:00")));
    }

}
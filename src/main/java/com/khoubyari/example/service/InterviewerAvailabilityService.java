package com.khoubyari.example.service;

import com.google.common.collect.Lists;
import com.khoubyari.example.dao.jpa.InterviewSlotRepository;
import com.khoubyari.example.dao.jpa.InterviewerRepository;
import com.khoubyari.example.domain.InterviewSlot;
import com.khoubyari.example.domain.Interviewer;
import com.khoubyari.example.domain.InterviewerAvailabilityRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/*
 * Sample service to demonstrate what the API would use to get things done
 */
@Service
public class InterviewerAvailabilityService {

    private static final Logger log = LoggerFactory.getLogger(InterviewerAvailabilityService.class);

    @Autowired
    private InterviewerRepository interviewerRepository;

    @Autowired
    private InterviewSlotRepository interviewSlotRepository;

    @Autowired
    CounterService counterService;

    @Autowired
    GaugeService gaugeService;

    public InterviewerAvailabilityService() {
    }

    public List<InterviewSlot> createInterviewSlots(InterviewerAvailabilityRequest interviewerAvailabilityRequest) {
        Interviewer interviewer = interviewerRepository.findOne(interviewerAvailabilityRequest.getInterviewerId());
        Date start = interviewerAvailabilityRequest.getStart();
        Date nearestHour = DateUtils.round(start, Calendar.HOUR);
        List<InterviewSlot> interviewSlots = Lists.newArrayList();
        Date slot = nearestHour;
        while (DateUtils.addHours(slot, 1).before(interviewerAvailabilityRequest.getEnd())) {
            interviewSlots.add(new InterviewSlot(slot, false, interviewer));
            slot = DateUtils.addHours(slot, 1);
        }
        return Lists.newArrayList(interviewSlotRepository.save(interviewSlots));
    }

    public Interviewer getInterviewer(long id) {
        return interviewerRepository.findOne(id);
    }

    public void updateInterviewer(Interviewer interviewer) {
        interviewerRepository.save(interviewer);
    }

    public void deleteInterviewer(Long id) {
        interviewerRepository.delete(id);
    }

    //http://goo.gl/7fxvVf
    public Page<InterviewSlot> getAllInterviewers(Integer page, Integer size) {
        Page pageOfInterviewers = interviewSlotRepository.findAll(new PageRequest(page, size));
        // example of adding to the /metrics
        if (size > 50) {
            counterService.increment("Khoubyari.InterviewerService.getAll.largePayload");
        }
        return pageOfInterviewers;
    }
}

package com.khoubyari.example.service;

import com.khoubyari.example.dao.jpa.InterviewerRepository;
import com.khoubyari.example.domain.Interviewer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/*
 * Sample service to demonstrate what the API would use to get things done
 */
@Service
public class InterviewerService {

    private static final Logger log = LoggerFactory.getLogger(InterviewerService.class);

    @Autowired
    private InterviewerRepository interviewerRepository;

    @Autowired
    CounterService counterService;

    @Autowired
    GaugeService gaugeService;

    public InterviewerService() {
    }

    public Interviewer createInterviewer(Interviewer interviewer) {
        return interviewerRepository.save(interviewer);
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
    public Page<Interviewer> getAllInterviewers(Integer page, Integer size) {
        Page pageOfInterviewers = interviewerRepository.findAll(new PageRequest(page, size));
        // example of adding to the /metrics
        if (size > 50) {
            counterService.increment("Khoubyari.InterviewerService.getAll.largePayload");
        }
        return pageOfInterviewers;
    }
}

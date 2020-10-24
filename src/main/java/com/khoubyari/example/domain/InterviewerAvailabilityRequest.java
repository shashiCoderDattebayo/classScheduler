package com.khoubyari.example.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class InterviewerAvailabilityRequest {
    private long interviewerId;



    private Date start;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone="Asia/Calcutta")
    private Date end;

    public InterviewerAvailabilityRequest(long interviewerId, Date start, Date end) {
        this.interviewerId = interviewerId;
        this.start = start;
        this.end = end;
    }

    public InterviewerAvailabilityRequest() {
    }

    public long getInterviewerId() {
        return interviewerId;
    }

    public void setInterviewerId(long interviewerId) {
        this.interviewerId = interviewerId;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}

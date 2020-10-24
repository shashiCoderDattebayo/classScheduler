package com.khoubyari.example.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.Date;

/*
 * a simple domain entity doubling as a DTO
 */
@Entity
@Table(name = "interview_slots")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class InterviewSlot implements Comparable<InterviewSlot> {

    @Id
    @GeneratedValue()
    private long id;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone="Asia/Calcutta")
    private Date slot;

    @Column(nullable = false)
    private boolean reserved;

    @NotNull
    @ManyToOne
    private Interviewer interviewer;

    public InterviewSlot() {
    }

    public InterviewSlot(Date slot, boolean reserved, Interviewer interviewer) {
        this.slot = slot;
        this.reserved = reserved;
        this.interviewer = interviewer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getSlot() {
        return slot;
    }

    public void setSlot(Date slot) {
        this.slot = slot;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public Interviewer getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(Interviewer interviewer) {
        this.interviewer = interviewer;
    }

    @Override
    public String toString() {
        return "InterviewSlots{" +
                "id=" + id +
                ", slot=" + slot +
                ", reserved=" + reserved +
                '}';
    }

    @Override
    public int compareTo(InterviewSlot o) {
        return this.getSlot().compareTo(o.getSlot());
    }
}

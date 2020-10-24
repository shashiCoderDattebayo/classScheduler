package com.khoubyari.example.domain;

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

/*
 * a simple domain entity doubling as a DTO
 */
@Entity
@Table(name = "interviews")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Interview {

    @Id
    @GeneratedValue()
    private long id;

    @NotNull
    @ManyToOne
    private Student student;

    @NotNull
    @ManyToOne
    private Interviewer interviewer;

    @Column(nullable = false)
    private long duration;

    public Interview() {
    }

    public Interview(Student student, Interviewer interviewer) {
        this.student = student;
        this.interviewer = interviewer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Interviewer getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(Interviewer interviewer) {
        this.interviewer = interviewer;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Interview{" +
                "id=" + id +
                ", student=" + student.toString() +
                ", interviewer=" + interviewer.toString() +
                ", duration=" + duration +
                '}';
    }
}

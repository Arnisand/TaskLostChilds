package com.example.playful.tasklostchilds.Child;

import com.example.playful.tasklostchilds.Child.Enums.Gender;
import com.example.playful.tasklostchilds.Child.Enums.SearchStatus;
import com.google.gson.annotations.Expose;

import java.util.Date;

public class Child {

    @Expose
    private long id;
    @Expose
    private String name;
    @Expose
    private SearchStatus status;
    @Expose
    private Date dateOfBirth;
    @Expose
    private Gender gender;
    @Expose
    private String childDescription;
    @Expose
    private String region;
    @Expose
    private String situationDescription;
    @Expose
    private String timeOfLoss;
    @Expose
    private String timeOfRequest;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SearchStatus getStatus() {
        return status;
    }

    public void setStatus(SearchStatus status) {
        this.status = status;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
//        Date dNow = new Date( );
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getChildDescription() {
        return childDescription;
    }

    public void setChildDescription(String childDescription) {
        this.childDescription = childDescription;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSituationDescription() {
        return situationDescription;
    }

    public void setSituationDescription(String situationDescription) {
        this.situationDescription = situationDescription;
    }

    public String getTimeOfLoss() {
        return timeOfLoss;
    }

    public void setTimeOfLoss(String timeOfLoss) {
        this.timeOfLoss = timeOfLoss;
    }

    public String getTimeOfRequest() {
        return timeOfRequest;
    }

    public void setTimeOfRequest(String timeOfRequest) {
        this.timeOfRequest = timeOfRequest;
    }
}

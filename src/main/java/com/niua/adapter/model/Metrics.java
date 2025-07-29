package com.niua.adapter.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

//@Getter
//@Setter
public class Metrics {
    private int assessments;
    private int todaysTotalApplications;
    private int todaysClosedApplications;
    private int noOfPropertiesPaidToday;

    private int todaysApprovedApplications;
    private int todaysApprovedApplicationsWithinSLA;
    private int pendingApplicationsBeyondTimeline;
    private int avgDaysForApplicationApproval;
    private int StipulatedDays;

    private List<GroupedData> todaysMovedApplications;
    private List<GroupedData> propertiesRegistered;
    private List<GroupedData> assessedProperties;
    private List<GroupedData> transactions;
    private List<GroupedData> todaysCollection;

    public void setAssessments(int assessments) {
        this.assessments = assessments;
    }

    public void setTodaysTotalApplications(int todaysTotalApplications) {
        this.todaysTotalApplications = todaysTotalApplications;
    }

    public void setTodaysClosedApplications(int todaysClosedApplications) {
        this.todaysClosedApplications = todaysClosedApplications;
    }

    public void setNoOfPropertiesPaidToday(int noOfPropertiesPaidToday) {
        this.noOfPropertiesPaidToday = noOfPropertiesPaidToday;
    }

    public void setTodaysApprovedApplications(int todaysApprovedApplications) {
        this.todaysApprovedApplications = todaysApprovedApplications;
    }

    public void setTodaysApprovedApplicationsWithinSLA(int todaysApprovedApplicationsWithinSLA) {
        this.todaysApprovedApplicationsWithinSLA = todaysApprovedApplicationsWithinSLA;
    }

    public void setPendingApplicationsBeyondTimeline(int pendingApplicationsBeyondTimeline) {
        this.pendingApplicationsBeyondTimeline = pendingApplicationsBeyondTimeline;
    }

    public void setAvgDaysForApplicationApproval(int avgDaysForApplicationApproval) {
        this.avgDaysForApplicationApproval = avgDaysForApplicationApproval;
    }

    public void setStipulatedDays(int stipulatedDays) {
        StipulatedDays = stipulatedDays;
    }

    public void setTodaysMovedApplications(List<GroupedData> todaysMovedApplications) {
        this.todaysMovedApplications = todaysMovedApplications;
    }

    public void setPropertiesRegistered(List<GroupedData> propertiesRegistered) {
        this.propertiesRegistered = propertiesRegistered;
    }

    public void setAssessedProperties(List<GroupedData> assessedProperties) {
        this.assessedProperties = assessedProperties;
    }

    public void setTransactions(List<GroupedData> transactions) {
        this.transactions = transactions;
    }

    public void setTodaysCollection(List<GroupedData> todaysCollection) {
        this.todaysCollection = todaysCollection;
    }

    public void setPropertyTax(List<GroupedData> propertyTax) {
        this.propertyTax = propertyTax;
    }

    public void setCess(List<GroupedData> cess) {
        this.cess = cess;
    }

    public void setRebate(List<GroupedData> rebate) {
        this.rebate = rebate;
    }

    public void setPenalty(List<GroupedData> penalty) {
        this.penalty = penalty;
    }

    public void setInterest(List<GroupedData> interest) {
        this.interest = interest;
    }

    public Metrics(int assessments, int todaysTotalApplications, int todaysClosedApplications, int noOfPropertiesPaidToday, int todaysApprovedApplications, int todaysApprovedApplicationsWithinSLA, int pendingApplicationsBeyondTimeline, int avgDaysForApplicationApproval, int stipulatedDays, List<GroupedData> todaysMovedApplications, List<GroupedData> propertiesRegistered, List<GroupedData> assessedProperties, List<GroupedData> transactions, List<GroupedData> todaysCollection, List<GroupedData> propertyTax, List<GroupedData> cess, List<GroupedData> rebate, List<GroupedData> penalty, List<GroupedData> interest) {
        this.assessments = assessments;
        this.todaysTotalApplications = todaysTotalApplications;
        this.todaysClosedApplications = todaysClosedApplications;
        this.noOfPropertiesPaidToday = noOfPropertiesPaidToday;
        this.todaysApprovedApplications = todaysApprovedApplications;
        this.todaysApprovedApplicationsWithinSLA = todaysApprovedApplicationsWithinSLA;
        this.pendingApplicationsBeyondTimeline = pendingApplicationsBeyondTimeline;
        this.avgDaysForApplicationApproval = avgDaysForApplicationApproval;
        StipulatedDays = stipulatedDays;
        this.todaysMovedApplications = todaysMovedApplications;
        this.propertiesRegistered = propertiesRegistered;
        this.assessedProperties = assessedProperties;
        this.transactions = transactions;
        this.todaysCollection = todaysCollection;
        this.propertyTax = propertyTax;
        this.cess = cess;
        this.rebate = rebate;
        this.penalty = penalty;
        this.interest = interest;
    }

    public Metrics() {

    }

    public int getAssessments() {
        return assessments;
    }

    public int getTodaysTotalApplications() {
        return todaysTotalApplications;
    }

    public int getTodaysClosedApplications() {
        return todaysClosedApplications;
    }

    public int getNoOfPropertiesPaidToday() {
        return noOfPropertiesPaidToday;
    }

    public int getTodaysApprovedApplications() {
        return todaysApprovedApplications;
    }

    public int getTodaysApprovedApplicationsWithinSLA() {
        return todaysApprovedApplicationsWithinSLA;
    }

    public int getPendingApplicationsBeyondTimeline() {
        return pendingApplicationsBeyondTimeline;
    }

    public int getAvgDaysForApplicationApproval() {
        return avgDaysForApplicationApproval;
    }

    public int getStipulatedDays() {
        return StipulatedDays;
    }

    public List<GroupedData> getTodaysMovedApplications() {
        return todaysMovedApplications;
    }

    public List<GroupedData> getPropertiesRegistered() {
        return propertiesRegistered;
    }

    public List<GroupedData> getAssessedProperties() {
        return assessedProperties;
    }

    public List<GroupedData> getTransactions() {
        return transactions;
    }

    public List<GroupedData> getTodaysCollection() {
        return todaysCollection;
    }

    public List<GroupedData> getPropertyTax() {
        return propertyTax;
    }

    public List<GroupedData> getCess() {
        return cess;
    }

    public List<GroupedData> getRebate() {
        return rebate;
    }

    public List<GroupedData> getPenalty() {
        return penalty;
    }

    public List<GroupedData> getInterest() {
        return interest;
    }

    private List<GroupedData> propertyTax;
    private List<GroupedData> cess;
    private List<GroupedData> rebate;
    private List<GroupedData> penalty;
    private List<GroupedData> interest;
}

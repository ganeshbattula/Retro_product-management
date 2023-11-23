package com.srum.ceremony.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class Retrospective {
	
	private String name; 
    private String summary;
    private LocalDate date;
    private List<String> participants;
    private List<FeedbackItemModel> feedbackItems = new ArrayList<>();
    
    FeedbackItemModel feedbackItem = new FeedbackItemModel();
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public List<String> getParticipants() {
		return participants;
	}
	public void setParticipants(List<String> participants) {
		this.participants = participants;
	}
	public List<FeedbackItemModel> getFeedbackItems() {
		return feedbackItems;
	}
	public void setFeedbackItems(List<FeedbackItemModel> feedbackItems) {
		this.feedbackItems = feedbackItems;
	}
	
	
	@Override
	public String toString() {
		return "Retrospective [name=" + name + ", summary=" + summary + ", date=" + date + ", participants="
				+ participants + ", feedbackItems=" + feedbackItems + "]";
	}
	

}

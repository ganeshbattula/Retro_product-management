package com.srum.ceremony.demo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class FeedbackItemModel {
	
	private String name; 
    private String body;
    private String feedbackType;
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getFeedbackType() {
		return feedbackType;
	}
	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}
	
	
	@Override
	public String toString() {
		return "FeedbackItemModel [name=" + name + ", body=" + body + ", feedbackType=" + feedbackType + "]";
	}
    
    

}

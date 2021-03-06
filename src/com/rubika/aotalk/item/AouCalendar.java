package com.rubika.aotalk.item;

public class AouCalendar {
	private String title;
	private String desc;
	private String time;
	private String topic;
	
	public AouCalendar(String title, String desc, String time, String topic) {
		this.title = title;
		this.desc = desc;
		this.time = time;
		this.topic = topic;
	}

	public String getTopic() {
		return topic;
	}
	
    public String getLabel() {
        return title;
    }
    
    public String getDescription() {
    	return desc;
    }
    
    public String getTime() {
    	return time;
    }
    
    @Override public String toString() {
        return desc;
    }
}

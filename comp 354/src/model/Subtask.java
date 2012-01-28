package model;

import java.util.ArrayList;

public class Subtask {
	private String identifier;
	private String parentid;
	private String priority;
	private String title;
	private String description;
	private String duration;
	private ArrayList peopleassigned = new ArrayList();

	
	public String getParentID() {
		return parentid;
	}
	public void setParentID(String parentid) {
		this.parentid = parentid;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDuration(){
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority){
		this.priority=priority;
	}
	public ArrayList getPeopleassigned(){
		return peopleassigned;
	}
	public void setPeopleassigned(String peopleassigned) {
		this.peopleassigned.add(peopleassigned);
	}



	@Override
	public String toString() {
		return "subtask [identifier=" + identifier + ", parentid=" + parentid + ", title=" + title + ", description="
				+ description + ", duration=" + duration + ", people assigned=" + peopleassigned + ", priority=" + priority + "]";
	}
}

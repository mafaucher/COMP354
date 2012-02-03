package model;

import java.util.ArrayList;

public class Task {
        private String identifier;
        private String title;
        private String description;
        private String duration;
        private String deliverable;
        private String deadline;
        private String completion;
    	private ArrayList<String> peopleassigned = new ArrayList<String>();
        
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
        public String getDelivarable(){
                return deliverable;
        }
        public void setDelivarable(String deliverable){
                this.deliverable=deliverable;
        }
        public String getDeadline() {
                return deadline;
        }
        public void setDeadline(String deadline){
                this.deadline=deadline;
        }
        public String getCompletion(){
                return completion;
        }
        public void setCompletion(String completion){
                this.completion=completion;
        }
    	public ArrayList<String> getPeopleassigned(){
    		return peopleassigned;
    	}
    	public void setPeopleassigned(String peopleassigned) {
    		this.peopleassigned.add(peopleassigned);
    	}



        @Override
        public String toString() {
                return "task [identifier=" + identifier + ", title=" + title + ", description="
                                + description + ", duration=" + duration + ", deliverable=" + deliverable + ", people assigned=" + peopleassigned + ", deadline=" + deadline + ", completion=" + completion + "]";
        }
}
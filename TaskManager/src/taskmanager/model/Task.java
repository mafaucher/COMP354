package taskmanager.model;

import java.util.ArrayList;

public class Task {
        private String identifier;
        private String title;
        private String description;
        private String duration;
        private String deliverable;
        private String startDate;
        private String deadline;
        private String completion;
    	private ArrayList<String> peopleassigned = new ArrayList<String>();

        public Task() {
            identifier = "";
            title = "-";
            description = "-";
            duration = "0";
            deliverable = "-";
            startDate = "-";
            deadline = "-";
            completion = "0";
            peopleassigned = new ArrayList<String>();
        }
        public Task(String id) {
            identifier = id;
            title = "-";
            description = "-";
            duration = "0";
            deliverable = "-";
            startDate = "-";
            deadline = "-";
            completion = "0";
            peopleassigned = new ArrayList<String>();
        }

        public String getIdentifier() {
                return identifier;
        }
        
        public boolean setIdentifier(String identifier) {
                if (!identifier.matches(XMLParser.PATTERN_IDENTIFIER)) return false;
                this.identifier = identifier;
                return true;
        }
        public String getTitle() {
                return title;
        }
        public boolean setTitle(String title) {
                if (!title.matches(XMLParser.PATTERN_TITLE)) return false;
                this.title = title;
                return true;
        }
        public String getDescription() {
                return description;
        }
        public boolean setDescription(String description) {
                if (!description.matches(XMLParser.PATTERN_DESCRIPTION)) return false;
                this.description = description;
                return true;
        }
        public String getDuration(){
                return duration;
        }
        public boolean setDuration(String duration) {
                if (!duration.matches(XMLParser.PATTERN_DURATION)) return false;
                this.duration = duration;
                return true;
        }
        public String getDeliverable(){
                return deliverable;
        }
        public boolean setDeliverable(String deliverable){
                this.deliverable=deliverable;
                return true;
        }
        
        public String getStartDate()
        {
            return startDate;
        }
        
        public boolean setStartDate(String startDate)
        {
            this.startDate = startDate;
            return true;
        }
        
        public String getDeadline() {
                return deadline;
        }
        public boolean setDeadline(String deadline){
                this.deadline=deadline;
                return true;
        }
        public String getCompletion(){
                return completion;
        }
        public boolean setCompletion(String completion){
                this.completion=completion;
                return true;
        }
    	public ArrayList<String> getPeopleassigned(){
    		return peopleassigned;
        }

    	public void setPeopleassigned(String peopleassigned) {
    		if(!this.peopleassigned.contains(peopleassigned)){
    			this.peopleassigned.add(peopleassigned);
    		}
    	}

        public void setPeopleassigned(ArrayList<String> peopleassigned)
        {
            this.peopleassigned = peopleassigned;
        }

        public String getPeopleassignedAsString() {

            //Use a string builder to be efficient with Memory
            StringBuilder peopleAssigned = new StringBuilder();
            ArrayList<String> alPID = getPeopleassigned();

            //work here to gather people assigned to a project
            for (int j = 0; j < alPID.size(); j++)
            {
                peopleAssigned.append(alPID.get(j));

                if (j != alPID.size() - 1)
                    peopleAssigned.append(", ");
            }
            return peopleAssigned.toString();
        }

        @Override
        public String toString() {
                return "task [identifier=" + identifier + ", title=" + title + ", description="
                                + description + ", duration=" + duration + ", deliverable=" + deliverable + ", people assigned=" + peopleassigned + ", deadline=" + deadline + ", completion=" + completion + "]";
        }
}
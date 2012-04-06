package taskmanager.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Task {
        private String identifier;
        private String title;
        private String description;
        private String duration;
        private String deliverable;
        private String startDate;
        private String deadline;
        private String completion;
        private String parentDependencyId;
    	private ArrayList<String> peopleassigned = new ArrayList<String>();

        public Task() {
            identifier = "";
            initialize();
        }
        public Task(String id) {
            identifier = id;
            initialize();
        }
        
        private void initialize()
        {
            title = "-";
            description = "-";
            duration = "0";
            deliverable = "-";
            startDate = getDateToday();
            deadline = getDateTomorrow();
            completion = "0";
            parentDependencyId = "";
            peopleassigned = new ArrayList<String>();
        }
        
        private String getDateToday()
        {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(XMLParser.FORMAT_DATE);
            return sdf.format(cal.getTime());
        }
        
        private String getDateTomorrow()
        {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(XMLParser.FORMAT_DATE);
            
            try 
            {
                c.setTime(sdf.parse(getDateToday()));
            } 
            catch (ParseException ex) 
            {
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            c.add(Calendar.DATE, 1);
            return sdf.format(c.getTime());

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
            SimpleDateFormat sdf = new SimpleDateFormat(XMLParser.FORMAT_DATE);	

	    try
	    {
	      sdf.parse(startDate);
	    }
	    catch (ParseException e)
	    {
	      System.err.println(e.getStackTrace());
	      return false;
	    }
            
            this.startDate = startDate;
            return true;
        }
        
        public String getDeadline() {
                return deadline;
        }
        public boolean setDeadline(String deadline){
            SimpleDateFormat sdf = new SimpleDateFormat(XMLParser.FORMAT_DATE);	

	    try
	    {
	      sdf.parse(deadline);
	    }
	    catch (ParseException e)
	    {
	      System.err.println(e.getStackTrace());
	      return false;
	    }
            
            this.deadline = deadline;
            return true;
        }
        public String getCompletion(){
                return completion;
        }
        public boolean setCompletion(String completion){
                this.completion=completion;
                return true;
        }
        
        public String getParentDependencyId()
        {
            return this.parentDependencyId;
        }
        
        public boolean setParentDependencyId(String id)
        {
            if (id.matches(XMLParser.PATTERN_PARENT_ID) && !id.equals(this.identifier))
            {
                this.parentDependencyId = id;
                return true;
            }
            return false;
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
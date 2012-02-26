package taskmanager.model;

import java.util.ArrayList;

public class Person {
	double TOTALHOURS = 0;
	ArrayList<String> PROJECTS = new ArrayList<String>();
	
	private String identifier;
	private String fname;
	private String lname;
	private String jobtitle;
	private String jobdescription;
	private String clearance;
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getFName() {
		return fname;
	}
	public void setFName(String fname) {
		this.fname = fname;
	}
	public String getLName() {
		return lname;
	}
	public void setLName(String lname) {
		this.lname = lname;
	}
	public String getJobTitle() {
		return jobtitle;
	}
	public void setJobTitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
	public String getJobDescription() {
		return jobdescription;
	}
	public void setJobDescription(String jobdesc) {
		this.jobdescription = jobdesc;
	}
	public String getClearance() {
		return clearance;
	}
	public void setClearance(String clearance) {
		this.clearance = clearance;
	}
	public double getTotalHours(){
		return TOTALHOURS;
	}
	public void setTotalHours(double duration) {
		this.TOTALHOURS += duration;
	}
	public ArrayList<String> getProjects() {
		return PROJECTS;
	}
	public void setProjects(String project){
		this.PROJECTS.add(project);
	}

	@Override
	public String toString() {
		return "person [identifier=" + identifier + ", fname=" + fname + ", lname=" + lname + ", jobtitle="
				+ jobtitle + ", jobdescription=" + jobdescription + ", clearance=" + clearance + "]";
	}
}

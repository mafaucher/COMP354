package model;

import java.util.ArrayList;

public class Person {
	String NAME="";
	double TOTALHOURS = 0;
	ArrayList PROJECTS = new ArrayList();
	
	public String getName() {
		return NAME;
	}
	public void setName(String name) {
		this.NAME = name;
	}
	public double getTotalHours(){
		return TOTALHOURS;
	}
	public void setTotalHours(double duration) {
		this.TOTALHOURS += duration;
	}

	public ArrayList getProjects() {
		return PROJECTS;
	}
	public void setProjects(String project){
		this.PROJECTS.add(project);
	}

}

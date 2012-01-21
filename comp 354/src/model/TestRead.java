package model;

import java.util.List;

import model.task;

public class TestRead {
	public static void main(String args[]) {
		StaXParser read = new StaXParser();
		List<task> readConfig = read.readConfig("test.xml");
		for (task task : readConfig) {
			System.out.println(task);
		}
	}
}
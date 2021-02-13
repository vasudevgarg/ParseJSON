package com.vdg.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Source {

	public static List<Venue> venueList= new ArrayList<>();
	public static void main(String[] args) throws IOException {
		
		HandlingJSON hJson=new HandlingJSON();
		hJson.parseJSON(venueList);
		
		if(venueList==null||venueList.size()==0) {
			System.out.println("No Content to display\nExiting...");
			return;
		}
		
		
		UserInput ui= new UserInput();
		ui.getUserInput(venueList);
	}
}

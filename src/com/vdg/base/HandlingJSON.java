package com.vdg.base;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HandlingJSON {
	
	public void parseJSON(List<Venue> venueList) {

		String jsonString;
		
		System.out.println("Parsing JSON response");
		
		try {
			jsonString = IOUtils.toString(new URL("https://api.foursquare.com/v2/venues/search?near=Bangalore&oauth_token=SQ1PQIHAYYDTHK0VJAYUTWM1CJN0XDILXSJG5KOPI045MC1T&v=20160123&limit=50"), Charset.forName("UTF-8"));
			JSONObject json =(JSONObject) new JSONParser().parse(jsonString);
			
			//System.out.println(jsonString);
		
			JSONObject response = (JSONObject) json.get("response"); 
			if(response==null)
				return;
			
			JSONArray jarray =	(JSONArray) response.get("venues");
			
			//venueList = new ArrayList<>();
			
			if(jarray!=null) {
				Iterator<JSONObject> iterator = jarray.iterator();
				while (iterator.hasNext()) {
					//System.out.println(iterator.next());
					
					JSONObject jobject=iterator.next();
					
					String name=(String) jobject.get("name");
					boolean verified=(boolean) jobject.get("verified");
					
					JSONObject stats=(JSONObject) jobject.get("stats");
					long tipCount=(long) stats.get("tipCount");
					long usersCount=(long) stats.get("usersCount");
					long checkinsCount=(long) stats.get("checkinsCount");
					
					JSONArray categoryArray =	(JSONArray) jobject.get("categories");
					Iterator<JSONObject> category_iterator = categoryArray.iterator();
					List<String> category_name=new ArrayList<>();
					while(category_iterator.hasNext()) {
						
						//System.out.println(category_iterator.next());
						
						JSONObject jcategory=category_iterator.next();
						category_name.add((String)jcategory.get("name"));						
					}
					
					String[] categoryStrings=category_name.toArray(new String[category_name.size()]);
					Venue v=new Venue(name, categoryStrings, verified, checkinsCount, usersCount, tipCount);
					venueList.add(v);
				}		
			}
			//else {
				//System.out.println("jarray is null");
			//}			
		} catch (MalformedURLException e) {
			// not commenting exception to show exception in case change is made in code/json format
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//System.out.println("size of list="+venueList.size());
		System.out.println("Parsing completed\n");
	}
}

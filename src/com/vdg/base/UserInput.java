package com.vdg.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class UserInput {

	public void display(List<Venue> vList) {
		
		if(vList==null||vList.size()==0) {
			System.out.println("No Content to display\n");
			return;
		}
		
		for(Venue v:vList) {
			System.out.println("-------------------------------------------------------------------------------------------------------------------");
			System.out.println("name: "+v.getName());
			System.out.print("category_name: ");
			String[] strings=v.getCategory_name();
			for(int i=0;i<strings.length;i++) {
				System.out.print(strings[i]);
				if(i!=strings.length-1) {
					System.out.print(",");
				}
				else {
					System.out.println();
				}
			}
			
			System.out.println("verified: "+v.isVerified());
			System.out.println("stats: (checkinsCount:"+v.getCheckinCount()+" userCount:"+v.getUsersCount()+" tipCount:"+v.getTipCount()+")");
		}
		System.out.println("-------------------------------------------------------------------------------------------------------------------\n");
	}
	
	public void getUserInput(List<Venue> venueList) {
		while(true) {
			System.out.println("Press 1 to display all the content");
			System.out.println("Press 2 to apply filter");
			System.out.println("Press 9 to exit");
			
			System.out.print("Enter input:");
			
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			int input;
			try {
				input = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e1) {
				System.out.println("Enter value is not a number\n");
				continue;
				//e1.printStackTrace();
			} catch (IOException e1) {
				System.out.println("Could not take the input\n");
				continue;
			}
			
			if(input==1) {
				display(venueList);
			}
			else if(input==2) {
				String customName=null;
				boolean filterVerifier=false;
				boolean customVerified=false;
				int customSorting=-1;
				
				while(true) {
					System.out.println("Want to apply filter on name(y/n):");
					String cString;
					try {
						cString = br.readLine();
					} catch (IOException e1) {
						System.out.println("Could not take the input\n");
						continue;
					}
					if(cString.equals("y")==true) {
						System.out.print("Enter string:");
						try {
							customName=br.readLine();
						} catch (IOException e1) {
							System.out.println("Could not take the input\n");
							continue;
						}
						break;
					}
					else if(cString.equals("n")==true) {
						break;
					}
					else {
						System.out.println("Invalid input\n");
					}
				}
				
				while(true) {
					System.out.println("Want to apply filter on verified parameter(y/n):");
					String cString;
					try {
						cString = br.readLine();
					} catch (IOException e1) {
						System.out.println("Could not take the input\n");
						continue;
					}
					if(cString.equals("y")==true) {
						while(true) {
							System.out.print("Enter (true/false):");
							String tempString;
							try {
								tempString = br.readLine();
							} catch (IOException e1) {
								System.out.println("Could not take the input\n");
								continue;
							}
							if(tempString.equals("true")) {
								filterVerifier=true;
								customVerified=true;
								break;
							}
							else if(tempString.equals("false")) {
								filterVerifier=true;
								customVerified=false;
								break;
							}
							else {
								System.out.println("Invalid Input\n");
							}
						}
						if(filterVerifier==true)
							break;
					}
					else if(cString.equals("n")==true) {
						break;
					}
					else {
						System.out.println("Invalid input\n");
					}
				}
				
				while(true) {
					System.out.println("Want to apply filter on sorting stats(y/n):");
					String cString;
					try {
						cString = br.readLine();
					} catch (IOException e1) {
						System.out.println("Could not take the input\n");
						continue;
					}
					if(cString.equals("y")==true) {
						while(true) {
							System.out.println("Press 4 to apply filter on checkinsCount");
							System.out.println("Press 5 to apply filter on usersCount");
							System.out.println("Press 6 to apply filter on tipCount");
							
							int filterOnSorting;
							try {
								filterOnSorting = Integer.parseInt(br.readLine());
							} catch (NumberFormatException e1) {
								System.out.println("Enter value is not a number\n");
								continue;
								//e1.printStackTrace();
							} catch (IOException e1) {
								System.out.println("Could not take the input\n");
								continue;
							}
							
							if(filterOnSorting<4||filterOnSorting>6) {
								System.out.println("Invalid Input\n");
								continue;
							}
							customSorting=filterOnSorting-4;
							break;
						}
						break;						
					}
					else if(cString.equals("n")==true) {
						break;
					}
					else {
						System.out.println("Invalid input\n");
					}
				}
				
				
				//System.out.println(customName+" "+filterVerifier+" "+customVerified+" "+customSorting);
				System.out.println("\nDisplaying result based on filter applied:");
				
				List<Venue> customList=applyFilter(venueList,customName, filterVerifier, customVerified, customSorting);
				display(customList);
				
				
			}
			else if(input==9) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
			else {
				System.out.println("Invalid Input");
				System.out.println("\n");
			}
		}
	}

	private List<Venue> applyFilter(List<Venue> venueList, String customName, boolean filterVerifier, boolean customVerified,
			int customSorting) {
		
		List<Venue> customListAfterFilter= new ArrayList<Venue>();
		
		if(customName!=null) {
			
		}
		
		for(Venue v:venueList) {
			if(customName!=null) {
				if(v.getName().contains(customName)==false)
					continue;
			}
			
			if(filterVerifier==true) {
				if(customVerified!=v.isVerified())
					continue;
			}
			
			customListAfterFilter.add(v);
		}
		
		if(customSorting==0) {
			Collections.sort(customListAfterFilter, 
					(a,b)->{
						return (int) (a.getCheckinCount()-b.getCheckinCount());
					});
		}
		else if(customSorting==1) {
			Collections.sort(customListAfterFilter, 
					(a,b)->{
						return (int) (a.getUsersCount()-b.getUsersCount());
					});
		}
		else if(customSorting==2) {
			Collections.sort(customListAfterFilter, 
					(a,b)->{
						return (int) (a.getTipCount()-b.getTipCount());
					});
		}
		
		return customListAfterFilter;
	}
}

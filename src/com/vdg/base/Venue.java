package com.vdg.base;

import java.util.Arrays;

public class Venue {
	
	private String name;
	private String[] category_name;
	private boolean verified;
	private long checkinCount;
	private long usersCount;
	private long tipCount;
	
	public Venue() {}
	public Venue(String name, String[] category_name, boolean verified, long checkinCount, long usersCount,
			long tipCount) {
		this.name = name;
		this.category_name = category_name;
		this.verified = verified;
		this.checkinCount = checkinCount;
		this.usersCount = usersCount;
		this.tipCount = tipCount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String[] category_name) {
		this.category_name = category_name;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public long getCheckinCount() {
		return checkinCount;
	}
	public void setCheckinCount(int checkinCount) {
		this.checkinCount = checkinCount;
	}
	public long getUsersCount() {
		return usersCount;
	}
	public void setUsersCount(int usersCount) {
		this.usersCount = usersCount;
	}
	public long getTipCount() {
		return tipCount;
	}
	public void setTipCount(int tipCount) {
		this.tipCount = tipCount;
	}
	
	@Override
	public String toString() {
		return "Venue [name=" + name + ", category_name=" + Arrays.toString(category_name) + ", verified=" + verified
				+ ", checkinCount=" + checkinCount + ", usersCount=" + usersCount + ", tipCount=" + tipCount + "]";
	}
	
}

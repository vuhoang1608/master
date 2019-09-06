package jc.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName, lastName, homeCity, username, password;
	private List<CarWash> favorites;
	public static boolean signedIn = false;
	public static String signedInUser = "";
	
	public Account(String firstName, String lastName, String homeCity, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.homeCity = homeCity;
		this.username = username;
		this.password = password;
		favorites = new ArrayList<CarWash>();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getHomeCity() {
		return homeCity;
	}

	public void setHomeCity(String homeCity) {
		this.homeCity = homeCity;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public ArrayList<CarWash> getFavorites() {
		if (favorites == null)
		{
			favorites = new ArrayList<CarWash>();
		}
		return new ArrayList<CarWash>(favorites);
	}

	public void setFavorites(List<CarWash> favorites) {
		this.favorites = favorites;
	}
	
	
	
}

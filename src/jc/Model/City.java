package jc.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class City implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name;
	public List<CarWash> carWashes;
	
	public City(String n, List<CarWash> washes)
	{
		name = n;
		carWashes = washes;		
	}
}

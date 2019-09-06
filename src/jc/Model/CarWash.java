package jc.Model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CarWash implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mName;
	private double mPrice;
	private double mAverageRating;
	private String mAddress;
	private ArrayList<Review> mReviews;
	private Map userReviews;
	public CarWash(String name,double price,double avrgRating,String address,ArrayList<Review> reviews){
		mName=name;mPrice=price;mAverageRating=avrgRating;mAddress=address;
		if(reviews!=null)mReviews=new ArrayList<>(reviews);
		userReviews = new HashMap<String, Review>();
	}
	public String getName() {
		return mName;
	}
	public void setName(String mName) {
		this.mName = mName;
	}
	public double getPrice() {
		return mPrice;
	}
	public void setPrice(double mPrice) {
		this.mPrice = mPrice;
	}
	public double getAverageRating() {
		return mAverageRating;
	}
	public void setAverageRating(double mAverageRating) {
		this.mAverageRating = mAverageRating;
	}
	public String getAddress() {
		return mAddress;
	}
	public void setAddress(String mAddress) {
		this.mAddress = mAddress;
	}

	public ArrayList<Review> getReviews() {
		return new ArrayList<>(mReviews);
	}
	public void setReviews(ArrayList<Review> mReviews) {
		this.mReviews =new ArrayList<>(mReviews);
	}
	public Map getUserReviews() {
		if (userReviews == null)
		{
			userReviews = new HashMap<String, Review>();
		}
		return new HashMap<String, Review>(userReviews);
	}
	public void setUserReviews(Map userReviews) {
		this.userReviews = userReviews;
	}
	public String starRating (double AverageRating) 
	{
		String starRate = "";
		int rating = (int) Math.round(AverageRating);
		for (int i = 1; i <= rating; i++)
			starRate += "★";
		
		for (int i = 0; i < 5 - rating; i++)
			starRate += "☆";
		return starRate;
	
	}
	@Override public String toString(){
		DecimalFormat f2=new DecimalFormat("#.00");
		return "    Name: " + mName + "\n    " +  starRating(mAverageRating) + "\n    Price: $" + f2.format(mPrice) +  
		 "\n    Address: " + mAddress 
		+ "\n    ______________________________________________________________________";
	}
	
	
}

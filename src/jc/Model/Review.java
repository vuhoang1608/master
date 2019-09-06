package jc.Model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Review implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int mRating;
	private String mReview;
	private boolean edited;
	
	public Review(String review,int rating, boolean edited){
		mRating=rating;mReview=review;this.edited = edited;
	}
	public double getmRating() {
		return mRating;
	}
	public void setmRating(int mRating) {
		this.mRating = mRating;
	}
	public String getmReview() {
		return mReview;
	}
	public boolean isEdited() {
		return edited;
	}
	public void setEdited(boolean edited) {
		this.edited = edited;
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
		return (edited?"(Edited)\n" : "") + starRating(mRating)+ "\n"  +mReview+"\n\n";
	}
}

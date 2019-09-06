package jc.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import jc.Model.Account;
import jc.Model.CarWash;
import jc.Model.Review;

public class ReviewScene {
	static CarWash cw;
	static Map<String, Review> userReviews;
	
	@FXML JFXButton review;
	@FXML JFXButton back;
	@FXML ListView<Review> reviewView;
	@FXML Label title;
	@FXML JFXButton quit2;
	@FXML JFXButton changeCity;
	@FXML Hyperlink createSignIn;
	@FXML Label logInWarn;
	
	private ObservableList<Review> rev;
	
	public void initialize() throws FileNotFoundException{
		setSignInText();
		
		 title.setText(cw.getName());
		 rev=FXCollections.observableArrayList(cw.getReviews());
		 
		 userReviews = cw.getUserReviews();
		 if (userReviews != null)
			 userReviews.forEach((k, v)->rev.add((Review)v));
		 
		 // Calculate the real average from the reviews
		 double sum = 0.0;
		 for (Review r : rev)
		 {
			 sum += r.getmRating();
		 }
		 cw.setAverageRating(sum / (double)rev.size());
		 
		 if (Account.signedIn)
		 {
			 logInWarn.setVisible(false);
			 review.setTextFill(Color.WHITE);
			 if (userReviews.containsKey(Account.signedInUser))
				 review.setText("Edit Review");
		 }
		 
//		 File[] files=new File("./src/DataMock").listFiles();
//		 LOOP:for(File f:files){
//			 if(f.getName().equals(cw.getName())){
//				 Scanner fs=new Scanner(f);
//				 review.setText("Edit Review");
//				 String data[]=fs.nextLine().split(";");
//				 rev.add(new Review(data[0],Integer.parseInt(data[1]), data[2].equals("Edited")));
//				 fs.close();
//				 break LOOP;
//			 }
//		 }
		 reviewView.setItems(rev);
	}
	
	@FXML public Object review() throws IOException{
		if (Account.signedIn)
			Main.swapScene("WriteScene.fxml");
		else
		{
			/*** DISPLAY POP UP TO SIGN IN***/
			SignInScene.displaySigninWarning();
		}
		return null;
	}
	@FXML public Object back() throws IOException{
		cw=null;
		Main.swapScene("CityScene.fxml");
		return null;
	}
	@FXML public Object quit2()
	{
		System.exit(0);
		return null;
	}
	@FXML public Object changeCity() throws IOException
	{
		Main.swapScene("StartScene.fxml");
		//dummy comment
		return null;
	}
	@FXML public Object createSignIn() throws IOException
	{
		SignInScene.saveScene = "ReviewScene.fxml";
		Main.swapToLoginScene();
		setSignInText();
		return null;
	}
	
	private void setSignInText()
	{
		if (Account.signedIn)
			createSignIn.setText("Log out");
		else
			createSignIn.setText("Create Account / Sign In");
	}
}
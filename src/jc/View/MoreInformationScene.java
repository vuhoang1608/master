package jc.View;

import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import jc.Model.Account;

public class MoreInformationScene {
	@FXML
	JFXButton back;
	@FXML
	Label title;
	@FXML
	JFXButton quit;
	@FXML
	JFXButton changeCity;
	@FXML
	TextArea moreInformationText;
	@FXML
	Hyperlink createSignIn;
	
	public void initialize() throws FileNotFoundException
	{
		setSignInText();
		
		 title.setText(ReviewScene.cw.getName()); //SET CAR WASH LOCATION NAME
		 File file = new File("./src/DataMock/MoreInformation.txt");
		 Scanner inputFile = new Scanner(file);
		 String carWashName= "";
		 String tempCarWashName = "";
		 //DISPLAY CONTACT AND OPERATIONAL HOUR OF CAR WASH LOCATION
		 while(inputFile.hasNext()) 
		 {
			 carWashName += inputFile.nextLine()+"\n";
			 tempCarWashName = carWashName;
		 }
		 moreInformationText.setText(tempCarWashName);
		 inputFile.close();

	}

	// RETURN TO CHOOSE CAR WASH LOCATION
	@FXML
	public Object back() throws IOException {
		ReviewScene.cw = null;
		Main.swapScene("CityScene.fxml");
		return null;
	}

	// TERMINATE THE PROGRAM
	@FXML
	public Object quit() {
		System.exit(0);
		return null;
	}

	// RETURN TO THE BEGINNING TO SELECT CITY
	@FXML
	public Object changeCity() throws IOException {
		Main.swapScene("StartScene.fxml");
		return null;
	}

	// DIRECT TO THE CREATE ACCOUNT/ SIGN IN SCENE
	@FXML
	public Object createSignIn() throws IOException {
		SignInScene.saveScene = "MoreInformation.fxml";
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
	
//	@FXML public Object writeReview() throws IOException
//	{
//		Main.swapScene("WriteScene.fxml");
//		return null;
//	}

}

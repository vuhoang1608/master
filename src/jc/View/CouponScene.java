package jc.View;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import jc.Model.Account;

public class CouponScene {

	@FXML
	ListView<String> coupon;
	@FXML
	Hyperlink createSignIn;
	@FXML
	JFXButton quit;
	@FXML
	JFXButton back;
	@FXML
	JFXButton changeCity;
	@FXML
	Label title;

	// DISPLAY CITY NAME WHEN CHOSEN
	public void initialize() {
		setSignInText();
		title.setText("Available Coupons");
	}

	// DIRECT TO THE CREATE ACCOUNT/SIGN IN SCENE
	@FXML
	public Object createSignIn() throws IOException {
		SignInScene.saveScene = "CouponScene.fxml";
		Main.swapToLoginScene();
		setSignInText();
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
		CityScene.city = null;
		Main.swapScene("StartScene.fxml");
		return null;
	}
	// RETURN TO CHOOSE CAR WASH LOCATION

	private void setSignInText() {
		if (Account.signedIn)
			createSignIn.setText("Log out");
		else
			createSignIn.setText("Create Account / Sign In");
	}

	// RETURN TO CHOOSE CAR WASH LOCATION
	@FXML
	public Object back() throws IOException {
		// CityScene.city = null;
		Main.swapScene("CityScene.fxml");
		return null;
	}
}

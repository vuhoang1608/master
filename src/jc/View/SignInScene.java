package jc.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jc.Model.Account;
import jc.Model.City;

public class SignInScene {
	@FXML
	JFXButton signIn;
	@FXML
	JFXButton createAccount;
	@FXML
	JFXButton cancel;
	@FXML
	JFXTextField signInUsername;
	@FXML
	JFXPasswordField SignInPass;
	@FXML
	JFXPasswordField signUpPass;
	@FXML
	JFXTextField createFirst;
	@FXML
	JFXTextField createLast;
	@FXML
	JFXComboBox<String> cities;
	@FXML
	JFXTextField createUsername;
	@FXML
	Label createWarning;
	@FXML
	Label signInWarning;
	@FXML
	Label userTakenWarn;

	public static String saveScene;

	public void initialize() throws FileNotFoundException {
		ObservableList<String> cityList = FXCollections.observableArrayList();
		for (City c : Main.carWashes.cities)
			cityList.add(c.name);
		Collections.sort(cityList);
		cities.setItems(cityList);
	}

	@FXML
	public Object signIn() throws IOException {
		String checkUser = signInUsername.getText();
		String checkPass = SignInPass.getText();
		if (checkUser == null || checkUser.equals("") || checkPass == null || checkPass.equals("")) {
			signInWarning.setVisible(true);
			return null;
		}

		int i = 0, len = Main.carWashes.accounts.size();
		while (i < len && !Account.signedIn) {
			Account a = Main.carWashes.accounts.get(i);
			if (checkUser.equals(a.getUsername()) && checkPass.equals(a.getPassword())) {
				Account.signedIn = true;
				Account.signedInUser = checkUser;
			}

			i++;
		}
		if (!Account.signedIn) {
			signInWarning.setVisible(true);
			return null;
		}

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Logged in!");
		alert.setHeaderText("Welcome back " + checkUser + "!");
		alert.setContentText("Now you have access to account-only features, such as writing reviews "
				+ "and saving all of your favorite car washes!");
		alert.show();

		Main.swapScene(saveScene);
		return null;
	}

	@FXML
	public Object createAccount() throws IOException {
		createWarning.setVisible(false);
		userTakenWarn.setVisible(false);

		String fName = createFirst.getText();
		String lName = createLast.getText();
		String city = cities.getValue();
		String user = createUsername.getText();
		String pass = signUpPass.getText();
		if (fName == null || fName.equals("") || lName == null || lName.equals("") || city == null || city.equals("")
				|| user == null || user.equals("") || pass == null || pass.equals("")) {
			createWarning.setVisible(true);
			return null;
		}

		for (Account a : Main.carWashes.accounts) {
			if (a.getUsername().equals(fName)) {
				userTakenWarn.setVisible(true);
				return null;
			}
		}

		Account newAcc = new Account(fName, lName, city, user, pass);
		Main.carWashes.accounts.add(newAcc);

		Account.signedIn = true;
		Account.signedInUser = user;

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Account created!");
		alert.setHeaderText("Account created!");
		alert.setContentText("Now you have access to account-only features, such as writing reviews "
				+ "and saving all of your favorite car washes!");
		alert.show();

		Main.swapScene(saveScene);
		return null;
	}

	@FXML
	public Object cancel() throws IOException {
		Main.swapScene(saveScene);
		return null;
	}

	public static void displaySigninWarning() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Account needed!");
		alert.setHeaderText("Please sign in!");
		alert.setContentText("In order to use this feature, you must be signed in."
				+ "\nClick on the button in the top right corner to create an account or sign in.");
		alert.show();

	}
}

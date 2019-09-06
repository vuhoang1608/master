package jc.View;

import java.io.IOException;
import java.util.Collections;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import jc.Model.Account;
import jc.Model.City;

public class StartScene {
	@FXML JFXButton select;;
	@FXML JFXListView<String> cities;
	@FXML Hyperlink createSignIn;
	
	public void initialize(){
		setSignInText();
		ObservableList<String> cityList=FXCollections.observableArrayList();
		for(City c: Main.carWashes.cities) cityList.add(c.name);
		Collections.sort(cityList);
		cities.setItems(cityList);
	}
	
	@FXML Object select() throws IOException{
		CityScene.city=cities.getSelectionModel().getSelectedItem();
		if(CityScene.city != null && !CityScene.city.equals(""))
			Main.swapScene("CityScene.fxml");
		return null;
	}
	
	@FXML public Object createSignIn() throws IOException
	{
		SignInScene.saveScene = "StartScene.fxml";
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

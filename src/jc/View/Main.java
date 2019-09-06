package jc.View;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import jc.Model.Account;
import jc.Model.CarWashes;

public class Main extends Application{
	static CarWashes carWashes;
	static{
		try {
			carWashes = new CarWashes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static Stage stage;
	private static Class _class;
	public static void main(String[]args){Application.launch();}
	@Override public void start(Stage arg0) throws Exception {
		stage=arg0;
		_class=getClass();
		swapScene("StartScene.fxml");
	}
	public static void swapScene(String fxmlSrc) throws IOException{
		stage.setTitle("Friendly Car Wash");
		stage.setScene(new Scene(FXMLLoader.load(_class.getResource(fxmlSrc))));
		stage.show();
	}
	
	public static void swapToLoginScene() throws IOException
	{
		if (!Account.signedIn)
		{
			swapScene("SignInScene.fxml");
		}
		else
		{
			Account.signedIn = false;
			Account.signedInUser = "";
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Logged out!");
			alert.setHeaderText("You are logged out!");
			alert.setContentText("You are now logged out.\n" +
					"To gain access to account-only features, such as writing reviews " +
					"and saving all of your favorite car washes, please sign in again.");
			alert.show();
			swapScene(SignInScene.saveScene);
		}
	}
}

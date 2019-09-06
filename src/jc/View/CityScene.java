package jc.View;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import jc.Model.Account;
import jc.Model.CarWash;
import jc.Model.City;

public class CityScene {
	static String city;
	ObservableList<String> sortMenu=FXCollections.observableArrayList
			("Alphabetical", "Price", "Rating");
	@FXML
	Label title;
	@FXML
	Label indicator;
	@FXML
	ListView<CarWash> washes;
	@FXML
	JFXComboBox<String> sortList;
	@FXML
	JFXButton select;
	@FXML
	JFXButton back;
	@FXML
	JFXButton quit;
	@FXML
	JFXButton information;
	@FXML
	Hyperlink createSignIn;
	@FXML
	JFXButton seeCoupon;
	@FXML
	JFXButton seeFavorites;
	@FXML
	JFXButton addToFavs;
	@FXML
	JFXButton removeFavs;
	@FXML
	JFXButton seeAll;
	@FXML
	Label addedToFavorite;

	private ObservableList<CarWash> washList;
	private List<CarWash> favorites;

	public void initialize() throws FileNotFoundException {
		sortList.setItems(sortMenu);
		setSignInText();

		washList = FXCollections.observableArrayList();
		title.setText("You have chosen " + city);

		if (Account.signedIn) {
			seeFavorites.setTextFill(Color.WHITE);
			addToFavs.setTextFill(Color.WHITE);
			seeAll.setTextFill(Color.WHITE);
			// removeFavs.setTextFill(Color.BLACK);

			Account currAccount = null;
			int i = 0, end = Main.carWashes.accounts.size();
			while (i < end && currAccount == null) {
				if (Main.carWashes.accounts.get(i).getUsername().equals(Account.signedInUser))
					currAccount = Main.carWashes.accounts.get(i);
				i++;
			}

			if (currAccount == null)
				System.out.println("Whoops something went wrong with making the account.");

			favorites = currAccount.getFavorites();
		}
		else
		{
			addedToFavorite.setText("Log in to use these features");
			addedToFavorite.setUnderline(true);
			addedToFavorite.setStyle("-fx-opacity: 1");
		}

		City currCity = null;
		int i = 0, end = Main.carWashes.cities.size();
		while (i < end && currCity == null) {
			if (Main.carWashes.cities.get(i).name.equals(city))
				currCity = Main.carWashes.cities.get(i);
			i++;
		}

		if (currCity == null)
			System.out.println("Whoops something went wrong with making the city.");

		for (CarWash wash : currCity.carWashes) {
			washList.add(wash);
		}

		washes.setItems(washList);
	}

	@FXML
	public Object seeFavorites() {
		if (Account.signedIn) {
			washList.clear();

			for (CarWash wash : favorites) {
				washList.add(wash);
			}
			washes.setItems(washList);

			removeFavs.setTextFill(Color.WHITE);
			removeFavs.setDisable(false);
		} else {
			/*** DISPLAY POP UP TO SIGN IN ***/
			SignInScene.displaySigninWarning();
		}
		return null;
	}

	@FXML
	public Object addToFavs() {
		if (Account.signedIn) {
			CarWash temp = washes.getSelectionModel().getSelectedItem();
			if (temp != null && !favorites.contains(temp)) {
				favorites.add(temp);
				Task<Void> task = new Task<Void>() {
					// IDICATE IF CAR WASH LOCATION IS ADDED TO FAVORITE LIST
					@Override
					protected Void call() throws Exception {
						// TODO Auto-generated method stub
						addedToFavorite.setStyle("-fx-opacity: 1");
						Thread.sleep(1000);
						addedToFavorite.setStyle("-fx-opacity: 0.9");
						Thread.sleep(100);
						addedToFavorite.setStyle("-fx-opacity: 0.8");
						Thread.sleep(100);
						addedToFavorite.setStyle("-fx-opacity: 0.7");
						Thread.sleep(100);
						addedToFavorite.setStyle("-fx-opacity: 0.6");
						Thread.sleep(100);
						addedToFavorite.setStyle("-fx-opacity: 0.5");
						Thread.sleep(100);
						addedToFavorite.setStyle("-fx-opacity: 0.4");
						Thread.sleep(100);
						addedToFavorite.setStyle("-fx-opacity: 0.3");
						Thread.sleep(100);
						addedToFavorite.setStyle("-fx-opacity: 0.2");
						Thread.sleep(100);
						addedToFavorite.setStyle("-fx-opacity: 0.1");
						Thread.sleep(100);
						addedToFavorite.setStyle("-fx-opacity: 0");
						Thread.sleep(100);
						return null;
					}
				};
				new Thread(task).start();
			}

			boolean found = false;
			int i = 0, end = Main.carWashes.accounts.size();
			while (i < end && !found) {
				if (Main.carWashes.accounts.get(i).getUsername().equals(Account.signedInUser)) {
					Main.carWashes.accounts.get(i).setFavorites(favorites);
					found = true;
				}
				i++;
			}
		} else {
			/*** DISPLAY POP UP TO SIGN IN ***/
			SignInScene.displaySigninWarning();
		}
		return null;
	}

	@FXML
	public Object removeFavs() throws IOException {
		if (Account.signedIn) {
			CarWash temp = washes.getSelectionModel().getSelectedItem();
			if (temp != null) {
				favorites.remove(temp);
			}

			boolean found = false;
			int i = 0, end = Main.carWashes.accounts.size();
			while (i < end && !found) {
				if (Main.carWashes.accounts.get(i).getUsername().equals(Account.signedInUser)) {
					Main.carWashes.accounts.get(i).setFavorites(favorites);
					found = true;
				}
				i++;
			}
			seeFavorites();
		} else {
			/*** DISPLAY POP UP TO SIGN IN ***/
			SignInScene.displaySigninWarning();
		}

		return null;
	}

	@FXML
	public Object seeAll() throws IOException {
		Main.swapScene("CityScene.fxml");
		return null;
	}

	@FXML public Object sortList(){
	    String selectedAction = sortList.getValue().toString();
	    
	    if (selectedAction.equalsIgnoreCase("Alphabetical"))
	    {
	    	Collections.sort(washList,new Comparator<CarWash>() {
	            public int compare(CarWash c1, CarWash c2) {
	                //You should ensure that list doesn't contain null values!
	                return c1.getName().compareTo(c2.getName());
	            }
	           });
	    	indicator.setVisible(false);
	    }
	    else if (selectedAction.equalsIgnoreCase("Price"))
	    {
	    	Collections.sort(washList,new Comparator<CarWash>(){
				public int compare(CarWash c1,CarWash c2){
					if(c1.getPrice()>c2.getPrice())return 1;
					else if(c1.getPrice()<c2.getPrice())return -1;
					return 0;
				}
			});
	    	indicator.setText("▲");
	    	indicator.setVisible(true);
	    }
	    else 
	    {
	    	Collections.sort(washList,new Comparator<CarWash>(){
				public int compare(CarWash c1,CarWash c2){
					if(c1.getAverageRating()>c2.getAverageRating())return -1;
					else if(c1.getAverageRating()<c2.getAverageRating())return 1;
					return 0;
				}
			});
	    	indicator.setText("▼");
	    	indicator.setVisible(true);
	    }
	return null;
}
	@FXML
	public Object select() throws IOException {
		ReviewScene.cw = washes.getSelectionModel().getSelectedItem();
		if (ReviewScene.cw == null)
			return null;
		Main.swapScene("ReviewScene.fxml");
		return null;
	}

	@FXML
	public Object back() throws IOException {
		city = null;
		Main.swapScene("StartScene.fxml");
		return null;
	}

	@FXML
	public Object quit() {
		System.exit(0);
		return null;
	}

	@FXML
	public Object information() throws IOException {
		ReviewScene.cw = washes.getSelectionModel().getSelectedItem();
		if (ReviewScene.cw == null)
			return null;
		Main.swapScene("MoreInformation.fxml");
		return null;
	}

	@FXML
	public Object createSignIn() throws IOException {
		SignInScene.saveScene = "CityScene.fxml";
		Main.swapToLoginScene();
		setSignInText();
		return null;
	}

	@FXML
	public Object seeCoupon() throws IOException {
		Main.swapScene("CouponScene.fxml");
		return null;
	}

	private void setSignInText() {
		if (Account.signedIn)
			createSignIn.setText("Log out");
		else
			createSignIn.setText("Create Account / Sign In");
	}
}

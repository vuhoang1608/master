package jc.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jc.Model.Account;
import jc.Model.Review;

public class WriteScene {
	@FXML
	JFXButton confirm;
	@FXML
	JFXButton back;
	@FXML
	JFXTextField rating;
	@FXML
	JFXTextArea reviewBody;
	@FXML
	Label title;
	@FXML
	Label warning;
	
	private boolean editing;

	public void initialize() throws FileNotFoundException {
		title.setText("Reviewing: " + ReviewScene.cw.getName());
		editing = false;
		
		// Assumed user is signed in
		if (ReviewScene.userReviews.containsKey(Account.signedInUser))
		{
			Review userRev = (Review) ReviewScene.userReviews.get(Account.signedInUser);
			reviewBody.setText(userRev.getmReview());
			rating.setText("" + (int)userRev.getmRating());
			editing = true;
		}
		
		
//		File[] files = new File("./src/DataMock").listFiles();
//		LOOP: for (File f : files) {
//			if (f.getName().equals(ReviewScene.cw.getName())) {
//				Scanner fs = new Scanner(f);
//				String data[] = fs.nextLine().split(";");
//				reviewBody.setText(data[0]);
//				rating.setText(data[1]);
//				fs.close();
//				editing = true;
//				break LOOP;
//			}
//		}
	}

	@FXML
	public Object confirm() throws IOException {
		//StringBuilder sb = new StringBuilder();
		int newRating;
		try {
			newRating = Integer.parseInt(rating.getText());
			if (rating.getText().equals("") || rating.getText() == null || newRating > 5 || newRating < 1)
				throw new Exception();
		} catch (Exception e) {
			warning.setVisible(true);
			return null;
		}
		String body = reviewBody.getText();
		
		Review toAdd = new Review(body, newRating, editing);
		ReviewScene.userReviews.put(Account.signedInUser, toAdd);
		ReviewScene.cw.setUserReviews(ReviewScene.userReviews);
		
//		Scanner ls = new Scanner(body);
//		while (ls.hasNextLine())
//			sb.append(ls.nextLine() + " ");
//		ls.close();
//		PrintWriter pw = new PrintWriter(new FileOutputStream(new File("./src/DataMock/" + ReviewScene.cw.getName())));
//		body = sb.toString().trim();
//		body += ";" + newRating;
//		pw.write(body);
//		if (editing)
//			pw.write(";Edited");
//		else
//			pw.write(";Not");
//		pw.close();
    	Main.swapScene("ReviewScene.fxml");
		return null;
	}

	@FXML
	public Object back() throws IOException {
		Main.swapScene("ReviewScene.fxml");
		return null;
	}
}

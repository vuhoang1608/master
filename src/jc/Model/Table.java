package jc.Model;

import java.util.ArrayList;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.util.Callback;
import jc.Model.CarWash;

public class Table extends CarWash{
private final SimpleStringProperty mName;
private final SimpleDoubleProperty mPrice;
private final SimpleDoubleProperty mRate;
private final SimpleStringProperty mAddress;

public Table(String newName, Double newPrice, double newRate, String newAddress)
{
	super(newName, newPrice, newRate,newAddress,null);
	mName = new SimpleStringProperty(newName);
	mPrice = new SimpleDoubleProperty(newPrice);
	mRate = new SimpleDoubleProperty(newRate);
	mAddress = new SimpleStringProperty(newAddress);
}

public String getName() {
	return mName.get();
}

public void setName(String newName) {
	mName.set(newName);
}

public double getPrice() {
	return mPrice.get();
}

public void setPrice(Double newPrice) {
	mPrice.set(newPrice);
}

public Double getRate() {
	return mRate.get();
}

public void setRate(double newRate) {
	mRate.set(newRate);
}
public String getAddress() {
	return mAddress.get();
}

public void setAddress(String newAddress) {
	mAddress.set(newAddress);
}

public ArrayList<Review> getArrayReviews() {
	return super.getReviews();
}

}
